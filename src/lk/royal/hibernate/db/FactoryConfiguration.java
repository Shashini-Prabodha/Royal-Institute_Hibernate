package lk.royal.hibernate.db;

import lk.royal.hibernate.entity.Course;
import lk.royal.hibernate.entity.Registration;
import lk.royal.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
//       Configuration configuration = new Configuration().configure()
//               .addAnnotatedClass(Student.class)
//               .addAnnotatedClass(Course.class)
//               .addAnnotatedClass(Registration.class);
//
//       sessionFactory = configuration.buildSessionFactory();


        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().loadProperties("hibernate.properties").build();

        Metadata meta = new MetadataSources(ssr)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Registration.class).getMetadataBuilder().build();

        sessionFactory = meta.getSessionFactoryBuilder().build();


    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
