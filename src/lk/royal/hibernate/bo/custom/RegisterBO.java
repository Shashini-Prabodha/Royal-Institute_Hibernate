package lk.royal.hibernate.bo.custom;

import lk.royal.hibernate.bo.SuperBO;
import lk.royal.hibernate.dto.RegistrationDTO;

import java.util.ArrayList;

public interface RegisterBO extends SuperBO {
    boolean saveRegister(RegistrationDTO dto) throws Exception;

    boolean updateRegister(RegistrationDTO dto) throws Exception;

    boolean deleteRegister(String id) throws Exception;

    RegistrationDTO getRegister(String id) throws Exception;

    ArrayList<RegistrationDTO> getAllRegister() throws Exception;
}
