package com.netcracker.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recordsPerPage")
public class RecordsPerPage extends HttpServlet {

    private ServletContext servletContext =null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer limit = Integer.valueOf(req.getParameter("count"));
        servletContext.removeAttribute("offset");
        servletContext.setAttribute("offset",0);

        System.out.println(limit);
        servletContext.removeAttribute("limit");
        servletContext.setAttribute("limit",limit);
        req.getRequestDispatcher("/showAll").forward(req,resp);
    }
}
