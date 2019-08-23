package com.netcracker.servlet;


import com.netcracker.dto.Employee;
import com.netcracker.services.EmployeeService;
import com.netcracker.utility.Constant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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

        String employeeId = req.getParameter(Constant.EMPLOYEE_ID);
        String basePay = req.getParameter(Constant.BASE_PAY);

        employee.setFirstName(req.getParameter(Constant.FIRST_NAME));
        employee.setLastName(req.getParameter(Constant.LAST_NAME));
        employee.setDateOfJoining(req.getParameter(Constant.DATE_OF_JOINING));
        employee.setDateOfBirth(req.getParameter(Constant.DATE_OF_BIRTH));
        employee.setDepartmentId(req.getParameter(Constant.DEPARTMENT_ID));
        employee.setDesignation(req.getParameter(Constant.DESIGNATION));
        employee.setGender(req.getParameter(Constant.GENDER));
        employee.setGrade(req.getParameter(Constant.GRADE));

        try {
            employee.setEmployeeId(Integer.parseInt(employeeId));
            employee.setBasePay(Integer.parseInt(basePay));
            String message = employeeService.saveEmployeeDetails(employee);
            if ("true".equals(message)) {
                req.getServletContext().setAttribute(Constant.SUCCESS_MESSAGE, "New Employee created successfully");
                req.getRequestDispatcher(Constant.ADMIN_HOMEPAGE_JSP).forward(req, resp);
            } else {
                req.getServletContext().setAttribute(Constant.ERROR_MESSAGE, message);
                req.getRequestDispatcher(Constant.INPUT_DETAILS_JSP).forward(req, resp);
            }

        } catch (NumberFormatException ae) {
            req.getServletContext().setAttribute(Constant.ERROR_MESSAGE, "salary and employee id should be integer");

            RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.INPUT_DETAILS_JSP);
            try {
                requestDispatcher.forward(req, resp);
            } catch (Exception a) {
                log("page not found");
            }
        } catch (Exception e) {
            req.getServletContext().setAttribute(Constant.ERROR_MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.INPUT_DETAILS_JSP);
            try {
                requestDispatcher.forward(req, resp);
            } catch (Exception ex)
            {log("page not found");
            }
        }
    }
}
