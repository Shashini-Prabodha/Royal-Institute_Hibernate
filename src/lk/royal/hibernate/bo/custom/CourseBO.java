package lk.royal.hibernate.bo.custom;

import lk.royal.hibernate.dto.CourseDTO;

import java.util.ArrayList;

public interface CourseBO {
    boolean saveCourse(CourseDTO dto) throws Exception;

    boolean updateCourse(CourseDTO dto) throws Exception;

    boolean deleteCourse(String id) throws Exception;

    CourseDTO getCourse(String id) throws Exception;

    ArrayList<CourseDTO> getAllCourse() throws Exception;
}
