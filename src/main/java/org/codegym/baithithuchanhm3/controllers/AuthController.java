package org.codegym.baithithuchanhm3.controllers;

import org.codegym.baithithuchanhm3.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="AuthController", urlPatterns = "/admin/*")
public class AuthController extends BaseController {
    private AuthService authService;
    @Override
    public void init() throws ServletException {
        // Initialize AuthService
        authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getPathInfo();
        if (uri == null ) {
            renderPage404(request, response);
        } else {
            switch (uri) {
                case "/login":
                    authService.renderPageLogin(request, response);
                    break;
                default:
                    renderPage404(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getPathInfo();
        if (uri == null ) {
            renderPage404(request, response);
        } else {
            switch (uri) {
                case "/login":
                    authService.login(request, response);
                    break;
                default:
                    renderPage404(request, response);
                    break;
            }
        }
    }
}
