package com.gurskaya.userManagement.dao;

import com.gurskaya.userManagement.entity.User;
import java.util.List;

public interface UserDao {

    List<User> getAll();

    void delete(User user);

    void add(User user);

    void update(User user);

    User getById(int id);
}
