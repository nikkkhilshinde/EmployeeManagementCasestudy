<%@ page import="com.netcracker.dto.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Edit employee</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body class=" bg-dark">

<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <a class="navbar-brand" href="adminHomepage.jsp" style="margin-left:210px"><strong>Employee management</strong></a>
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

<div class="container" style="margin-top: 40px;">
<% Employee employee = (Employee) config.getServletContext().getAttribute("retrievedEmployee");%>
    <div class="card border-dark mb-3" style="border-radius: 25px;border: 2px solid #73AD21;">
        <div class="card-header">
            Edit Employee
        </div>
        <form action="update" method="post">
            <div class="card-body">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">Employee Id</span>
                    </div>
                        <input name="employeeId" type="text" class="form-control" placeholder="Employee Id"
                               aria-label="employeeId"
                               required readonly="true"
                               value="<%= employee.getEmployeeId()%>">
                    </div>

                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">First and last name</span>
                        </div>
                        <input name="firstName" type="text" class="form-control" placeholder="First Name" required value="<%= employee.getFirstName()%>" >
                        <input name="lastName" type="text" class="form-control" placeholder="Last Name" required  value="<%= employee.getLastName()%>" >
                    </div><br>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">Department Id</span>
                        </div>
                        <input name="departmentId" type="text" class="form-control" placeholder="Department Id"
                               aria-label="employeeId"
                               aria-describedby="basic-addon1"
                               required
                               value="<%= employee.getDepartmentId()%>">
                    </div>

                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="">Date of joing and Date of Birth</span>
                        </div>
                        <input name="dateOfJoining" type="date" class="form-control" placeholder="Date of Joining" required value=<%= employee.getDateOfJoining()%>>
                        <input name="dateOfBirth" type="date" class="form-control" placeholder="Date of Birth" required value="<%= employee.getDateOfBirth()%>">
                    </div><br>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01">Grade</label>
                        </div>
                        <select name="grade" class="custom-select" id="inputGroupSelect01" onselect="<%=employee.getGrade()%>">
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
                            <span class="input-group-text">Designation</span>
                        </div>
                        <input name="designation" type="text" class="form-control" placeholder="Designation"
                               aria-label="employeeId"
                               value="<%= employee.getDesignation()%>">
                    </div>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text">Gender</label>
                        </div>
                        <select name="gender" class="custom-select">
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
                               aria-label="Amount (to the nearest rupees)"
                               required
                               value="<%= employee.getBasePay()%>">
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


</body>

</html>