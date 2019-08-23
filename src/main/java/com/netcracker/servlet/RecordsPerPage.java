package com.netcracker.servlet;

import com.netcracker.utility.Constant;

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
        Integer limit = 0;
        try{
            limit = Integer.valueOf(req.getParameter("count"));
        }catch (Exception ae){log("required integer entered something lelse");}
        servletContext.removeAttribute(Constant.OFFSET);
        servletContext.setAttribute(Constant.OFFSET,0);

        servletContext.removeAttribute(Constant.LIMIT);
        servletContext.setAttribute(Constant.LIMIT,limit);

        try{req.getRequestDispatcher("/showAll").forward(req,resp);}
        catch (Exception ae){log(Constant.PAGE_NOT_FOUND);}
    }
}
