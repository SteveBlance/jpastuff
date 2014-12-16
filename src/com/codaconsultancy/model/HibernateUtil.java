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
        setupAncestor();
        setupExam();
        setupTeam();
        enrollStudents();
    }

    private static void enrollStudents() {
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        student1.setName("Bob");
        student2.setName("Zac");
        student3.setName("Liz");
        Course maths101 = new Course();
        maths101.setCourseCode("M101");
        Course french201 = new Course();
        french201.setCourseCode("F201");
        maths101.enroleStudent(student1);
        maths101.enroleStudent(student2);
        french201.enroleStudent(student1);
        french201.enroleStudent(student3);

        HibernateUtil.beginTransaction();
        getSession().save(student1);
        getSession().save(student2);
        getSession().save(student3);
        getSession().save(maths101);
        getSession().save(french201);

        HibernateUtil.commitTransaction();
    }

    private static void setupTeam() {
        Team team = new Team();
        team.setName("The Pars");
        Player player1 = new Player();
        Player player2 = new Player();
        HibernateUtil.beginTransaction();
        getSession().save(team);
        getSession().save(player1);
        getSession().save(player2);

        player1.setNickname("Kozzie");
        player1.setTeam(team);
        player2.setNickname("Gozie");
        player2.setTeam(team);

        HibernateUtil.commitTransaction();
    }

    private static void setupExam() {
        Exam exam = new Exam();
        exam.setShortName("OCPJP");
        ExamDetail detail = new ExamDetail();
        detail.setFullName("Oracle Certified Professional - Java Programmer");
        detail.setNumberOfQuestions(80);
        detail.setPassingPercentage(65);
        exam.setDetail(detail);
        HibernateUtil.beginTransaction();
        getSession().save(exam);
        HibernateUtil.commitTransaction();
    }

    private static void setupAncestor() {
        Ancestor ancestor = new Ancestor();
        ancestor.setNationality("Scottish");

        Parent parent = new Parent();
        parent.setNationality("Irish");
        parent.setLastName("McKenzie");

        Child child = new Child();
        child.setNationality("Welsh");
        child.setFirstName("Ivor");
        child.setLastName("Jones");

        HibernateUtil.beginTransaction();
        getSession().save(ancestor);
        getSession().save(parent);
        getSession().save(child);
        HibernateUtil.commitTransaction();
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
        configuration.addAnnotatedClass(Ancestor.class);
        configuration.addAnnotatedClass(Parent.class);
        configuration.addAnnotatedClass(Child.class);
        configuration.addAnnotatedClass(Exam.class);
        configuration.addAnnotatedClass(ExamDetail.class);
        configuration.addAnnotatedClass(Team.class);
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Course.class);


        configuration.configure();
        return configuration;
    }
}
