<%--
  Created by IntelliJ IDEA.
  User: visa1116
  Date: 8/2/2019
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>

    <%
        ServletContext context = config.getServletContext();
        if (context.getAttribute("message") != null) {
            out.println(context.getAttribute("message"));
            context.removeAttribute("message");
        }

        if(session.getAttribute("username")!=null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("inbox.jsp");
            dispatcher.forward(request,response);
        }

    %>
    <form action="AuthServlet" method="post">
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="uname"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="upass"/></td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <button type="submit">Login</button>
                </td>
            </tr>
        </table>
    </form>
    <%--    <a href="register.jsp">New User? Sign up here!</a>--%>
</head>
<body>

</body>
</html>
