package com.netcracker.servlet;

import com.netcracker.dto.Employee;
import com.netcracker.services.EmployeeService;

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
        servletContext.setAttribute("offset", 0);
        servletContext.setAttribute("limit",2);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = (int) servletContext.getAttribute("offset");
        Integer limit = (Integer) servletContext.getAttribute("limit");
        if(offset>=0){

            offset = offset - limit;
        }
        ArrayList<Employee> allEmployees = employeeService.getNextOrPreviousSetOfEmployees(offset,limit);

        servletContext.removeAttribute("offset");
        servletContext.setAttribute("offset", offset);
        System.out.println("offset:" + offset);

        req.removeAttribute("allEmployees");
        req.setAttribute("allEmployees", allEmployees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("showAllEmployee.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer limit = (Integer) servletContext.getAttribute("limit");
        int offset = (int) servletContext.getAttribute("offset");
        ArrayList<Employee> allEmployees = employeeService.getNextOrPreviousSetOfEmployees(offset,limit);
        if(offset<employeeService.getEmployeeCount()){

            offset = offset + limit;
        }

        servletContext.removeAttribute("offset");
        servletContext.setAttribute("offset", offset);
        System.out.println("offset:" + offset);
        req.removeAttribute("allEmployees");
        req.setAttribute("allEmployees", allEmployees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("showAllEmployee.jsp");
        requestDispatcher.forward(req, resp);
    }
}
