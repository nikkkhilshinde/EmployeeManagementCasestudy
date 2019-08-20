<%@ page import="java.util.ArrayList" %>
<%@ page import="com.netcracker.dto.Employee" %>
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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body class="bg-dark">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="adminHomepage.jsp" style="margin-left:210px"><b>Employee management</b></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto"></ul>
        <form action="logout" method="post">
            <input type="submit" class="btn btn-danger" value="Logout" style="margin-right: 210px">
        </form>
    </div>
</nav>

<div class="container">

    <div class="card" style="margin-top: 50px;border-radius: 25px">
        <div class="card-header">
            All employees(Showing 5 records per page)
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
                for (Employee employee : allEmployees
                ) {
                    out.println("<tr>");

                    out.println("<td>" + employee.getEmployeeId() + "</td>");
                    out.println("<td>" + employee.getFirstName() + "</td>");
                    out.println("<td>" + employee.getLastName() + "</td>");
                    out.println("<td>" + employee.getDateOfJoining() + "</td>");
                    out.println("<td>" + employee.getDateOfBirth() + "</td>");
                    out.println("<td>" + employee.getDepartmentId() + "</td>");
                    out.println("<td>" + employee.getGrade() + "</td>");
                    out.println("<td>" + employee.getDesignation() + "</td>");
                    out.println("<td>" + employee.getGender() + "</td>");
                    out.println("<td>" + employee.getBasePay() + "</td>");
                    out.println("</tr>");
                }

                out.print("    </tbody>\n" +
                        "</table>");

            %>
            <div class="container">
                <ul class="list-inline">
                    <li class="list-inline-item">
                        <form class="form-inline pull-right" method="get" action="showAll">
                            <input type="submit" value="Previous" class="btn btn-light">
                        </form>
                    </li>
                    <li class="list-inline-item">
                        <form class="form-inline pull-right" method="post" action="showAll">
                            <input type="submit" value="Next" class="btn btn-primary d-inline">
                        </form>
                    </li>
                </ul>
            </div>

        </div>

</body>
</html>