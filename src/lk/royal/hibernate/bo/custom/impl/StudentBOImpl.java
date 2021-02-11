package lk.royal.hibernate.bo.custom.impl;

import lk.royal.hibernate.bo.custom.StudentBO;
import lk.royal.hibernate.dao.DAOFactory;
import lk.royal.hibernate.dao.DAOType;
import lk.royal.hibernate.dao.custom.StudentDAO;
import lk.royal.hibernate.dto.StudentDTO;
import lk.royal.hibernate.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO dto) throws Exception {
        return studentDAO.save(new Student(dto.getID(), dto.getName(), dto.getAddress(), dto.getContactNo(), dto.getDob(), dto.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        return studentDAO.update(new Student(dto.getID(), dto.getName(), dto.getAddress(), dto.getContactNo(), dto.getDob(), dto.getGender()));
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public StudentDTO getStudent(String id) throws Exception {
        Student student = studentDAO.get(id);
        return new StudentDTO(student.getID(), student.getName(), student.getAddress(), student.getContactNo(), student.getDob(), student.getGender());
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws Exception {
        List<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> list = new ArrayList<>();
        for (Student student : all) {
            list.add(new StudentDTO(student.getID(),
                    student.getName(),
                    student.getAddress(),
                    student.getContactNo(),
                    student.getDob(),
                    student.getGender())
            );
        }
        return null;
    }
}
