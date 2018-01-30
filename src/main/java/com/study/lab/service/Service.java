package com.study.lab.service;

import com.study.lab.entity.User;

import java.util.List;

public interface Service {

    List<User> getAll();

    void delete(User user);

    void add(User user);

}
