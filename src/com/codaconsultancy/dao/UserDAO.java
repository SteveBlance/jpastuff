package com.codaconsultancy.dao;

import com.codaconsultancy.model.User;

import java.util.List;

public interface UserDAO {

    public User create(User user);

    public boolean update(User user);

    public boolean delete(User user);

    public User findByPrimaryKey(Long primaryKey);

    public User findByExample(User user, boolean fuzzy);

    public List<User> findAll();
}
