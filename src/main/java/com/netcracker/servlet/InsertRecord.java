package com.netcracker.servlet;


import com.netcracker.dto.Employee;
import com.netcracker.services.EmployeeService;
import com.netcracker.utility.Constant;
import oracle.jdbc.driver.Const;

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

        String employeeId = req.getParameter(Constant.employeeId);
        String basePay = req.getParameter(Constant.basePay);

        employee.setFirstName(req.getParameter(Constant.firstName));
        employee.setLastName(req.getParameter(Constant.lastName));
        employee.setDateOfJoining(req.getParameter(Constant.dateOfJoining));
        employee.setDateOfBirth(req.getParameter(Constant.dateOfBirth));
        employee.setDepartmentId(req.getParameter(Constant.departmentId));
        employee.setDesignation(req.getParameter(Constant.designation));
        employee.setGender(req.getParameter(Constant.gender));
        employee.setGrade(req.getParameter(Constant.grade));

        try {
            employee.setEmployeeId(Integer.parseInt(employeeId));
            employee.setBasePay(Integer.parseInt(basePay));
            String message = employeeService.saveEmployeeDetails(employee);
            if ("true".equals(message)) {
                req.getServletContext().setAttribute(Constant.successMessage, "New Employee created successfully");
                req.getRequestDispatcher(Constant.adminHomepage).forward(req, resp);
            } else {
                req.getServletContext().setAttribute(Constant.errorMessage, message);
                req.getRequestDispatcher(Constant.inputDetailsPage).forward(req, resp);
            }

        } catch (NumberFormatException ae) {
            req.getServletContext().setAttribute(Constant.errorMessage, "salary and employee id should be integer");
            ae.printStackTrace();
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.inputDetailsPage);
            try {
                requestDispatcher.forward(req, resp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            req.getServletContext().setAttribute(Constant.errorMessage, e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.inputDetailsPage);
            try {
                requestDispatcher.forward(req, resp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
