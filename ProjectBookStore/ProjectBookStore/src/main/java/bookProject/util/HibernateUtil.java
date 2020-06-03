package bookProject.util;

import bookProject.domain.Book;
import bookProject.domain.Order;
import bookProject.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER,"com.mysql.jdbc.Driver");
                settings.put(Environment.URL,"jdbc:mysql://localhost:3306/books?useSSL=false&serverTimezone=UTC");
                settings.put(Environment.USER,"root");
                settings.put(Environment.PASS,"customer13");
                settings.put(Environment.SHOW_SQL,"true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Book.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
