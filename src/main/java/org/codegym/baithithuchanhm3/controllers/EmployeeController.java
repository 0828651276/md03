package org.codegym.baithithuchanhm3.controllers;

import org.codegym.baithithuchanhm3.services.EmployeeService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EmployeeController", urlPatterns = {"/admin/employees/*"})
public class EmployeeController extends BaseController {
    private EmployeeService employeeService;


    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService();
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
                    employeeService.showPageEmployeeList(req, resp);
                    break;
                case "/create":
                    employeeService.showPageAddEmployee(req, resp);
                    break;
                case "/update":
                    employeeService.showPageUpdateEmployee(req, resp);
                    break;
                case "/delete":
                    employeeService.deleteEmployee(req, resp);
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
                    employeeService.createEmployee(req, resp);
                    break;
                case "/update":
                    employeeService.updateEmployee(req, resp);
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
