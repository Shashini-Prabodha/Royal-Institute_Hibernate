package lk.royal.hibernate.dao.custom;

import lk.royal.hibernate.dao.CrudDAO;
import lk.royal.hibernate.entity.Course;

public interface CourseDAO extends CrudDAO<Course, String> {
    String getLastCourseID() throws Exception;

}
