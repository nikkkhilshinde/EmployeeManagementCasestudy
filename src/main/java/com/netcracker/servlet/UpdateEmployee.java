package com.netcracker.servlet;

import com.netcracker.dto.Employee;
import com.netcracker.services.EmployeeService;
import com.netcracker.utility.Constant;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateEmployee extends HttpServlet {

    private EmployeeService employeeService = null;
    private ServletContext servletContext = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        employeeService = new EmployeeService();
        servletContext = config.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Employee employee = new Employee();

        employee.setEmployeeId(Integer.parseInt(req.getParameter("employeeId")));
        if (employeeService.getEmployeeById(employee) != null) {

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
            try {
                employee.setBasePay(Integer.parseInt(basePay));
            } catch (NumberFormatException ae) {
                req.getServletContext().setAttribute(Constant.errorMessage, "Salary should be integer");
                req.getRequestDispatcher(Constant.searchEmployeePage).forward(req, resp);
            }
            String message = employeeService.updateEmployee(employee);
            if ("true".equals(message)) {
                servletContext.setAttribute("successMessage", "Employee updates successfully");
                req.getRequestDispatcher(Constant.searchEmployeePage).forward(req, resp);
            } else {
                if (message!=null) {

                    servletContext.setAttribute(Constant.errorMessage, message);
                    req.getRequestDispatcher(Constant.searchEmployeePage).forward(req, resp);
                } else {
                    req.getRequestDispatcher(Constant.searchEmployeePage).forward(req, resp);
                }
            }
        } else {
            servletContext.setAttribute(Constant.errorMessage, "Employee Not Found");
            req.getRequestDispatcher(Constant.searchEmployeePage).forward(req, resp);
        }
    }
}
