package lk.royal.hibernate.dao;

import lk.royal.hibernate.entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CrudDAOImpl<T extends SuperEntity, ID extends Serializable> implements CrudDAO<T, ID> {

    protected Session session;
    private Class<T> entity;

    public CrudDAOImpl() {
        entity = (Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

//    @Override
//    public void save(SuperEntity entity) throws Exception {
//        session.save(entity);
//    }
//
//    @Override
//    public void update(SuperEntity entity) throws Exception {
//        session.update(entity);
//    }
//
//    @Override
//    public void delete(ID id) throws Exception {
//session.delete(session.load(entity,id));
//    }

    @Override
    public boolean save(T entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(T entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(ID id) throws Exception {
        return false;
    }

    @Override
    public T get(ID id) throws Exception {
      return session.get(entity,id);
    }

    @Override
    public List<T> getAll() throws Exception {
        return session.createQuery("FROM"+entity.getName()).list();
    }


}

//
//public abstract class CrudDAOImpl<T extends SuperEntity,ID extends Serializable> implements CrudDAO<T, ID>{
//
//    protected Session session;
//    private Class<T> entity;
//
//    public CrudDAOImpl(){
//        entity = (Class<T>)(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
//    }
//
//    @Override
//    public void setSession(Session session) {
//        this.session = session;
//    }
//
//    @Override
//    public void save(T entity) throws Exception {
//        session.save(entity);
//    }
//
//    @Override
//    public void update(T entity) throws Exception {
//        session.update(entity);
//    }
//
//    @Override
//    public void delete(ID key) throws Exception {
//        session.delete(session.load(entity,key));
//    }
//
//    @Override
//    public List<T> findAll() throws Exception {
//        return session.createQuery("FROM " + entity.getName()).list();
//    }
//
//    @Override
//    public T find(ID key) throws Exception {
//        return session.get(entity,key);
//    }