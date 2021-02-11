package lk.royal.hibernate.dao.custom.impl;

import lk.royal.hibernate.dao.custom.CourseDAO;
import lk.royal.hibernate.db.FactoryConfiguration;
import lk.royal.hibernate.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    private Session session;

    public CourseDAOImpl() {
        this.session = FactoryConfiguration.getInstance().getSession();
    }

    @Override
    public boolean save(Course entity) throws Exception {
        Transaction transaction = session.beginTransaction();
        Serializable save = session.save(entity);

        System.out.println("Ser + " + save);

        transaction.commit();
        session.close();
        return save != null;
    }

    @Override
    public boolean update(Course entity) throws Exception {
        Transaction transaction = session.beginTransaction();

        try {
            session.update(entity);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception exception) {
            transaction.rollback();
        }
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(id);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception exception) {
            transaction.rollback();
        }
        return false;

    }

    @Override
    public Course get(String id) throws Exception {
        Transaction transaction = session.beginTransaction();
        try {
            Course course = session.get(Course.class, id);
            transaction.commit();
            session.close();
            return course;
        } catch (Exception exception) {
            transaction.rollback();
        }
        return null;
    }

    @Override
    public List<Course> getAll() throws Exception {
        Transaction transaction = session.beginTransaction();
        try {

            Query query = session.createQuery("FROM Course");
            List list = query.list();
            transaction.commit();
            session.close();
            return list;
        } catch (Exception exception) {
            transaction.rollback();
        }
        return null;
    }

    @Override
    public void setSession(Session session) {

    }

    @Override
    public String getLastCourseID() throws Exception {
        Transaction transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("select code from Course order by code desc limit 1");
        String id = (String) sqlQuery.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }
}
