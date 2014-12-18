package com.codaconsultancy.dao;

import com.codaconsultancy.entities.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class UserDAOImpl extends BaseDAO implements UserDAO {

    @Override
    public User create(User user) {
        if (user.getId() != null && user.getId() != 0) {
            user = null;
        } else {
            user.setLastAccessTime(new Date());
            user.setRegistrationDate(new GregorianCalendar());
            user.setVerified(false);
            save(user);
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean successFlag = true;
        try {
            if (user.getId() == null || user.getId() == 0) {
                successFlag = false;
            } else {
                super.save(user);
            }
        } catch (Throwable th) {
            successFlag = false;
        }
        return successFlag;
    }

    @Override
    public boolean delete(User user) {
        boolean successFlag = true;
        try {
            user.setPassword("");
            super.delete(user);
        } catch (Throwable th) {
            successFlag = false;
        }
        return successFlag;
    }

    @Override
    public User findByPrimaryKey(Long primaryKey) {
        User user = (User) super.findByPrimaryKey(User.class, primaryKey);
        return user;
    }

    @Override
    public List findByExample(User user, boolean fuzzy) {
        List users;
        Criteria criteria = getSession().createCriteria(User.class);
        Example example = Example.create(user);
        if (fuzzy) {
            example.enableLike(MatchMode.ANYWHERE);
            example.ignoreCase();
            example.excludeZeroes();
        }
        criteria.add(example);
        users = criteria.list();
        return users;
    }

    @Override
    public List findAll() {
        Query query = getSession().createQuery("from User");
        return query.list();
    }
}
