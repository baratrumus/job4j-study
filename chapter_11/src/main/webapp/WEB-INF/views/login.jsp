<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${error != ''}">
    <div style = "background-color: #ff331f">
         <c:out value="${error}" /> created
    </div>
</c:if>


<form method='post' action="${pageContext.servletContext.contextPath}/login" enctype="multipart/form-data">
    <b>Login:</b><br>
    <input type='text' name='login' size='40'/><br>
    <b>Password:</b><br>
    <input type='text' name='password' size='40'/><br>

    <input type='submit' value='Login'/>

</form>
</body>
</html>
