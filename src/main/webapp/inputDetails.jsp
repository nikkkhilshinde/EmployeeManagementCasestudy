<%--
  Created by IntelliJ IDEA.
  User: shind
  Date: 8/10/2019
  Time: 11:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        table, th, td {
            border: 0px solid black;
        }

        td {
            font-weight: bold;
        }
    </style>
</head>
<body>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<%
    System.out.println("Welcome" + session.getAttribute("username"));
%>
<form action="insert" method="post">
    <table style="width:100%">
        <tr>
            <td align="right">Employee ID :</td>
            <td><input type="text" name="employeeId"></td>
        </tr>
        <tr>
            <td align="right">First Name :</td>
            <td><input type="text" name="firstName"></td>
        </tr>
        <tr>
            <td align="right">Last Name :</td>
            <td><input type="text" name="lastName"></td>
        </tr>
        <tr>
            <td align="right">Date of Joining :</td>
            <td><input type="date" name="dateOfJoining"></td>
        </tr>
        <tr>
            <td align="right">Date of Birth :</td>
            <td><input type="date" name="dateOfBirth"></td>
        </tr>
        <tr>
            <td align="right">Department ID :</td>
            <td><input type="text" name="departmentId"></td>
        </tr>
        <tr>
            <td align="right">Grade :</td>
            <td><input type="text" name="grade"></td>
        </tr>
        <tr>
            <td align="right">Designation :</td>
            <td><input type="text" name="designation"></td>
        </tr>
        <tr>
            <td align="right">Gender :</td>
            <td><input type="text" name="gender"></td>
        </tr>
        <tr>
            <td align="right">Base pay :</td>
            <td><input type="text" name="basePay"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="submit"></td>
        </tr>
        <tr>
            <td></td>
            <td>

            </td>
        </tr>
    </table>
</form>
<form method="post" action="logout">
    <input type="submit" value="Logout">
</form>
</body>
</html>
