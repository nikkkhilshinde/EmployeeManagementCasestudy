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
import java.util.ArrayList;

@WebServlet("/showAll")
public class ShowAll  extends HttpServlet {

    private EmployeeService employeeService = null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        employeeService = new EmployeeService();
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Employee> allEmployees = employeeService.getAllEmployees();
        req.setAttribute("allEmployees",allEmployees);

//        for (Employee employee:allEmployees
//             ) {
//            resp.getWriter().println("Name:"+employee.getFirstName());
//        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("showAllEmployee.jsp");
        requestDispatcher.forward(req,resp);

    }
}
