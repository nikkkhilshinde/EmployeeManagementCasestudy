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
public class ShowAll  extends HttpServlet {

    private EmployeeService employeeService = null;
    private ServletContext servletContext = null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        employeeService = new EmployeeService();
        servletContext = config.getServletContext();
        servletContext.setAttribute("offset",0);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset =(int) servletContext.getAttribute("offset");
        offset = offset - 5;
        ArrayList<Employee> allEmployees = employeeService.getNextOrPreviousSetOfEmployees(offset);

        servletContext.removeAttribute("offset");
        servletContext.setAttribute("offset",offset);
        System.out.println("offset:"+offset);

        req.removeAttribute("allEmployees");
        req.setAttribute("allEmployees",allEmployees);

//        for (Employee employee:allEmployees
//             ) {
//            resp.getWriter().println("Name:"+employee.getFirstName());
//        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("showAllEmployee.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ArrayList<Employee> allEmployees = employeeService.getAllEmployees();
        int offset =(int) servletContext.getAttribute("offset");
        ArrayList<Employee> allEmployees = employeeService.getNextOrPreviousSetOfEmployees(offset);
        offset = offset + 5;
        servletContext.removeAttribute("offset");
        servletContext.setAttribute("offset",offset);
        System.out.println("offset:"+offset);
        req.removeAttribute("allEmployees");
        req.setAttribute("allEmployees",allEmployees);

//        for (Employee employee:allEmployees
//             ) {
//            resp.getWriter().println("Name:"+employee.getFirstName());
//        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("showAllEmployee.jsp");
        requestDispatcher.forward(req,resp);

    }
}
