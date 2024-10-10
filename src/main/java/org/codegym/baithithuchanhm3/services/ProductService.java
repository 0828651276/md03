package org.codegym.baithithuchanhm3.services;

import org.codegym.baithithuchanhm3.enties.Product;
import org.codegym.baithithuchanhm3.models.ProductModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private ProductModel productModel;

    public ProductService() {
        this.productModel = new ProductModel();
    }

    public void showPageProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String keyword = request.getParameter("keyword");
        ResultSet data = null;
        int totalItemOfPage = 5;

        String page = request.getParameter("page");
        int offset = 0;
        if (page != null) {
            offset = totalItemOfPage * (Integer.parseInt(page) - 1);
        }

        if (keyword != null) {
            data = ProductModel.findProductByProductName(keyword, totalItemOfPage, offset);
        } else {
            // get all users from model
            data = ProductModel.getAllProduct(totalItemOfPage, offset);
        }

        List<Product> products = new ArrayList<>();

        while (data.next()) {
            int id = data.getInt("product_id");
            String name = data.getString("product_name");
            String discription = data.getString("description");
            int inventory = data.getInt("inventory");
            int price = data.getInt("price");
            String imageUrl = data.getString("image_url");
            Product product = new Product(id, name, discription, inventory, price, imageUrl);
            products.add(product);
        }
        // Tinh so luong trang
        int totalProduct = productModel.getTotalProduct();
        if (keyword != null) {
            totalProduct = products.size();
        }
        int totalPage = totalProduct % totalItemOfPage == 0 ? totalProduct / totalItemOfPage : totalProduct / totalItemOfPage + 1;

        // set data vao request de truyen xuong jsp
        request.setAttribute("data", products);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", page);

        // forward request to list.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/products/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productModel.deleteProduct(id);
        response.sendRedirect(request.getContextPath() + "/admin/products");
    }

    public void showPageAddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // forward request to list.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/products/create.jsp");
        requestDispatcher.forward(request, response);
    }

    public void showPageUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // get user update tu database
        int id = Integer.parseInt(request.getParameter("id"));
        Product productUpdate = this.getProductById(id);
        // forward request to list.jsp
        request.setAttribute("product", productUpdate);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/products/update.jsp");
        requestDispatcher.forward(request, response);
    }

    public void createProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // get data from form
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int inventory = Integer.parseInt(request.getParameter("inventory"));
        int price = Integer.parseInt(request.getParameter("price"));
        String imageUrl = request.getParameter("imageUrl");
        Product product = new Product(name, description, inventory, price, imageUrl);
        productModel.addProduct(product);
        // redirect to list user page
        response.sendRedirect(request.getContextPath() + "/admin/products");
    }

    public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // get data from form
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int inventory = Integer.parseInt(request.getParameter("inventory"));
        int price = Integer.parseInt(request.getParameter("price"));
        String imageUrl = request.getParameter("imageUrl");
        Product product = new Product(id, name, description, inventory, price, imageUrl);
        productModel.updateProduct(product);
        // redirect to list user page
        response.sendRedirect(request.getContextPath() + "/admin/products");
    }

    public Product getProductById(int id) throws SQLException {
        ResultSet resultSet = productModel.findProductById(id);
        Product product = null;
        while (resultSet.next()) {
            String name = resultSet.getString("product_name");
            String description = resultSet.getString("description");
            int inventory = resultSet.getInt("inventory");
            int price = resultSet.getInt("price");
            String imageUrl = resultSet.getString("image_url");
            product = new Product(id, name, description, inventory, price, imageUrl);
        }
        return product;
    }
}
