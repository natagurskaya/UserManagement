package com.gurskaya.userManagement.service.userServiceImpl;

import com.gurskaya.userManagement.dao.UserDao;
import com.gurskaya.userManagement.entity.User;

import java.util.List;

public class UserService implements com.gurskaya.userManagement.service.UserService {

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
    public User getById(int id) {
        return userDao.getById(id);
    }
}
