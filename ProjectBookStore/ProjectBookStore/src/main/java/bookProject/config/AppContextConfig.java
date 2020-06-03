package bookProject.config;

import bookProject.DAO.*;

import bookProject.service.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

public class AppContextConfig {
    @Configuration
    @ComponentScan("bookProject.*")
    @EnableTransactionManagement
    @PropertySource("classpath:sql.properties")
    public class ApplicationContextConfig {

        @Autowired
        private Environment env;

        @Bean
        public ResourceBundleMessageSource messageSource() {
            ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
            // Load property in message/validator.properties
            rb.setBasenames(new String[]{"messages/validator"});
            return rb;
        }

        @Bean(name = "multipartResolver")
        public CommonsMultipartResolver multipartResolver() {
            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
            return commonsMultipartResolver;
        }

        /*@Bean(name = "dataSource")
        public DataSource getDataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
            dataSource.setUrl(env.getProperty("ds.url"));
            dataSource.setUsername(env.getProperty("ds.username"));
            dataSource.setPassword(env.getProperty("ds.password"));
            System.out.println("## getDataSource: " + dataSource);
            return dataSource;
        }*/

        /*@Autowired
        @Bean(name = "sessionFactory")
        public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
            Properties properties = new Properties();
            properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
            properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
            properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
            LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
            factoryBean.setPackagesToScan(new String[]{"bookProject.domain"});
            factoryBean.setDataSource(dataSource);
            factoryBean.setHibernateProperties(properties);
            factoryBean.afterPropertiesSet();
            SessionFactory sf = factoryBean.getObject();
            System.out.println("## getSessionFactory: " + sf);
            return sf;
        }

        @Autowired
        @Bean(name = "transactionManager")
        public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
            HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
            return transactionManager;
        }

        /*@Bean(name = "userService")
        public UserService getApplicantSrvice() {
            return new UserServiceImpl();
        }

        @Bean(name = "bookService")
        public BookService getBookService() {
            return new BookServiceImpl();
        }

        @Bean(name = "orderService")
        public OrderService getOrderService() {
            return new OrderServiceImpl();
        }*/
    }
}