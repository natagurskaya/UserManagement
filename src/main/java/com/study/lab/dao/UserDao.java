package com.study.lab.dao;

import com.study.lab.entity.User;
import java.util.List;

public interface UserDao {

    List<User> getAll();

    void delete(User user);

    void add(User user);

    void update(User user);

    User getById(Integer id);
}
