package org.codegym.baithithuchanhm3.services;

import org.codegym.baithithuchanhm3.enties.User;
import org.codegym.baithithuchanhm3.models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserService {

    private UserModel userModel;

    public UserService() {
        this.userModel = new UserModel();
    }

    public void showPageUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String keyword = request.getParameter("keyword");
        ResultSet data = null;
        int totalItemOfPage = 5;

        String page = request.getParameter("page");
        int offset = 0;
        if (page != null) {
            offset = totalItemOfPage * (Integer.parseInt(page) - 1);
        }

        if (keyword != null) {
            data = userModel.findUserByUserName(keyword, totalItemOfPage, offset);
        } else {
            // get all users from model
            data = userModel.getAllUser(totalItemOfPage, offset);
        }

        List<User> users = new ArrayList<>();

        while (data.next()){
            int id = data.getInt("id");
            String username = data.getString("username");
            String password = data.getString("password");
            String role = data.getString("role");
            User user = new User(id, username, password, role);
            users.add(user);
        }
        // Tinh so luong trang
        int totalUser = userModel.getTotalUser();
        if (keyword != null) {
            totalUser = users.size();
        }
        int totalPage = totalUser % totalItemOfPage == 0 ? totalUser / totalItemOfPage : totalUser / totalItemOfPage + 1;

        // set data vao request de truyen xuong jsp
        request.setAttribute("data", users);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", page);

        // forward request to list.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/users/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userModel.deleteUser(id);
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }

    public void showPageAddUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // forward request to list.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/users/create.jsp");
        requestDispatcher.forward(request, response);
    }

    public void showPageUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // get user update tu database
        int id = Integer.parseInt(request.getParameter("id"));
        User userUpdate = this.getUserById(id);
        // forward request to list.jsp
        request.setAttribute("user", userUpdate);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/users/update.jsp");
        requestDispatcher.forward(request, response);
    }

    public void createUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // get data from form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = new User(username, password, role);
        userModel.addUser(user);
        // redirect to list user page
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // get data from form
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = new User(id, username, password, role);
        userModel.updateUser(user);
        // redirect to list user page
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }

    public User getUserById(int id) throws SQLException {
        ResultSet resultSet = userModel.findUserById(id);
        User user = null;
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            user = new User(id, username, password, role);
        }
        return user;
    }

    public User getUserByNameAndPassword(String username, String password) {
        User user = null;
        try {
            ResultSet resultSet = userModel.findUserByNameAndPassword(username, password);
            while (resultSet.next()) {
                String role = resultSet.getString("role");
                int id = resultSet.getInt("id");
                user = new User(id, username, password, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}