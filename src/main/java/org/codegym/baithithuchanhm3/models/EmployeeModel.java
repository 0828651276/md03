package org.codegym.baithithuchanhm3.models;

import org.codegym.baithithuchanhm3.enties.Employee;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    private List<Employee> list;
    private static Connection connection;

    public EmployeeModel() {
        list = new ArrayList<>();
        connection = Database.getConnection();
    }

    public static ResultSet getAllEmployee(int limit, int offset) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM employees LIMIT ? Offset ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the du lieu vao cau lenh
        statement.setInt(1, limit);
        statement.setInt(2, offset);
        // thu hien truy van
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public int getTotalEmployee() throws SQLException {
        // viet sql
        String sql = "SELECT COUNT(*) as totalEmployee FROM employees";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thu hien truy van
        ResultSet resultSet = statement.executeQuery();
        int totalEmployee = 0;
        while (resultSet.next()) {
            totalEmployee = resultSet.getInt("totalEmployee");
        }
        return totalEmployee;
    }

    public void deleteEmployee(int id) throws SQLException {
        // viet sql
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the id vao cau lenh
        statement.setInt(1, id);
        statement.execute();
    }

    public void addEmployee(Employee employee) throws SQLException {
        // viet sql
        String sql = "INSERT INTO employees (name, email, phone) VALUES (?,?,?)";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the du lieu vao cau lenh
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getEmail());
        statement.setInt(3, employee.getPhone());
        statement.execute();
    }

    public void updateEmployee(Employee employee) throws SQLException {
        // viet sql
        String sql = "UPDATE employees SET name=?, email=?, phone=? WHERE employee_id=?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the du lieu vao cau lenh
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getEmail());
        statement.setInt(3, employee.getPhone());
        statement.setInt(4, employee.getId());
        statement.execute();
    }

    public ResultSet findEmployeeById(int id) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the id vao cau lenh
        statement.setInt(1, id);
        // thu hien truy van
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public static ResultSet findEmployeeByEmployeeName(String name, int limit, int offset) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM employees WHERE name LIKE ? LIMIT ? offset ?";
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

