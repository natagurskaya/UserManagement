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

    private static final String SQL_GET_ALL = "select id, firstName, lastName, payment from user";
    private static final String SQL_DELETE = "delete from user where id = ?";
    private static final String SQL_ADD = "insert into user (firstName, lastName, payment) values(?, ?, ?)";

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
            throw new RuntimeException("Users can't be shown", e);
        }
        return result;
    }

    @Override
    public void delete(User user) {
        try (Connection connection = connectToDatabase()) {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot delete user!", e);
        }
    }

    @Override
    public void add(User user) {
        try (Connection connection = connectToDatabase();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDouble(3, user.getPayment());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot add user!", e);
        }
    }

    private static Connection connectToDatabase() throws SQLException {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(URL, USERNAME, PASS);
        return connection;
    }
}
