package lk.royal.hibernate.dao;

import lk.royal.hibernate.dao.custom.impl.CourseDAOImpl;
import lk.royal.hibernate.dao.custom.impl.QueryDAOImpl;
import lk.royal.hibernate.dao.custom.impl.RegisterDAOImpl;
import lk.royal.hibernate.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory(): daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOType daoType){
        switch (daoType){
            case STUDENT:
                return (T) new StudentDAOImpl();
            case REGISTER:
                return (T) new RegisterDAOImpl();
            case COURSE:
                return (T) new CourseDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}
