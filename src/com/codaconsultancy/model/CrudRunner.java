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
        // CrudRunner.create();
        CrudRunner.retrieve();
    }

    private static void retrieve() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query queryResult = session.createQuery("from User");

        List<User> users = queryResult.list();


        for (User user : users) {
            System.out.println(user.getPassword());
        }

        session.getTransaction().commit();
    }

    private static void create() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user = new User();
        user.setPassword("abc144");

        session.save(user);
        session.getTransaction().commit();
    }
}
