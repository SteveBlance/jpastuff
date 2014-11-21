package com.codaconsultancy.model;

import org.hibernate.Query;
import org.hibernate.Session;

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
        Session session = HibernateUtil.beginTransaction();

        Query queryResult = session.createQuery("from User");

        List<User> allUsers = queryResult.list();

        for (User user : allUsers) {
            session.delete(user);
        }
        HibernateUtil.commitTransaction();
    }

    private static void updateAll() {
        Session session = HibernateUtil.beginTransaction();

        Query queryResult = session.createQuery("from User");

        List<User> allUsers = queryResult.list();

        for (User user : allUsers) {
            user.setPassword("password");
            session.update(user);
        }
        HibernateUtil.commitTransaction();
    }

    private static void retrieveById(Long id) {
        Session session = HibernateUtil.beginTransaction();

        Query queryResult = session.createQuery("from User where id = :id");
        queryResult.setLong("id", id);

        User user = (User) queryResult.uniqueResult();

        System.out.println(user.getPassword());
        HibernateUtil.commitTransaction();
    }

    private static void retrieve() {
        Session session = HibernateUtil.beginTransaction();

        Query queryResult = session.createQuery("from User");

        List<User> users = queryResult.list();

        for (User user : users) {
            System.out.println(user.getPassword());
        }

        HibernateUtil.commitTransaction();
    }

    private static void create() {
        Session session = HibernateUtil.beginTransaction();

        User user = new User();
        user.setPassword("abc160");

        session.save(user);
        HibernateUtil.commitTransaction();
    }

}
