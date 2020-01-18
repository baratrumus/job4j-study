<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="servlets.crudservlet.User" %>
<html>
<head>
    <title>Update</title>
    <meta charset='UTF-8'>
</head>
<body>
<%
    User user = (User)request.getAttribute("user");
    String id = Integer.toString(user.getId());
%>

<form method='post' action='update'>

    <b>Id</b><br>
    <input type='text' name='id1' value='<%=id%>' disabled/><br>
    <input type='hidden' name='id' value='<%=id%>' /><br>
    <b>Name:</b><br>
    <input type='text' size='40' name='name' value='<%=user.getName()%>'/><br>
    <b>Login:</b><br>
    <input type='text' size='40' name='login' value='<%=user.getLogin()%>'/><br>
    <b>Email:</b><br>
    <input type='email' size='40' name='email' value='<%=user.getEmail()%>'/><br><br>
    <input type='submit' value='Update'/>
 </form>

</body>
</html>
