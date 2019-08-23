package com.netcracker.servlet;

import com.netcracker.dto.Admin;
import com.netcracker.services.AdminService;
import com.netcracker.utility.Constant;


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


@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
    private AdminService adminService = null;
    private ServletContext context = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        adminService = new AdminService();
        context = config.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin admin = new Admin();
        admin.setPassword(req.getParameter("upass"));
        admin.setUsername(req.getParameter("uname"));

        String pageToVisit = "";
        try {
            boolean bool = adminService.validateAdmin(admin);
            if (bool) {
                pageToVisit = "adminHomepage.jsp";
                HttpSession session = req.getSession(true);
                session.setAttribute("username" , admin.getUsername());
            } else {
                pageToVisit = "index.jsp";
                context.setAttribute("message","Invalid Credentials");
            }
        } catch (Exception e) {
            log("error");
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(pageToVisit);
        try{
            requestDispatcher.forward(req,resp);
        }catch (Exception ae){
            log(Constant.PAGE_NOT_FOUND);
        }
    }
}
