package lk.royal.hibernate.bo.custom.impl;

import lk.royal.hibernate.bo.custom.RegisterBO;
import lk.royal.hibernate.dao.DAOFactory;
import lk.royal.hibernate.dao.DAOType;
import lk.royal.hibernate.dao.custom.RegisterDAO;
import lk.royal.hibernate.dto.RegistrationDTO;
import lk.royal.hibernate.entity.Registration;

import java.util.ArrayList;
import java.util.List;

public class RegisterBOImpl implements RegisterBO {

    RegisterDAO registerDAO = DAOFactory.getInstance().getDAO(DAOType.REGISTER);

    @Override
    public boolean saveRegister(RegistrationDTO dto) throws Exception {
        return registerDAO.save(new Registration(dto.getRegNo(), dto.getRegDate(), dto.getRegFee()));
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
        return null;
    }
}
