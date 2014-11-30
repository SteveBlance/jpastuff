package com.codaconsultancy.model;

import com.codaconsultancy.entities.Snafu;
import com.codaconsultancy.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

//    public static void main(String[] args) {
//        HibernateUtil.recreateDatabase();
//        setupUsers();
//        setupSnafu();
//    }

    private static void setupSnafu() {
        HibernateUtil.beginTransaction();
        Snafu snafu = new Snafu();
        snafu.setSituation("normal");
        getSession().save(snafu);
        HibernateUtil.commitTransaction();
    }

    private static void setupUsers() {
        saveUser("mj", "abc123", "mj@mcnz.com", false, new Date(), new GregorianCalendar(2006, 1, 1));
        saveUser("mario", "pass", "mario@scja.ca", true, newDate("01/01/2008"), new GregorianCalendar(2007, 1, 1));
        saveUser("sk8trgrl", "password", "avril@scja.com", false, new Date(), new GregorianCalendar(2008, 1, 1));
        saveUser("ridley", "mypassword", "getbent@scja.ca", true, newDate("05/10/2006"), new GregorianCalendar(2006, 5, 11));
        saveUser("kerrr", "pwd", "sheehan@princessjava.com", false, newDate("25/02/2008"), new GregorianCalendar(2007, 12, 12));
        saveUser("astra", "pwd", "rabbit@princessjava.com", false, new Date(), new GregorianCalendar());
        saveUser("cameron", "90210", "me@scwcd.com", true, newDate("15/09/2008"), new GregorianCalendar(2008, 8, 12));
        saveUser("stephen", "low", "stanley@pulpjava.com", false, newDate("25/02/2008"), new GregorianCalendar(2008, 2, 15));
        saveUser("che", "password", "ernesto@pulpjava.com", true, newDate("26/07/1999"), new GregorianCalendar(1999, 3, 9));
        saveUser("remy", "password", "rabbit@scja.com", false, new Date(), new GregorianCalendar(2007, 5, 21));
    }

    private static Date newDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static void saveUser(String loginName, String password, String emailAddress, boolean verified, Date lastAccessedDate, GregorianCalendar registrationDate) {
        Session session = HibernateUtil.beginTransaction();
        User user = new User();
        user.setLoginName(loginName);
        user.setPassword(password);
        user.setEmailAddress(emailAddress);
        user.setVerified(verified);
        user.setLastAccessTime(new Date());
        user.setRegistrationDate(registrationDate);
        session.saveOrUpdate(user);
        HibernateUtil.commitTransaction();
    }

    public static Session beginTransaction() {
        System.out.println("calling begin transaction");
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        return session;
    }

    public static void commitTransaction() {
        System.out.println("calling commit transaction");
        Session session = HibernateUtil.getSession();
        session.getTransaction().commit();
    }

    public static void rollbackTransaction() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().rollback();
    }

    public static void closeSession() {
        HibernateUtil.getSession().close();
    }

    public static Session getSession() {
        if (sessionFactory == null) {
            Configuration configuration = HibernateUtil.getInitialisedConfiguration();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory.getCurrentSession();
    }

    public static void recreateDatabase() {
        Configuration configuration = HibernateUtil.getInitialisedConfiguration();
        new SchemaExport(configuration).create(true, true);
    }

    public static Configuration getInitialisedConfiguration() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Snafu.class);

        configuration.configure();
        return configuration;
    }
}
