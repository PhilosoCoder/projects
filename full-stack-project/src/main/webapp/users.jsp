<%@page language="java" import="java.util.*" %>
<%@page import="service.Service" %>
<%@page import="model.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>list of users</title>
    <link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="javascript:history.go(-1)">
    <img src="back-button.png" width="40" height="40" alt="Go Back">
</a>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <%
        List<User> list = new Service().listUsers();
        if (list == null || list.isEmpty()) {
    %>No users found<%
} else {
    for (User u : list) {
%>
    <tr>
        <td><%out.println(u.getId());%></td>
        <td><%out.println(u.getName());%></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>