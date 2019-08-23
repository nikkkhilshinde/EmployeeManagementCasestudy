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

        try{employee.setEmployeeId(Integer.parseInt(req.getParameter("employeeId")));}
        catch (Exception ae){
            log("Employee id must be integer");
        }
        if (employeeService.getEmployeeById(employee) != null) {

            String employeeId = req.getParameter("employeeId");
            String basePay = req.getParameter("basePay");

            try{employee.setEmployeeId(Integer.parseInt(employeeId));}
            catch (Exception ae){
                log("employee id must be integer");
            }
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
                req.getServletContext().setAttribute(Constant.ERROR_MESSAGE, "Salary should be integer");
                try{
                    req.getRequestDispatcher(Constant.SEARCH_EMPLOYEE_JSP).forward(req, resp);
                }catch (Exception ar){log(Constant.PAGE_NOT_FOUND);}
            }
            String message = employeeService.updateEmployee(employee);
            if ("true".equals(message)) {
                servletContext.setAttribute("successMessage", "Employee updates successfully");
                try{req.getRequestDispatcher(Constant.SEARCH_EMPLOYEE_JSP).forward(req, resp);}
                catch (Exception a){log(Constant.PAGE_NOT_FOUND);}
            } else {
                if (message!=null) {

                    servletContext.setAttribute(Constant.ERROR_MESSAGE, message);
                    try{req.getRequestDispatcher(Constant.SEARCH_EMPLOYEE_JSP).forward(req, resp);}
                    catch (Exception ae){log("page not found");}
                } else {
                    try{req.getRequestDispatcher(Constant.SEARCH_EMPLOYEE_JSP).forward(req, resp);}
                    catch (Exception ae){log("page not found");}
                }
            }
        } else {
            servletContext.setAttribute(Constant.ERROR_MESSAGE, "Employee Not Found");
            try{req.getRequestDispatcher(Constant.SEARCH_EMPLOYEE_JSP).forward(req, resp);}
            catch (Exception a){
                log(Constant.PAGE_NOT_FOUND);
            }
        }
    }
}
