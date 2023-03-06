<%@page language="java" import="java.util.*" %>
<%@page import="service.Service" %>
<%@page import="model.Advertisement" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Advertisements by users</title>
    <link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="javascript:history.go(-1)">
    <img src="back-button.png" width="40" height="40" alt="Go Back">
</a>
<table>
    <tr>
        <th>Name</th>
        <th>Advertisement(s)</th>
    </tr>
    <%
        Map<String, List<Advertisement>> list = new Service().listAdvertisementsByUsers();
        if (list == null || list.isEmpty()) {
    %>No users found<%
} else {
    for (Map.Entry<String, List<Advertisement>> entry : list.entrySet()) {
%>
    <tr>
        <td><% out.println(entry.getKey());%></td>
        <td><%
            for (Advertisement advertisement : entry.getValue()) {
                out.println(advertisement.getTitle() + "</br>");
            }
        %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>