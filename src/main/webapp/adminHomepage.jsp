<%--
  Created by IntelliJ IDEA.
  User: shind
  Date: 8/11/2019
  Time: 4:23 PM
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>

<body class="bg-dark">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <!-- <nav class="navbar navbar-expand-lg navbar-dark bg-dark"  style="background-color: #e3f2fd;"> -->
    <a class="navbar-brand" href="#" style="margin-left:210px"><b>Employee management</b></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">


        </ul>
        <form class="form-inline my-2 my-lg-0">

            <button type="button" style="margin-right: 210px" class="btn btn-outline-info" data-toggle="modal"
                    data-target="#myModal">
                Show profile
            </button>
        </form>
    </div>
</nav>
<!-- //////////////////////////////////////////////////// -->
<div class="modal" id="myModal" style="margin-top: 150px">
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
                <button type="button" class="btn btn-danger" data-dismiss="modal">Logout</button>
            </div>

        </div>
    </div>
</div>



<div class="container" style="margin-top: 50px">
    <%
        if(config.getServletContext().getAttribute("successMessage")!=null){
            out.print("<div class=\"card\" style=\"border-radius: 25px;border: 2px solid #73AD21;\">\n" +
                    "        <h5 class=\"card-header\" style=\"color:green;\">Success</h5>\n" +
                    "        <div class=\"card-body\">");
            out.print(config.getServletContext().getAttribute("successMessage"));
            out.print("</div>\n" +
                    "    </div>");
            config.getServletContext().removeAttribute("successMessage");
        }
    %>

    <div class="row">
        <div class="col-md-4">
            <div class="card border-dark mb-3" style="border-radius: 25px;border: 2px solid #73AD21;">

                <div class="card-body ">
                    <form action="inputDetails.jsp" method="post">
                        <input style="width: 100%;height: 100px" class="btn btn-outline-success" type="submit"
                               value="Insert Employee"></form>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card border-dark mb-3" style="border-radius: 25px;border: 2px solid #73AD21;">
                <div class="card-body ">
                    <form action="showAll" method="post">
                        <input style="width: 100%;height: 100px" class="btn btn-outline-success" type="submit"
                               value="Show All Employees"></form>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card border-dark mb-3" style="border-radius: 25px;border: 2px solid #73AD21;">
                <div class="card-body ">
                    <form action="inputDetails.jsp" method="post">
                        <input style="width: 100%;height: 100px" class="btn btn-outline-success" type="submit"
                               value="Edit Employee"></form>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>

</html>
