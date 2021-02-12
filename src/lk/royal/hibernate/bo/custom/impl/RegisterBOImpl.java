package lk.royal.hibernate.bo.custom.impl;

import lk.royal.hibernate.bo.custom.RegisterBO;
import lk.royal.hibernate.dao.DAOFactory;
import lk.royal.hibernate.dao.DAOType;
import lk.royal.hibernate.dao.custom.CourseDAO;
import lk.royal.hibernate.dao.custom.RegisterDAO;
import lk.royal.hibernate.dao.custom.StudentDAO;
import lk.royal.hibernate.db.FactoryConfiguration;
import lk.royal.hibernate.dto.RegistrationDTO;
import lk.royal.hibernate.dto.StudentDTO;
import lk.royal.hibernate.entity.Course;
import lk.royal.hibernate.entity.Registration;
import lk.royal.hibernate.entity.Student;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class RegisterBOImpl implements RegisterBO {

    RegisterDAO registerDAO = DAOFactory.getInstance().getDAO(DAOType.REGISTER);
   CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);
    StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);

    @Override
    public boolean saveRegister(RegistrationDTO dto) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        session.beginTransaction();

        StudentDTO studentDTO = dto.getStudentDTO();
        Student student = new Student(studentDTO.getID(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContactNo(), studentDTO.getDob(), studentDTO.getGender());

        Course course = courseDAO.get(dto.getCourse_code());
        System.out.println(dto.getRegNo()+" "+dto.getRegDate()+" "+ dto.getRegFee()+" "+student.getID()+" "+course.getCode());
        return registerDAO.save(new Registration(dto.getRegNo(), dto.getRegDate(), dto.getRegFee(),student,course));
    }

    @Override
    public boolean updateRegister(RegistrationDTO dto) throws Exception {
        return registerDAO.update(new Registration(dto.getRegNo(), dto.getRegDate(), dto.getRegFee()));
    }

    @Override
    public boolean deleteRegister(String id) throws Exception {
        return registerDAO.delete(id);
    }

    @Override
    public RegistrationDTO getRegister(String id) throws Exception {
        Registration registration = registerDAO.get(id);
        return new RegistrationDTO(registration.getRegNo(), registration.getRegDate(), registration.getRegFee());
    }

    @Override
    public ArrayList<RegistrationDTO> getAllRegister() throws Exception {
        List<Registration> all = registerDAO.getAll();
        ArrayList<RegistrationDTO> list = new ArrayList<>();
        for (Registration registration : all) {
            list.add(new RegistrationDTO(
                    registration.getRegNo(),
                    registration.getRegDate(),
                    registration.getRegFee())
            );
        }
        return list;
    }

    @Override
    public int newRegNo() throws Exception {
        return registerDAO.getLastRegNo()+1;
    }
}
