package com.codaconsultancy.dao;

import com.codaconsultancy.model.User;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class UseDAOImpl extends BaseDAO implements UserDAO {

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
                save(user);
            }
        } catch (Throwable th) {
            successFlag = false;
        }
        return successFlag;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public User findByPrimaryKey(Long primaryKey) {
        return null;
    }

    @Override
    public User findByExample(User user, boolean fuzzy) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
