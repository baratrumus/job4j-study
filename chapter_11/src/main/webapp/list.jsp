<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="servlets.crudservlet.User" %>
<html>
<head>
    <title>User list</title>
    <meta charset='UTF-8'>
</head>
<body>
<%
Map<Integer, User> users = (Map<Integer, User>)request.getAttribute("userMap");
String created = (String)request.getAttribute("created");
String name = (String)request.getAttribute("name");
StringBuilder sb = new StringBuilder();

if ("yes".equals(created)) {
    sb.append("User ").append(name).append(" created");
}

if (users != null) {
    for (Map.Entry<Integer, User> entry : users.entrySet()) {
        User user = entry.getValue();
        sb.append("<tr><td>").append(user.getId()).append("</td>");
        sb.append("<td>").append(user.getName()).append("</td>");
        sb.append("<td>").append(user.getLogin()).append("</td>");
        sb.append("<td>").append(user.getEmail()).append("</td>");
        sb.append("<td>").append(user.getDate()).append("</td>");

        sb.append("<td><form action='");
        sb.append(request.getContextPath());
        sb.append("/update' method='get'>");
        sb.append("<input type='hidden' name='id' value='");
        sb.append(user.getId());
        sb.append("'>");
        sb.append("<input type='submit' value='update'></form></td>");

        sb.append("<td><form action='");
        sb.append(request.getContextPath());
        sb.append("/list' method='post'>");
        sb.append("<input type='hidden' name='id' value='");
        sb.append(user.getId());
        sb.append("'>");
        sb.append("<input type='submit' value='delete'></form></td></tr>");
    }
}
%>


<table border='1' cellpadding='3'>
    <caption>Users table</caption>
    <tr align='center'>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Creation date</th>
        <th colspan='2'>Operations</th>
    </tr>
    <%=sb.toString()%>
</table>

<form action='create' method='get'>
    <input type='submit' value='Create new user'/>
</form>

</body>
</html>
