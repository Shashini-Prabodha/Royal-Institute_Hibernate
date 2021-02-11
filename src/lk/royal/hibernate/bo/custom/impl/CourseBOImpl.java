package lk.royal.hibernate.bo.custom.impl;

import lk.royal.hibernate.bo.custom.CourseBO;
import lk.royal.hibernate.dao.DAOFactory;
import lk.royal.hibernate.dao.DAOType;
import lk.royal.hibernate.dao.custom.CourseDAO;
import lk.royal.hibernate.dto.CourseDTO;
import lk.royal.hibernate.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO= DAOFactory.getInstance().getDAO(DAOType.COURSE);

    @Override
    public boolean saveCourse(CourseDTO dto) throws Exception {
        return courseDAO.save(new Course(dto.getCode(),dto.getCourseName(),dto.getType(),dto.getDuration(),dto.getFee()));
    }

    @Override
    public boolean updateCourse(CourseDTO dto) throws Exception {
        return courseDAO.update(new Course(dto.getCode(),dto.getCourseName(),dto.getType(),dto.getDuration(),dto.getFee()));
    }

    @Override
    public boolean deleteCourse(String id) throws Exception {
        return courseDAO.delete(id);
    }

    @Override
    public CourseDTO getCourse(String id) throws Exception {
        Course course = courseDAO.get(id);
        return new CourseDTO(course.getCode(),course.getCourseName(),course.getType(),course.getDuration(),course.getFee());
    }

    @Override
    public ArrayList<CourseDTO> getAllCourse() throws Exception {
        List<Course> all = courseDAO.getAll();
        ArrayList<CourseDTO> list = new ArrayList<>();
        for (Course course : all) {
            list.add(new CourseDTO(course.getCode(),
                    course.getCourseName(),
                    course.getType(),
                    course.getDuration(),
                    course.getFee()));
        }
        return null;
    }
}
