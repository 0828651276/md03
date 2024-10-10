package org.codegym.baithithuchanhm3.models;

import org.codegym.baithithuchanhm3.enties.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private List<User> list;
    private Connection connection;

    public UserModel() {
        list = new ArrayList<>();
        connection = Database.getConnection();
    }

    public ResultSet getAllUser(int limit, int offset) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM users LIMIT ? Offset ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the du lieu vao cau lenh
        statement.setInt(1, limit);
        statement.setInt(2, offset);
        // thu hien truy van
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public int getTotalUser() throws SQLException {
        // viet sql
        String sql = "SELECT COUNT(*) as totalUser FROM users";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thu hien truy van
        ResultSet resultSet = statement.executeQuery();
        int totalUser = 0;
        while (resultSet.next()) {
            totalUser = resultSet.getInt("totalUser");
        }
        return totalUser;
    }

    public void deleteUser(int id) throws SQLException {
        // viet sql
        String sql = "DELETE FROM users WHERE id = ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the id vao cau lenh
        statement.setInt(1, id);
        statement.execute();
    }

    public void addUser(User user) throws SQLException {
        // viet sql
        String sql = "INSERT INTO users (username, password, role) VALUES (?,?,?)";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the du lieu vao cau lenh
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getRole());
        statement.execute();
    }

    public void updateUser(User user) throws SQLException {
        // viet sql
        String sql = "UPDATE users SET username=?, password=?, role=? WHERE id=?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the du lieu vao cau lenh
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getRole());
        statement.setInt(4, user.getId());
        statement.execute();
    }

    public ResultSet findUserById(int id) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM users WHERE id = ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the id vao cau lenh
        statement.setInt(1, id);
        // thu hien truy van
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public ResultSet findUserByUserName(String username, int limit, int offset) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM users WHERE username LIKE ? LIMIT ? offset ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the id vao cau lenh
        statement.setString(1, "%" + username + "%");
        statement.setInt(2, limit);
        statement.setInt(3, offset);
        // thu hien truy van
        return statement.executeQuery();
    }

    public ResultSet findUserByNameAndPassword(String username, String password) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the id vao cau lenh
        statement.setString(1, username);
        statement.setString(2, password);
        // thu hien truy van
        return statement.executeQuery();
    }
}
