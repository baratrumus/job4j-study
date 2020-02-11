<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title>User create</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

    <style><%@include file="/css/style.css"%></style>


</head>

<body>
<form id="form1"  class="form-signin" method='post' action="${pageContext.servletContext.contextPath}/create" enctype="multipart/form-data">
    <h1 class="h3 mb-3 font-weight-normal">Sign up</h1>

    <b>Name:</b><br>
    <input type="text" name='name' id="name" class="form-control" placeholder="Name" required autofocus>

    <b>Login:</b><br>
    <input type="text" name='login' id="login" class="form-control" placeholder="Login" required>

    <b>Password:</b><br>
    <input type="password" name='password' id="password" class="form-control" placeholder="Password" required>

    <b>Email:</b><br>
    <input type="email" name='email' id="email" class="form-control" placeholder="Email" required>

    <b>Role</b><br>
    <select name="roles" id="roles"  class="form-control">
        <c:forEach var="role" items="${roleMap}">
            <option value="${role.key}">${role.value}</option>
        </c:forEach>
    </select>

    <b>Country:</b><br>
    <input type="text" name='country' id="country" class="form-control" placeholder="Country" required>

    <b>City:</b><br>
    <select name="CityInput" id="CityInput" class="form-control" placeholder="City" >
        <c:forEach var="city" items="${citiesList}">
        <option
                <c:if test="${user.getCity() == city}">
                    selected
                </c:if>
                value="${city}">${city}</option>
        </c:forEach>
    </select>


    <br>
    <input type='hidden' name='created' value=''/><br>


    <b>Upload image</b><br>
    <div class="checkbox">
        <input type="file" name="file"  class="form-control"><br>
    </div>

    <input class="btn btn-lg btn-primary btn-block"  type='submit' value='Create'/>

</form>
</body>
</html>
