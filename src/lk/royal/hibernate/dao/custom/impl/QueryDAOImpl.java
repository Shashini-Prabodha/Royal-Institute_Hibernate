package lk.royal.hibernate.dao.custom.impl;

import lk.royal.hibernate.dao.custom.QueryDAO;
import lk.royal.hibernate.db.FactoryConfiguration;
import lk.royal.hibernate.entity.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Student> getCourseWiseStudent(String code) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        NativeQuery sqlQuery = session.createSQLQuery("select s.ID,s.name, s.address,s.contactNo,s.dob,s.gender from student s INNER JOIN course c,registration r,registration_course rc where c.code=?1 and r.student_ID=s.ID and r.regNo=rc.registrations_regNo and rc.course_code=c.code");
        sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        sqlQuery.setParameter(1, code);
        List students = sqlQuery.list();
        List<Student> list=new ArrayList<>();

        for (Object student : students) {
            Map m = (Map) student;

            list.add(new Student(m.get("ID")+"",m.get("name")+"" ,m.get("address")+"",Integer.parseInt(m.get("contactNo")+"") ,Date.valueOf(m.get("dob")+""),m.get("gender")+""));
            System.out.println(m.get("name") + " - " + m.get("address"));
        }
        transaction.commit();

        return list;
    }

    @Override
    public void setSession(Session session) {

    }
}

//    select s.ID,s.name, s.address,s.contactNo,s.dob,s.gender from student s INNER JOIN course c,registration r,registration_course rc where c.code="C001" and r.student_ID=s.ID and r.regNo=rc.registrations_regNo and rc.course_code=c.code;

