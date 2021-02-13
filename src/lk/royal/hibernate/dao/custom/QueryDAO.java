package lk.royal.hibernate.dao.custom;

import lk.royal.hibernate.dao.SuperDAO;
import lk.royal.hibernate.entity.Student;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Student> getCourseWiseStudent(String code) throws Exception;
}
