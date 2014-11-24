package com.codaconsultancy.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import java.util.List;

public class CriteriaQuery {

    public static void main(String[] args) {
//       findVerifiedUsers();
//        findUniqueUser();
//        findUsingLike();
        findFirstFive();
    }

    private static void findFirstFive() {
        Session session = HibernateUtil.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.setFirstResult(0);
        criteria.setMaxResults(5);
        List list = criteria.list();
        for (Object aList : list) {
            System.out.println(aList.toString());
        }
    }

    private static void findUsingLike() {
        User user = new User();
        user.setEmailAddress(".com");
        Example example = Example.create(user);
        example.enableLike(MatchMode.END);
        Session session = HibernateUtil.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(example);
        List list = criteria.list();
        for (Object aList : list) {
            System.out.println(aList.toString());
        }

    }

    private static void findUniqueUser() {
        User user = new User();
        user.setLoginName("mario");
        user.setPassword("pass");
        Example example = Example.create(user);
        Session session = HibernateUtil.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(example);
        User foundUser = (User) criteria.uniqueResult();
        System.out.println(foundUser.toString());
    }

    private static void findVerifiedUsers() {
        User user = new User();
        user.setVerified(Boolean.TRUE);
        Example example = Example.create(user);
        Session session = HibernateUtil.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(example);
        List results = criteria.list();
        for (Object result : results) {
            System.out.println(result.toString());
        }

    }
}
