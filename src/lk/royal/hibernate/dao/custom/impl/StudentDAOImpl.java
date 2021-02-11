package lk.royal.hibernate.dao.custom.impl;

import lk.royal.hibernate.dao.custom.StudentDAO;
import lk.royal.hibernate.db.FactoryConfiguration;
import lk.royal.hibernate.entity.Course;
import lk.royal.hibernate.entity.Registration;
import lk.royal.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private Session session;

    public StudentDAOImpl() {
        this.session = FactoryConfiguration.getInstance().getSession();
    }

    @Override
    public boolean save(Student entity) throws Exception {
        Transaction transaction = session.beginTransaction();
        Serializable save = session.save(entity);

        System.out.println("Ser + " + save);

        transaction.commit();
        session.close();
        return save != null;
    }

    @Override
    public boolean update(Student entity) throws Exception {
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
    public Student get(String id) throws Exception {
        Transaction transaction = session.beginTransaction();
        try {
            Student student = session.get(Student.class, id);
            transaction.commit();
            session.close();
            return student;
        } catch (Exception exception) {
            transaction.rollback();
        }
        return null;
    }

    @Override
    public List<Student> getAll() throws Exception {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM Student");
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

}
