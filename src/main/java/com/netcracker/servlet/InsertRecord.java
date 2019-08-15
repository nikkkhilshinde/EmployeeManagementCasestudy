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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();



        String employeeId = req.getParameter("employeeId");
        String basePay = req.getParameter("basePay");

        employee.setEmployeeId(Integer.parseInt(employeeId));
        employee.setFirstName(req.getParameter("firstName"));
        employee.setLastName(req.getParameter("lastName"));
        employee.setDateOfJoining(req.getParameter("dateOfJoining"));
        employee.setDateOfBirth(req.getParameter("dateOfBirth"));
        employee.setDepartmentId(req.getParameter("departmentId"));
        employee.setDesignation(req.getParameter("designation"));
        employee.setGender(req.getParameter("gender"));
        employee.setGrade(req.getParameter("grade"));
        employee.setBasePay(Integer.parseInt(basePay));

        try {
            String message = employeeService.saveEmployeeDetails(employee);
            if("true".equals(message)){
                req.getServletContext().setAttribute("successMessage","New Employee created successfully");
                req.getRequestDispatcher("adminHomepage.jsp").forward(req,resp);
            }
            else{
                req.getServletContext().setAttribute("errorMessage","Error while inserting record");
                req.getRequestDispatcher("inputDetails.jsp").forward(req,resp);
            }

        } catch (SQLException e) {
            req.getServletContext().setAttribute("errorMessage","exception Error while inserting record");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("inputDetails.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
