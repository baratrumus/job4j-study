<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>User list</title>
    <meta charset='UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>

<body>

<c:if test="${created == 'yes'}">
    <p>User <c:out value="${name}" /> created</p>
</c:if>

<div class="container">
<table class="table" border='1' cellpadding='3'>
    <caption>Users table</caption>
    <tr align='center'>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Creation date</th>
        <th>Picture</th>
        <th colspan='2'>Operations</th>
    </tr>

<c:set var = "baseUrl" scope = "session" value = "${pageContext.servletContext.contextPath}"/>

<c:forEach var="userEntry" items="${userMap}">
   <tr>
       <td> <c:out value="${userEntry.value.getId()}" /></td>
       <td> <c:out value="${userEntry.value.name}" /></td>
       <td> <c:out value="${userEntry.value.login}" /></td>
       <td> <c:out value="${userEntry.value.email}" /></td>
       <td> <c:out value="${userEntry.value.getDate()}" /></td>

       <td>
           <img src="${baseUrl}/images/${userEntry.value.getPhotoId()}" width="100px" height="100px"/>
           <a href="${baseUrl}/download?name=${userEntry.value.getPhotoId()}">Download</a>
       </td>

       <td><form action="${pageContext.servletContext.contextPath}/update" method='get'>
           <input type='hidden' name='id' value="${userEntry.value.getId()}">
           <input type='submit' value='update'></form>
       </td>
       <td><form action="${pageContext.servletContext.contextPath}/list" method='post'>
           <input type='hidden' name='id' value="${userEntry.value.getId()}">
           <input type='submit' value='delete'></form>
       </td>
   </tr>
</c:forEach>
</table>
</div>
<form action='create' method='get'>
    <input type='submit' value='Create new user'/>
</form>

</body>
</html>
