package lk.royal.hibernate.bo.custom;

import lk.royal.hibernate.dto.StudentDTO;

import java.util.ArrayList;

public interface StudentBO {
    boolean saveStudent(StudentDTO dto) throws Exception;

    boolean updateStudent(StudentDTO dto) throws Exception;

    boolean deleteStudent(String id) throws Exception;

    StudentDTO getStudent(String id) throws Exception;

    ArrayList<StudentDTO> getAllStudent() throws Exception;
}
