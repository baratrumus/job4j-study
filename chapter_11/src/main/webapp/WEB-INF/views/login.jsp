<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html lang="">
<head>
    <title>User login</title>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <%--
       <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

        <link href="${pageContext.servletContext.contextPath}/static/style.css" rel="stylesheet"  type="text/css">
        <script type="text/javascript" src="js/script"/>

        <style><%@include file="/css/style.css"%></style>
    --%>

    <link href="css/signin.css" rel="stylesheet"  type="text/css"/>


</head>

<body  class="text-center">

<c:if test="${error != ''}">
    <div style = "background-color: #ff331f">
         <c:out value="${error}" />
    </div>
</c:if>

<form  class="form-signin" method='post' action="${pageContext.servletContext.contextPath}/signin">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>

    <label for="login" class="sr-only">Login</label>
    <input type="text" name='login' id="login" class="form-control" placeholder="Login" required autofocus>

    <label for="password" class="sr-only">Password</label>
    <input type="password" name='password' id="password" class="form-control" placeholder="Password" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

</form>
</body>
</html>
