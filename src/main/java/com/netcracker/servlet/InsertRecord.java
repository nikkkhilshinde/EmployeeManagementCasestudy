package com.netcracker.servlet;


import com.netcracker.dto.Employee;
import com.netcracker.services.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/insert")
public class InsertRecord extends HttpServlet {

    private EmployeeService employeeService = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        Employee employee = new Employee();

        String employeeId = req.getParameter("employeeId");
        String basePay = req.getParameter("basePay");

        employee.setFirstName(req.getParameter("firstName"));
        employee.setLastName(req.getParameter("lastName"));
        employee.setDateOfJoining(req.getParameter("dateOfJoining"));
        employee.setDateOfBirth(req.getParameter("dateOfBirth"));
        employee.setDepartmentId(req.getParameter("departmentId"));
        employee.setDesignation(req.getParameter("designation"));
        employee.setGender(req.getParameter("gender"));
        employee.setGrade(req.getParameter("grade"));

        try {
            employee.setEmployeeId(Integer.parseInt(employeeId));
            employee.setBasePay(Integer.parseInt(basePay));
            String message = employeeService.saveEmployeeDetails(employee);
            if ("true".equals(message)) {
                req.getServletContext().setAttribute("successMessage", "New Employee created successfully");
                req.getRequestDispatcher("adminHomepage.jsp").forward(req, resp);
            } else {
                req.getServletContext().setAttribute("errorMessage", message);
                req.getRequestDispatcher("inputDetails.jsp").forward(req, resp);
            }

        } catch (NumberFormatException ae) {
            req.getServletContext().setAttribute("errorMessage", "salary and employee id should be integer");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("inputDetails.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            req.getServletContext().setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("inputDetails.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
