package com.study.lab.dao;

import com.study.lab.entity.User;
import java.util.List;

public interface UserDao {

    List<User> getAll();

    void save(User user);

    void update(User user);

    void delete(User user);
}
