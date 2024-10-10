package org.codegym.baithithuchanhm3.services;





import org.codegym.baithithuchanhm3.enties.Employee;
import org.codegym.baithithuchanhm3.models.EmployeeModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private EmployeeModel employeeModel;

    public EmployeeService() {
        this.employeeModel = new EmployeeModel();
    }

    public void showPageEmployeeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String keyword = request.getParameter("keyword");
        ResultSet data = null;
        int totalItemOfPage = 5;

        String page = request.getParameter("page");
        int offset = 0;
        if (page != null) {
            offset = totalItemOfPage * (Integer.parseInt(page) - 1);
        }

        if (keyword != null) {
            data = EmployeeModel.findEmployeeByEmployeeName(keyword, totalItemOfPage, offset);
        } else {
            // get all users from model
            data = EmployeeModel.getAllEmployee(totalItemOfPage, offset);
        }

        List<Employee> employees = new ArrayList<>();

        while (data.next()) {
            int id = data.getInt("employee_id");
            String name = data.getString("name");
            String email = data.getString("email");
            ;
            int phone = data.getInt("phone");
            Employee employee = new Employee(id, name, email, phone);
            employees.add(employee);
        }
        // Tinh so luong trang
        int totalEmployee = employeeModel.getTotalEmployee();
        if (keyword != null) {
            totalEmployee = employees.size();
        }
        int totalPage = totalEmployee % totalItemOfPage == 0 ? totalEmployee / totalItemOfPage : totalEmployee / totalItemOfPage + 1;

        // set data vao request de truyen xuong jsp
        request.setAttribute("data", employees);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", page);

        // forward request to list.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/employees/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeModel.deleteEmployee(id);
        response.sendRedirect(request.getContextPath() + "/admin/employees");
    }

    public void showPageAddEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // forward request to list.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/employees/create.jsp");
        requestDispatcher.forward(request, response);
    }

    public void showPageUpdateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // get user update tu database
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employeeUpdate = this.getEmployeeById(id);
        // forward request to list.jsp
        request.setAttribute("employee", employeeUpdate);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/employees/update.jsp");
        requestDispatcher.forward(request, response);
    }

    public void createEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // get data from form
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int phone = Integer.parseInt(request.getParameter("phone"));
        Employee employee = new Employee(id, name, email, phone);
        employeeModel.addEmployee(employee);
        // redirect to list user page
        response.sendRedirect(request.getContextPath() + "/admin/employees");
    }

    public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // get data from form
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int phone = Integer.parseInt(request.getParameter("phone"));

        Employee employee = new Employee(id, name, email, phone);
        employeeModel.updateEmployee(employee);
        // redirect to list user page
        response.sendRedirect(request.getContextPath() + "/admin/employees");
    }

    public Employee getEmployeeById(int id) throws SQLException {
        ResultSet resultSet = employeeModel.findEmployeeById(id);
        Employee employee = null;
        while (resultSet.next()) {
            String name = resultSet.getString("customer_name");
            String email = resultSet.getString("email");
            int phone = resultSet.getInt("phone");
            employee = new Employee(id, name, email, phone);
        }
        return employee;
    }
}


