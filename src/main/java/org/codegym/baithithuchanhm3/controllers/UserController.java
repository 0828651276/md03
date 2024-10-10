package org.codegym.baithithuchanhm3.controllers;

import org.codegym.baithithuchanhm3.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserController", urlPatterns = {"/admin/users/*"})
public class UserController extends BaseController {
    private UserService userService;


    @Override
    public void init() throws ServletException {
        userService = new UserService();
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
                    userService.showPageUserList(req, resp);
                    break;
                case "/create":
                    userService.showPageAddUser(req, resp);
                    break;
                case "/update":
                    userService.showPageUpdateUser(req, resp);
                    break;
                case "/delete":
                    userService.deleteUser(req, resp);
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
                    userService.createUser(req, resp);
                    break;
                case "/update":
                    userService.updateUser(req, resp);
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