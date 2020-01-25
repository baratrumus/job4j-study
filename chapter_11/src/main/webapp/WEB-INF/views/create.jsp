<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <meta charset='UTF-8'>
    <title>User create</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>

<body>
<form method='post' action="${pageContext.servletContext.contextPath}/create" enctype="multipart/form-data">
    <b>Name:</b><br>
    <input type='text' name='name' size='40'/><br>
    <b>Login:</b><br>
    <input type='text' name='login' size='40'/><br>
    <b>Email:</b><br>
    <input type='email' name='email' size='40'/><br>
    <input type='hidden' name='created' value=''/><br>
  <!--    -->
    <b>Upload image</b><br>
    <div class="checkbox">
        <input type="file" name="file"><br>
    </div>

    <input type='submit' value='Create'/>

</form>
</body>
</html>
