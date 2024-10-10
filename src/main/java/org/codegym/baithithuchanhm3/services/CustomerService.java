package org.codegym.baithithuchanhm3.services;

import org.codegym.baithithuchanhm3.enties.Customer;
import org.codegym.baithithuchanhm3.models.CustomerModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerService {

    private CustomerModel customerModel;

    public CustomerService() {
        this.customerModel = new CustomerModel();
    }

    public void showPageCustomerList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String keyword = request.getParameter("keyword");
        ResultSet data = null;
        int totalItemOfPage = 5;

        String page = request.getParameter("page");
        int offset = 0;
        if (page != null) {
            offset = totalItemOfPage * (Integer.parseInt(page) - 1);
        }

        if (keyword != null) {
            data = CustomerModel.findCustomerByCustomerName(keyword, totalItemOfPage, offset);
        } else {
            // get all users from model
            data = CustomerModel.getAllCustomer(totalItemOfPage, offset);
        }

        List<Customer> customers = new ArrayList<>();

        while (data.next()) {
            int id = data.getInt("customer_id");
            String name = data.getString("customer_name");
            String email = data.getString("email");
            String address = data.getString("address");
            String phone = data.getString("phone");
            Customer customer = new Customer(id, name, email, address, phone);
            customers.add(customer);
        }
        // Tinh so luong trang
        int totalCustomer = customerModel.getTotalCustomer();
        if (keyword != null) {
            totalCustomer = customers.size();
        }
        int totalPage = totalCustomer % totalItemOfPage == 0 ? totalCustomer / totalItemOfPage : totalCustomer / totalItemOfPage + 1;

        // set data vao request de truyen xuong jsp
        request.setAttribute("data", customers);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", page);

        // forward request to list.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/customers/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        customerModel.deleteCustomer(id);
        response.sendRedirect(request.getContextPath() + "/admin/customers");
    }

    public void showPageAddCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // forward request to list.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/customers/create.jsp");
        requestDispatcher.forward(request, response);
    }

    public void showPageUpdateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // get user update tu database
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customerUpdate = this.getCustomerById(id);
        // forward request to list.jsp
        request.setAttribute("customer", customerUpdate);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/customers/update.jsp");
        requestDispatcher.forward(request, response);
    }

    public void createCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // get data from form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        Customer customer = new Customer(name, email, address, phone);
        customerModel.addCustomer(customer);
        // redirect to list user page
        response.sendRedirect(request.getContextPath() + "/admin/customers");
    }

    public void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // get data from form
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        Customer customer = new Customer(id, name, email, address, phone);
        customerModel.updateCustomer(customer);
        // redirect to list user page
        response.sendRedirect(request.getContextPath() + "/admin/customers");
    }

    public Customer getCustomerById(int id) throws SQLException {
        ResultSet resultSet = customerModel.findCustomerById(id);
        Customer customer = null;
        while (resultSet.next()) {
            String name = resultSet.getString("customer_name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            customer = new Customer(id, name, email, address, phone);
        }
        return customer;
    }
}

