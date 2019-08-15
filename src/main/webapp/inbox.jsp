<%--
  Created by IntelliJ IDEA.
  User: shind
  Date: 8/10/2019
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    if(session.getAttribute("username")==null){
        response.sendRedirect("index.jsp");
    }
%>
<h2>Welcome to inbox,<%=  session.getAttribute("username") %></h2>

<form method="post" action="logout">
    <input type="submit" value="Logout">
</form>

</body>
</html>
