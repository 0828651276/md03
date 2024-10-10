package org.codegym.baithithuchanhm3.services;

import org.codegym.baithithuchanhm3.enties.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthService extends BaseService {
    public void renderPageLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        request.setAttribute("error", session.getAttribute("passwordError"));
        getTemplate("/views/auth/login.jsp", request, response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember-me");
        HttpSession session = request.getSession(true);
        //Xu li logic
        //validate email and password

        UserService userService = new UserService();
        User user = userService.getUserByNameAndPassword(name, password);

        if (user == null) {
            session.setAttribute("passwordError", "Invalid name or password");
            response.sendRedirect("/admin/login");
            return;
        }

        // Đăng nhập thành công, lưu thông tin người dùng vào session
        session.setAttribute("loggedInUser", user);

        // Xử lý "Remember me" nếu được chọn
        if ("on".equals(remember)) {
            Cookie rememberCookie = new Cookie("user", user.getPassword());
            rememberCookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
            response.addCookie(rememberCookie);
        }

        // Chuyển hướng đến trang dashboard sau khi đăng nhập thành công
        response.sendRedirect("/admin/users");
    }
}




