package com.codaconsultancy.model;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class LoadRunner {

    public static void main(String[] args) {
//        LoadRunner.callLoad();
        LoadRunner.callGet();
    }

    private static void callGet() {
        Session session = getSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, 5L);
        System.out.println(user.getPassword());
        session.getTransaction().commit();
    }

    private static void callLoad() {
        Session session = getSession();
        session.beginTransaction();
        try {
            User user = (User) session.load(User.class, 6L);
            System.out.println(user.getPassword());
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
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
