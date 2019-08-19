<%--
  Created by IntelliJ IDEA.
  User: visa1116
  Date: 8/2/2019
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <%
        ServletContext context = config.getServletContext();
//        if (context.getAttribute("message") != null) {
//            out.println(context.getAttribute("message"));
//            context.removeAttribute("message");
//        }

        if(session.getAttribute("username")!=null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("inbox.jsp");
            dispatcher.forward(request,response);
        }

    %>

<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<body class="container bg-dark" style="margin-top: 100px; max-width: 500px;">

<%

    if (context.getAttribute("message") != null) {
        out.print("<div class=\"card border-dark mb-3\" style=\"border-radius: 25px;border: 2px solid #73AD21;\">\n" +
                "    <div class=\"card-header\">\n" +
                "        Error" +
                "    </div>\n" +
                "    <div class=\"card-body\">");
        out.println(context.getAttribute("message"));
        out.print("</div></div>");
        context.removeAttribute("message");
    }
%>

<div class="card border-dark mb-3" style="border-radius: 25px;border: 2px solid #73AD21;">
    <div class="card-header">
        Admin Login
    </div>
    <div class="card-body">

        <form action="AuthServlet" method="post">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1" >Username</span>
            </div>
            <input name="uname" type="text" class="form-control" placeholder="Username"
                   aria-describedby="basic-addon1">
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">Password</span>
            </div>
            <input name="upass" type="password" class="form-control" placeholder="password"
                   aria-describedby="basic-addon1">
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> Remember me
            </label>
        </div>
        <hr>
            <input type="submit" class="btn btn-primary" style="width: 321px" value="Login">
        <button type="button" class="btn btn-secondary" style="width: 100px">Cancel</button>
        </form>
    </div>
</div>
</body>

</html>
