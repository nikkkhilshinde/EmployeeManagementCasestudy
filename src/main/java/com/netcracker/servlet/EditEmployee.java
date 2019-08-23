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

@WebServlet("/editEmployee")
public class EditEmployee extends HttpServlet {

    ServletContext servletContext = null;
    EmployeeService employeeService = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        employeeService = new EmployeeService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();

        try {
            employee.setEmployeeId(Integer.parseInt(req.getParameter("employeeId")));
        } catch (NumberFormatException a) {
            req.getServletContext().setAttribute("errorMessage", "Employee Id should be integer");
            try{req.getRequestDispatcher("searchEmployee.jsp").forward(req, resp);}
            catch (Exception ae){
                log(Constant.PAGE_NOT_FOUND);
            }

        }
        Employee retrievedEmployee = employeeService.getEmployeeById(employee);

        if (retrievedEmployee != null) {
            servletContext.setAttribute("retrievedEmployee", retrievedEmployee);
            try{req.getRequestDispatcher("editEmployee.jsp").forward(req, resp);}
            catch (Exception ae){log(Constant.PAGE_NOT_FOUND);}
        } else {
            servletContext.setAttribute("errorMessage", "Employee with id:"+ employee.getEmployeeId() +" not found");
            try{req.getRequestDispatcher("searchEmployee.jsp").forward(req, resp);}
            catch (Exception ae){
                log(Constant.PAGE_NOT_FOUND);
            }
        }
    }
}
