package com.gurskaya.userManagement.service;

import com.gurskaya.userManagement.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    void delete(User user);

    void add(User user);

    void update(User user);

    User getById(int id);
}
