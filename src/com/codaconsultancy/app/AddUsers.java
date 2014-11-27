package com.codaconsultancy.app;

import com.codaconsultancy.dao.UseDAOImpl;
import com.codaconsultancy.dao.UserDAO;
import com.codaconsultancy.entities.User;
import com.codaconsultancy.model.HibernateUtil;
import org.hibernate.HibernateException;

import java.util.Scanner;

public class AddUsers {

    public static void main(String[] args) {
        int keepAdding = 1;
        UserDAO userDAO = new UseDAOImpl();
        while (keepAdding == 1) {
            Scanner keyboard = new Scanner(System.in);
            User user = new User();
            System.out.println("What is the user's login name?");
            user.setLoginName(keyboard.next());
            System.out.println("What is the email address?");
            user.setEmailAddress(keyboard.next());
            System.out.println("What is the password?");
            user.setPassword(keyboard.next());
            try {
                HibernateUtil.beginTransaction();
                userDAO.create(user);
                HibernateUtil.commitTransaction();
            } catch (HibernateException e) {
                e.printStackTrace();
                System.out.println("Database Insert Failed");
                System.out.println(e.getClass() + e.getMessage());
                HibernateUtil.rollbackTransaction();
            }
            System.out.println("Would you like to continue? (1=y / 0=n)");
            keepAdding = keyboard.nextInt();
        }
    }
}
