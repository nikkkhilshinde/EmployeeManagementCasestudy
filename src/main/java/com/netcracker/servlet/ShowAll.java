package com.netcracker.servlet;

import com.netcracker.dto.Employee;
import com.netcracker.services.EmployeeService;
import com.netcracker.utility.Constant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/showAll")
public class ShowAll extends HttpServlet {

    private EmployeeService employeeService = null;
    private ServletContext servletContext = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        employeeService = new EmployeeService();
        servletContext = config.getServletContext();
        servletContext.setAttribute(Constant.OFFSET, 0);
        servletContext.setAttribute(Constant.LIMIT,2);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = (int) servletContext.getAttribute(Constant.OFFSET);
        Integer limit = (Integer) servletContext.getAttribute(Constant.LIMIT);
        if(offset>=0){

            offset = offset - limit;
        }

        ArrayList<Employee> allEmployees = employeeService.getNextOrPreviousSetOfEmployees(offset,limit);

        servletContext.removeAttribute(Constant.OFFSET);
        servletContext.setAttribute(Constant.OFFSET, offset);

        req.removeAttribute(Constant.ALL_EMPLOYEES);
        req.setAttribute(Constant.ALL_EMPLOYEES, allEmployees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.SHOW_ALL_EMPLOYEE_JSP);
        try{requestDispatcher.forward(req, resp);}
        catch (Exception ae){log(Constant.PAGE_NOT_FOUND);}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer limit = (Integer) servletContext.getAttribute(Constant.LIMIT);
        int offset = (int) servletContext.getAttribute(Constant.OFFSET);

        ArrayList<Employee> allEmployees = employeeService.getNextOrPreviousSetOfEmployees(offset,limit);
        if(offset<employeeService.getEmployeeCount()){

            offset = offset + limit;
        }
        servletContext.removeAttribute(Constant.OFFSET);
        servletContext.setAttribute(Constant.OFFSET, offset);
        req.removeAttribute(Constant.ALL_EMPLOYEES);
        req.setAttribute(Constant.ALL_EMPLOYEES, allEmployees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.SHOW_ALL_EMPLOYEE_JSP);
        try{requestDispatcher.forward(req, resp);}
        catch (Exception a){log(Constant.PAGE_NOT_FOUND);}
    }
}
