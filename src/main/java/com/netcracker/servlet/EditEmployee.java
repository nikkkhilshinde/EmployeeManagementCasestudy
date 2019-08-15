package com.netcracker.servlet;

import com.netcracker.dto.Employee;
import com.netcracker.services.EmployeeService;

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
        employee.setEmployeeId(Integer.parseInt(req.getParameter("employeeId")));

        Employee retrievedEmployee =  employeeService.getEmployeeById(employee);

        if(retrievedEmployee!=null){
            servletContext.setAttribute("retrievedEmployee",retrievedEmployee);
            req.getRequestDispatcher("editEmployee.jsp").forward(req,resp);
        }
        else{
            servletContext.setAttribute("errorMessage","User not found");
            req.getRequestDispatcher("searchEmployee.jsp").forward(req,resp);
        }
        

    }
}
