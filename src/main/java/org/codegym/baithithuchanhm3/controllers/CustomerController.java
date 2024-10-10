package org.codegym.baithithuchanhm3.controllers;

import org.codegym.baithithuchanhm3.services.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CustomerController", urlPatterns = {"/admin/customers/*"})
public class CustomerController extends BaseController {
    private CustomerService customerService;


    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getPathInfo();
        if (url == null) {
            url = "/";
        }
        try {
            switch (url) {
                case "/":
                    customerService.showPageCustomerList(req, resp);
                    break;
                case "/create":
                    customerService.showPageAddCustomer(req, resp);
                    break;
                case "/update":
                    customerService.showPageUpdateCustomer(req, resp);
                    break;
                case "/delete":
                    customerService.deleteCustomer(req, resp);
                    break;

                default:
                    renderPage404(req, resp);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        if (url == null) {
            url = "/";
        }

        try {
            switch (url) {
                case "/create":
                    customerService.createCustomer(req, resp);
                    break;
                case "/update":
                    customerService.updateCustomer(req, resp);
                    break;
                default:
                    renderPage404(req, resp);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
