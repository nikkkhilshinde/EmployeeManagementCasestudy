package com.netcracker.servlet;

import com.netcracker.dto.Admin;
import com.netcracker.dto.Employee;
import com.netcracker.services.AdminService;
import com.netcracker.services.EmployeeService;

import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
    AdminService adminService = null;
    private ServletContext context = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        adminService = new AdminService();
        this.context = config.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin admin = new Admin();
        admin.setPassword(req.getParameter("upass"));
        admin.setUsername(req.getParameter("uname"));

        String pageToVisit = "";
        try {
            if (adminService.validateAdmin(admin)) {
                pageToVisit = "adminHomepage.jsp";
                HttpSession session = req.getSession(true);
                session.setAttribute("username" , admin.getUsername());
            } else {
                pageToVisit = "index.jsp";
                context.setAttribute("message","Invalid Credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(pageToVisit);
        requestDispatcher.forward(req,resp);
    }
}
