<%@page language="java" import="java.util.*" %>
<%@page import="service.Service"%>
<%@page import="model.User"%>

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
      if(list==null || list.isEmpty()){
        out.println("No users found");
      } else {
        for (User u : list) {
          out.println("<tr>");
          out.println("<td>" + u.getId() + "</td>");
          out.println("<td>" + u.getName() + "</td>");
          out.println("</tr>");}
      }
    %>
  </table>
</body>
</html