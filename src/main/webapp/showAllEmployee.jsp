<%@ page import="java.util.ArrayList" %>
<%@ page import="com.netcracker.dto.Employee" %>
<%--
  Created by IntelliJ IDEA.
  User: shind
  Date: 8/15/2019
  Time: 12:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    ArrayList<Employee> allEmployees = (ArrayList<Employee>) request.getAttribute("allEmployees");

%>

</body>
</html>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Hello, world!</title>
</head>
<body class="bg-dark">

<!-- //////////////////////////////////////// -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <!-- <nav class="navbar navbar-expand-lg navbar-dark bg-dark"  style="background-color: #e3f2fd;"> -->
    <a class="navbar-brand" href="adminHomepage.jsp" style="margin-left:210px"><b>Employee management</b></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
<%--            <li class="nav-item active">--%>
<%--                <button class="btn btn-outline-dark my-2 my-sm-0" style="margin-right: 7px" type="submit">Add new--%>
<%--                    Employee</button>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <button class="btn btn-outline-dark my-2 my-sm-0" style="margin-right: 7px" type="submit">Show all--%>
<%--                </button>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Edit employee</button>--%>
<%--            </li>--%>

        </ul>
<%--        <form class="form-inline my-2 my-lg-0">--%>

<%--            <button type="button" style="margin-right: 210px" class="btn btn-outline-info" data-toggle="modal" data-target="#myModal">--%>
<%--                Show profile--%>
<%--            </button>--%>
<%--        </form>--%>
        <form action="logout" method="post">
            <input type="submit" class="btn btn-danger" value="Logout" style="margin-right: 210px">
        </form>
    </div>
</nav>
<!-- //////////////////////////////////////////////////// -->
<!-- ///////////////////////////////// -->
<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Profile info</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                Logged in with admin
                <hr>
                admin@netcracker.com
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <form action="logout" method="post">
                    <button type="submit" class="btn btn-danger" data-dismiss="modal">Logout</button>
                </form>
            </div>

        </div>
    </div>
</div>
<!-- /////////////////////////////////////// -->
<div class="container">

    <div class="card" style="margin-top: 50px">
        <div class="card-header">
            Featured
        </div>
        <div class="card-body">


<%
    out.print("<table class=\"table table-bordered \">\n" +
            "    <thead >\n" +
            "    <tr>\n" +
            "        <th scope=\"col\">Emp Id</th>\n" +
            "        <th scope=\"col\">First Name</th>\n" +
            "        <th scope=\"col\">Last Name</th>\n" +
            "        <th scope=\"col\">Joining</th>\n" +
            "        <th scope=\"col\">DOB</th>\n" +
            "        <th scope=\"col\">Department Id</th>\n" +
            "        <th scope=\"col\">Grade</th>\n" +
            "        <th scope=\"col\">Designation</th>\n" +
            "        <th scope=\"col\">Gender</th>\n" +
            "        <th scope=\"col\">Base Pay</th>\n" +
            "    </tr>\n" +
            "    </thead>\n" +
            "    <tbody>");
    for (Employee employee:allEmployees
    ) {
        out.println("<tr>");

        out.println("<td>"+ employee.getEmployeeId() +"</td>");
        out.println("<td>"+ employee.getFirstName() +"</td>");
        out.println("<td>"+ employee.getLastName() +"</td>");
        out.println("<td>"+ employee.getDateOfJoining() +"</td>");
        out.println("<td>"+ employee.getDateOfBirth() +"</td>");
        out.println("<td>"+ employee.getDepartmentId() +"</td>");
        out.println("<td>"+ employee.getGrade() +"</td>");
        out.println("<td>"+ employee.getDesignation() +"</td>");
        out.println("<td>"+ employee.getGender() +"</td>");
        out.println("<td>"+ employee.getBasePay() +"</td>");
        out.println("</tr>");
    }

    out.print("    </tbody>\n" +
            "</table>");
%>
            <div class="d-inline p-2 bg-primary text-white">d-inline</div>
            <div class="d-inline p-2 bg-dark text-white">d-inline</div>

</div>

</body>
</html>