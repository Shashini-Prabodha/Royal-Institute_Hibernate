package lk.royal.hibernate.dao.custom;

import lk.royal.hibernate.dao.CrudDAO;
import lk.royal.hibernate.entity.Registration;

public interface RegisterDAO  extends CrudDAO<Registration, String> {
    int getLastRegNo() throws Exception;

}
