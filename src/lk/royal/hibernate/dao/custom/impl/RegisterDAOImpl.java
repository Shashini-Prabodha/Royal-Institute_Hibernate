package lk.royal.hibernate.dao.custom.impl;

import lk.royal.hibernate.dao.custom.RegisterDAO;
import lk.royal.hibernate.db.FactoryConfiguration;
import lk.royal.hibernate.entity.Course;
import lk.royal.hibernate.entity.Registration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class RegisterDAOImpl implements RegisterDAO {

    private Session session;

    public RegisterDAOImpl() {
        this.session = FactoryConfiguration.getInstance().getSession();
    }

    @Override
    public boolean save(Registration entity) throws Exception {
        Transaction transaction = session.beginTransaction();
        Serializable save = session.save(entity);
        transaction.commit();
        session.close();
        return save != null;
    }

    @Override
    public boolean update(Registration entity) throws Exception {
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
    public Registration get(String id) throws Exception {
        Transaction transaction = session.beginTransaction();
        try {
            Registration registration = session.get(Registration.class, id);
            transaction.commit();
            session.close();
            return registration;
        } catch (Exception exception) {
            transaction.rollback();
        }
        return null;
    }

    @Override
    public List<Registration> getAll() throws Exception {
        Transaction transaction = session.beginTransaction();
        try {

            Query query = session.createQuery("FROM Registration");
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
