<%--
  Created by IntelliJ IDEA.
  User: shind
  Date: 8/11/2019
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>

<%
    if(session.getAttribute("username")==null){
        response.sendRedirect("index.jsp");
    }
%>

<center>
    <table>
        <tr>
            <td>
                <form action="inputDetails.jsp" method="post"><input type="submit" value="Insert Employee"></form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="showAll" method="post"><input type="submit" value="Show all employees"></form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="searchEmployee" method="post"><input type="submit"
                                                                   value="search and modify employee by Employee ID">
                </form>
            </td>
        </tr>
    </table>
    <form method="post" action="logout">
        <input type="submit" value="Logout">
    </form>
</center>
</body>
</html>
