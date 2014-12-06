package com.codaconsultancy.model;

import com.codaconsultancy.entities.*;
import com.codaconsultancy.entities.keys.CompoundKey;
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

    public static void main(String[] args) {
        HibernateUtil.recreateDatabase();
        setupUsers();
        setupSnafu();
        setupFooBar();
        setupThing();
        setupInterest();
        setupFracture();
        setupPrison();
    }

    private static void setupPrison() {
        Prison prison = new Prison();
        prison.setCity("Dunfermline");
        long userId = 18L;
        long bankId = 85L;
        CompoundKey key = new CompoundKey(userId, bankId);
        prison.setKey(key);
        HibernateUtil.beginTransaction();
        getSession().save(prison);
        HibernateUtil.commitTransaction();
    }

    private static void setupFracture() {
        Fracture fracture = new Fracture();
        fracture.setBankId(8L);
        fracture.setUserId(21L);
        fracture.setBone("arm");
        HibernateUtil.beginTransaction();
        getSession().save(fracture);
        HibernateUtil.commitTransaction();
    }

    private static void setupInterest() {
        Interest interest = new Interest();
        interest.setRate(18.5);
        Long wayne = 6L;
        Long bankOfScotland = 1665L;
        CompoundKey key = new CompoundKey(wayne, bankOfScotland);
        interest.setId(key);
        HibernateUtil.beginTransaction();
        getSession().save(interest);
        HibernateUtil.commitTransaction();
    }

    private static void setupThing() {
        HibernateUtil.beginTransaction();
        ThingDetail thingDetail = new ThingDetail();
        thingDetail.setAlias("Joey Shabidoo");
        thingDetail.setCount(10);
        Thing thing = new Thing();
        thing.setName("Homer");
        thing.setThingDetail(thingDetail);
        getSession().save(thing);
        HibernateUtil.commitTransaction();
    }

    private static void setupFooBar() {
        HibernateUtil.beginTransaction();
        FooBar fooBar = new FooBar();
        fooBar.setBarCode("90210");
        fooBar.setFooName("ManChu");
        getSession().save(fooBar);
        HibernateUtil.commitTransaction();
    }

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
        configuration.addAnnotatedClass(FooBar.class);
        configuration.addAnnotatedClass(Thing.class);
        configuration.addAnnotatedClass(Interest.class);
        configuration.addAnnotatedClass(Fracture.class);
        configuration.addAnnotatedClass(Prison.class);

        configuration.configure();
        return configuration;
    }
}
