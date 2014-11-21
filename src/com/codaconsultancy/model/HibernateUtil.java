package com.codaconsultancy.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        HibernateUtil.recreateDatabase();
    }

    public static Session beginTransaction() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        return session;
    }

    public static void commitTransaction() {
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

        configuration.configure();
        return configuration;
    }
}
