<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <meta charset='UTF-8'>
    <title>User create</title>
</head>
<body>
<form method='post' action="${pageContext.servletContext.contextPath}/">
    <b>Name:</b><br>
    <input type='text' name='name' size='40'/><br>
    <b>Login:</b><br>
    <input type='text' name='login' size='40'/><br>
    <b>Email:</b><br>
    <input type='email' name='email' size='40'/><br>
    <input type='hidden' name='created' value=''/><br>
    <input type='submit' value='Create'/>

</form>
</body>
</html>
