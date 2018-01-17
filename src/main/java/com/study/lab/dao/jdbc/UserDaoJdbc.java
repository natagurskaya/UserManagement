package com.study.lab.dao.jdbc;

import com.study.lab.dao.UserDao;
import com.study.lab.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private static final String URL = "jdbc:mysql://localhost:3306/userpaymentservice";
    private static final String USERNAME = "root";
    private static final String PASS = "root123";

    public static final String SQL_GET_ALL = "select id, firstName, lastName, payment from user";

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();

        try (Connection connection = connectToDatabase();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPayment(resultSet.getDouble("payment"));
                result.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot create connection!", e);
        }
        return result;
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

    private static Connection connectToDatabase() {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Driver is not found", e);
        }
        return connection;
    }
}
