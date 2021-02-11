package lk.royal.hibernate.dao;

import lk.royal.hibernate.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudDAO<T extends SuperEntity,ID extends Serializable> extends SuperDAO {

//    void save(T entity) throws Exception;
//
//    void update(T entity) throws Exception;
//
//    void delete(ID id) throws Exception;

    boolean save(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(ID id) throws Exception;

    T get(ID id) throws Exception;

    List<T> getAll() throws Exception;



}