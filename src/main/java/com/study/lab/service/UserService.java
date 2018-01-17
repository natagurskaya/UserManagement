package com.study.lab.service;

import com.study.lab.ServiceLocator;
import com.study.lab.dao.UserDao;
import com.study.lab.entity.User;

import java.util.List;

public class UserService implements UserDao {

    private UserDao userDao = ServiceLocator.get(UserDao.class);

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
