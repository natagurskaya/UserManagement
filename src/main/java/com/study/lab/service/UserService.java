package com.study.lab.service;

import com.study.lab.dao.UserDao;
import com.study.lab.entity.User;

import java.util.List;

public class UserService implements Service {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }
}
