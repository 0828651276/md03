package org.codegym.baithithuchanhm3.models;

import org.codegym.baithithuchanhm3.enties.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private List<Product> list;
    private static Connection connection;

    public ProductModel() {
        list = new ArrayList<>();
        connection = Database.getConnection();
    }

    public static ResultSet getAllProduct(int limit, int offset) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM products LIMIT ? Offset ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the du lieu vao cau lenh
        statement.setInt(1, limit);
        statement.setInt(2, offset);
        // thu hien truy van
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public int getTotalProduct() throws SQLException {
        // viet sql
        String sql = "SELECT COUNT(*) as totalProduct FROM products";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thu hien truy van
        ResultSet resultSet = statement.executeQuery();
        int totalProduct = 0;
        while (resultSet.next()) {
            totalProduct = resultSet.getInt("totalProduct");
        }
        return totalProduct;
    }

    public void deleteProduct(int id) throws SQLException {
        // viet sql
        String sql = "DELETE FROM products WHERE product_id = ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the id vao cau lenh
        statement.setInt(1, id);
        statement.execute();
    }

    public void addProduct(Product product) throws SQLException {
        // viet sql
        String sql = "INSERT INTO products (product_name, description, inventory, price, image_url ) VALUES (?,?,?,?,?)";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the du lieu vao cau lenh
        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setInt(3, product.getInventory());
        statement.setInt(4, product.getPrice());
        statement.setString(5, product.getImageUrl());
        statement.execute();
    }

    public void updateProduct(Product product) throws SQLException {
        // viet sql
        String sql = "UPDATE products SET product_name=?, description=?, inventory=?, price=?, image_url=? WHERE product_id=?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the du lieu vao cau lenh
        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setInt(3, product.getInventory());
        statement.setInt(4, product.getPrice());
        statement.setString(5, product.getImageUrl());
        statement.setInt(6, product.getId());
        statement.execute();
    }

    public ResultSet findProductById(int id) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM products WHERE product_id = ?";
        // dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        // thay the id vao cau lenh
        statement.setInt(1, id);
        // thu hien truy van
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public static ResultSet findProductByProductName(String name, int limit, int offset) throws SQLException {
        // viet sql
        String sql = "SELECT * FROM products WHERE product_name LIKE ? LIMIT ? offset ?";
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
