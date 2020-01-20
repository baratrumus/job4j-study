<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="servlets.crudservlet.User" %>
<html>
<head>
    <title>User list</title>
    <meta charset='UTF-8'>
</head>
<body>

<c:if test="${created == 'yes'}">
    <p>User <c:out value="${name}" /> created</p>
</c:if>


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

<c:forEach var="userEntry" items="${userMap}">
   <tr>
       <td> <c:out value="${userEntry.value.getId()}" /></td>
       <td> <c:out value="${userEntry.value.name}" /></td>
       <td> <c:out value="${userEntry.value.login}" /></td>
       <td> <c:out value="${userEntry.value.email}" /></td>
       <td> <c:out value="${userEntry.value.getDate()}" /></td>

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

<form action='create' method='get'>
    <input type='submit' value='Create new user'/>
</form>

</body>
</html>
