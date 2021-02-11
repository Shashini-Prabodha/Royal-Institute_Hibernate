package lk.royal.hibernate.db;

import lk.royal.hibernate.entity.Course;
import lk.royal.hibernate.entity.Registration;
import lk.royal.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
   private static FactoryConfiguration factoryConfiguration;
   private SessionFactory sessionFactory;

   private FactoryConfiguration(){
       Configuration configuration = new Configuration().configure()
               .addAnnotatedClass(Student.class)
               .addAnnotatedClass(Course.class)
               .addAnnotatedClass(Registration.class);

       sessionFactory = configuration.buildSessionFactory();
//       Properties properties = new Properties();
//
//       try {
//           properties.load(new FileInputStream("E:\\sem 2\\Hibernate\\Royal-Institute-master\\src\\lk\\royal\\hibernate\\hibernate.properties"));
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
//
//
//       Configuration configuration = new Configuration();
//
//       configuration.configure("hibernate.properties").addProperties(properties).addAnnotatedClass(Student.class);
//
//       ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//               .applySettings(configuration.getProperties()).build();
//
//       sessionFactory = configuration.buildSessionFactory(serviceRegistry);
   }
   public static FactoryConfiguration getInstance(){
       return (factoryConfiguration==null)?factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
   }
   public Session getSession(){
       return sessionFactory.openSession();
   }

}
