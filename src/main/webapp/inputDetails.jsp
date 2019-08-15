<%--
  Created by IntelliJ IDEA.
  User: shind
  Date: 8/10/2019
  Time: 11:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
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

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>

<body class=" bg-dark">

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
<%--                    Employee--%>
<%--                </button>--%>
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

<%--            <form action="logout" method="post">--%>
<%--                <input type="submit" class="btn btn-danger" value="Logout" style="margin-right: 210px">--%>
<%--            </form>--%>
<%--        </form>--%>

        <form action="logout" method="post">
            <input type="submit" class="btn btn-danger" value="Logout" style="margin-right: 210px">
        </form>
    </div>
</nav>
<!-- //////////////////////////////////////////////////// -->

<div class="container" style="margin-top: 40px;">


    <%--    /////////////////////////////////--%>

    <%
        if (config.getServletContext().getAttribute("errorMessage") != null) {
            out.print("<div class=\"card\" style=\"border-radius: 25px;border: 2px solid #73AD21;\" >\n" +
                    "        <h5 class=\"card-header\" style=\"color: red\">Unable to Create new Employee</h5>\n" +
                    "        <div class=\"card-body\">");
            out.println(config.getServletContext().getAttribute("errorMessage"));
            out.println("</div>\n" +
                    "    </div>");
            config.getServletContext().removeAttribute("errorMessage");
        }
    %>


    <%--    ////////////////////////////////////////////////--%>


    <div class="card border-dark mb-3" style="border-radius: 25px;border: 2px solid #73AD21;">
        <div class="card-header">
            Add New Employee
        </div>

        <form action="insert" method="post">
            <div class="card-body">

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">Employee Id</span>
                    </div>
                    <input name="employeeId" type="text" class="form-control" placeholder="Employee Id"
                           aria-label="employeeId"
                           aria-describedby="basic-addon1"
                           required>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="">First and last name</span>
                    </div>
                    <input name="firstName" type="text" class="form-control" placeholder="First Name" required>
                    <input name="lastName" type="text" class="form-control" placeholder="Last Name" required >
                </div>

                <br>


                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">Department Id</span>
                    </div>
                    <input name="departmentId" type="text" class="form-control" placeholder="Department Id"
                           aria-label="employeeId"
                           aria-describedby="basic-addon1"
                           required>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="">Date of joing and Date of Birth</span>
                    </div>
                    <input name="dateOfJoining" type="date" class="form-control" placeholder="Date of Joining" required>
                    <input name="dateOfBirth" type="date" class="form-control" placeholder="Date of Birth" required>
                </div>

                <br>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="inputGroupSelect01">Grade</label>
                    </div>
                    <select name="grade" class="custom-select" id="inputGroupSelect01">
                        <option selected>Choose Grade</option>
                        <option value="G1" selected>G1</option>
                        <option value="G2">G2</option>
                        <option value="G3">G3</option>
                        <option value="G4">G4</option>
                        <option value="G5">G5</option>
                        <option value="G6">G6</option>
                        <option value="G7">G7</option>
                    </select>
                </div>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">Designation</span>
                    </div>
                    <input name="designation" type="text" class="form-control" placeholder="Designation "
                           aria-label="employeeId"
                           aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="inputGroupSelect01">Gender</label>
                    </div>
                    <select name="gender" class="custom-select" id="inputGroupSelect01">
                        <option selected>Choose Gender</option>
                        <option value="Male" selected>Male</option>
                        <option value="Female">Female</option>
                    </select>
                </div>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">₹</span>
                    </div>
                    <input name="basePay" type="text" class="form-control" placeholder="Salary"
                           aria-label="Amount (to the nearest dollar)"
                           required>
                    <div class="input-group-append">
                        <span class="input-group-text">.00</span>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary" style="width: 321px">Add employee</button>
            </div>
        </form>
    </div>
</div>
</div>

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
</body>

</html>