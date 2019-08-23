package com.netcracker.servlet;

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

@WebServlet("/logout")
public class Logout extends HttpServlet {

    private ServletContext servletContext = null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        servletContext.setAttribute("logoutMessage","Successfully logged out");
        try{
            requestDispatcher.forward(req,resp);
        }catch (Exception ae){
            log(Constant.PAGE_NOT_FOUND);
        }
    }
}
