package com.codaconsultancy.model;

import com.codaconsultancy.entities.User;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

public class LoadRunner {

    public static void main(String[] args) {
//        LoadRunner.callLoad();
        LoadRunner.callGet();
    }

    private static void callGet() {
        Session session = HibernateUtil.beginTransaction();
        User user = (User) session.get(User.class, 5L);
        System.out.println(user.getPassword());
        HibernateUtil.commitTransaction();
    }

    private static void callLoad() {
        Session session = HibernateUtil.beginTransaction();
        try {
            User user = (User) session.load(User.class, 6L);
            System.out.println(user.getPassword());
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        HibernateUtil.commitTransaction();
    }

}
