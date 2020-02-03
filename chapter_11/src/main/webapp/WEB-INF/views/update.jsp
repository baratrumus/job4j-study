<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update</title>
    <meta charset='UTF-8'>
</head>
<body>

<form method='post' action='update'>

    <b>Id</b><br>
    <input type='text' name='id1' value="${user.getId()}" disabled/><br>
    <input type='hidden' name='id' value="${user.getId()}" /><br>
    <b>Name:</b><br>
    <input type='text' size='40' name='name' value="${user.getName()}"/><br>
    <b>Login:</b><br>
    <input type='text' size='40' name='login' value="${user.getLogin()}"/><br>
    <b>Password:</b><br>
    <input type='text' size='40' name='pass' value="${user.getPassword()}"/><br>
    <b>Email:</b><br>
    <input type='email' size='40' name='email' value="${user.getEmail()}"/><br>

<c:if test="${sessionScope.roleName == 'admin'}">
    <b>Role:</b><br>
    <select name="roles" width ='40'>
        <c:forEach var="role" items="${roleMap}">
            <option
                <c:if test="${user.getRole() == role.value}">
                    selected
                </c:if>
                    value="${role.key}">${role.value}</option>
        </c:forEach>
    </select>
</c:if>

<c:if test="${sessionScope.roleName != 'admin'}">
    <input type='hidden' name='roles' value="${user.getRoleId()}" /><br>
</c:if>

<br><br>
<input type='submit' value='Update'/>
 </form>

</body>
</html>
