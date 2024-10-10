package org.codegym.baithithuchanhm3.models;

import org.codegym.baithithuchanhm3.enties.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    private List<Customer> list;
    private static Connection connection;

    public CustomerModel() {
        list = new ArrayList<>();
        connection = Database.getConnection();
    }

    public static ResultSet getAllCustomer(int limit, int offset) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM customers LIMIT ? Offset ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the du lieu vao cau lenh
        statement.setInt(1, limit);
        statement.setInt(2, offset);
        // thu hien truy van
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public int getTotalCustomer() throws SQLException {
        // viet sql
        String sql = "SELECT COUNT(*) as totalCustomer FROM customers";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thu hien truy van
        ResultSet resultSet = statement.executeQuery();
        int totalCustomer = 0;
        while (resultSet.next()) {
            totalCustomer = resultSet.getInt("totalCustomer");
        }
        return totalCustomer;
    }

    public void deleteCustomer(int id) throws SQLException {
        // viet sql
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the id vao cau lenh
        statement.setInt(1, id);
        statement.execute();
    }

    public void addCustomer(Customer customer) throws SQLException {
        // viet sql
        String sql = "INSERT INTO customers (customer_name, email, address,phone ) VALUES (?,?,?,?)";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the du lieu vao cau lenh
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getAddress());
        statement.setString(3, customer.getEmail());
        statement.setString(4, customer.getPhone());
        statement.execute();
    }

    public void updateCustomer(Customer customer) throws SQLException {
        // viet sql
        String sql = "UPDATE customers SET customer_name=?, email=?, address=?, phone=? WHERE customer_id=?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the du lieu vao cau lenh
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getAddress());
        statement.setString(3, customer.getEmail());
        statement.setString(4, customer.getPhone());
        statement.setInt(5, customer.getId());
        statement.execute();
    }

    public ResultSet findCustomerById(int id) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the id vao cau lenh
        statement.setInt(1, id);
        // thu hien truy van
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public static ResultSet findCustomerByCustomerName(String name, int limit, int offset) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM customers WHERE customer_name LIKE ? LIMIT ? offset ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the id vao cau lenh
        statement.setString(1, "%" + name + "%");
        statement.setInt(2, limit);
        statement.setInt(3, offset);
        // thu hien truy van
        return statement.executeQuery();
    }

}

