package com.codaconsultancy.model;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

public class CrudRunner {

    public static void main(String[] args) {
        CrudRunner.create();
//        CrudRunner.retrieve();
//        CrudRunner.retrieveById(1L);
//        CrudRunner.updateAll();
//        CrudRunner.deleteAll();

    }

    private static void deleteAll() {
        Session session = getSession();
        session.beginTransaction();

        Query queryResult = session.createQuery("from User");

        List<User> allUsers = queryResult.list();

        for (User user : allUsers) {
            session.delete(user);
        }
        session.getTransaction().commit();
    }

    private static void updateAll() {
        Session session = getSession();
        session.beginTransaction();

        Query queryResult = session.createQuery("from User");

        List<User> allUsers = queryResult.list();

        for (User user : allUsers) {
            user.setPassword("password");
            session.update(user);
        }
        session.getTransaction().commit();
    }

    private static void retrieveById(Long id) {
        Session session = getSession();
        session.beginTransaction();

        Query queryResult = session.createQuery("from User where id = :id");
        queryResult.setLong("id", id);

        User user = (User) queryResult.uniqueResult();

        System.out.println(user.getPassword());
        session.getTransaction().commit();
    }

    private static void retrieve() {
        Session session = getSession();
        session.beginTransaction();

        Query queryResult = session.createQuery("from User");

        List<User> users = queryResult.list();

        for (User user : users) {
            System.out.println(user.getPassword());
        }

        session.getTransaction().commit();
    }

    private static void create() {
        Session session = getSession();
        session.beginTransaction();

        User user = new User();
        user.setPassword("abc160");

        session.save(user);
        session.getTransaction().commit();
    }

    private static Session getSession() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory.getCurrentSession();
    }
}
