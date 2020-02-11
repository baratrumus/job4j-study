<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;image/*;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>User list</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

    <style><%@include file="/css/style.css"%></style>

    <script>
<%--
                $(function(){
                    $('#fillUpdate').on('submit', function(event){
                        event.preventDefault();
                        event.stopPropagation();
                        fillUpdateform($('#fillUpdate input[id="idd"]').val())
                    });
                });
--%>

                function fillUpdateform(userId) {
                    $.ajax({
                        url: "./update",
                        type: 'GET',
                        data: {
                            id: userId,
                        },
                        success: function (responseJSON) {
                            console.log(JSON.stringify(responseJSON));
                            //console.log(responseJSON.City);

                            var user = responseJSON.User;
                            var sessionRoleName = $('#sessionRoleName').val();
                            console.log("Session Role name: " + sessionRoleName);

                            $('#idInput').prop("value", user.id);
                            $('#idReal').val(user.id);
                            $('#nameInput').val(user.name);
                            $('#loginInput').val(user.login);
                            $('#passInput').val(user.password);
                            $('#emailInput').val(user.email);
                            $('#CountryInput').val(user.country);

                            //to prevent duplicate options when button is pressed again
                            var $selectCity = $("#CityInput");
                            $selectCity.find("option").remove();

                            $.each(responseJSON.City, function(key, value) {
                                if (value === user.city ) {
                                    $("<option selected>").val(value).text(value).appendTo($selectCity);
                                } else {
                                    $("<option>").val(value).text(value).appendTo($selectCity);
                                }
                            });

                            if (sessionRoleName === "admin") {
                                var $selectRole = $("#roleInput");
                                $selectRole.find("option").remove();

                                $.each(responseJSON.Roles, function (key, value) {
                                    console.log("Admin enter");
                                    if (value === user.role.role) {
                                        $("<option selected>").val(key).text(value).appendTo($selectRole);
                                    } else {
                                        $("<option>").val(key).text(value).appendTo($selectRole);
                                    }
                                });
                            } else {
                                $.each(responseJSON.Roles, function (key, value) {
                                    console.log("Edit user role: " + user.role.role);
                                    console.log(value);
                                    if (value === user.role.role) {
                                        var edt =  $("#editForm").append('<input type="hidden" name="roleInput" id="roleInput">');
                                        console.log(edt);
                                        $("#roleInput").prop("value", key);
                                    }
                                });
                            }
                        }
                    });
                }


        function validate() {
            var name = $('#nameInput').val();
            var login = $('#loginInput').val();
            var pass = document.getElementById('passInput').value;
            if (name === '') {
                alert('Fill the field <Name>');
            }
            if (login === '') {
                alert('Fill the field <Login>');
            }
            if (pass == '') {
                alert('Fill the field <Password>');
            }
            if (pass.length < 4) {
                alert('Password must have 3 simbols at least');
            }
            return true;
        }

    </script>

</head>

<body class="py-4">

<input id="sessionRoleName" type='hidden' name='roles' value="${sessionScope.roleName}" /><br>

<div class="container-md themed-container">
    <div class="row mb-3">
        <div class="col-4 themed-grid-col">
            <c:if test="${created == 'yes'}">
                <p>User <c:out value="${name}" /> created</p>
            </c:if>
        </div>

        <div class="col-4 themed-grid-col">
            <p>You are logged as <b> <c:out value="${sessionScope.login}" /></b></p>
        </div>

        <div class="col-4 themed-grid-col">
            <p>Your role is <b> <c:out value="${sessionScope.roleName}" /></b></p>
        </div>

        <div class="col-4 themed-grid-col">
            <c:if test="${sessionScope.roleName == 'admin'}">
                <p>You can edit all users and roles </p>
            </c:if>

            <c:if test="${sessionScope.roleName != 'admin'}">
                <p>You can edit only your data</p>
            </c:if>
        </div>

    </div>
</div>


<div class="container-md themed-container">
    <table class="table table-striped" border='1' cellpadding='3'>
        <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Login</th>
                <th scope="col">Email</th>
                <th scope="col">Country</th>
                <th scope="col">City</th>
                <th scope="col">Role</th>
                <th scope="col">Creation date</th>
                <th scope="col">Picture<c:out value="${user.name}" /></th>
                <th colspan='2'>Operations</th>
            </tr>
        </thead>
    <c:set var = "baseUrl" scope = "session" value = "${pageContext.servletContext.contextPath}"/>

    <c:forEach var="userEntry" items="${userMap}">
       <tr>
           <td> <c:out value="${userEntry.value.getId()}" /></td>
           <td> <c:out value="${userEntry.value.name}" /></td>
           <td> <c:out value="${userEntry.value.login}" /></td>
           <td> <c:out value="${userEntry.value.email}" /></td>
           <td> <c:out value="${userEntry.value.country}" /></td>
           <td> <c:out value="${userEntry.value.city}" /></td>
           <td> <c:out value="${userEntry.value.getRole()}" /></td>
           <td> <c:out value="${userEntry.value.getDate()}" /></td>

           <td>
               <img src="${baseUrl}/images/${userEntry.value.getPhotoId()}" width="100px" height="100px"/>
               <a href="${baseUrl}/download?name=${userEntry.value.getPhotoId()}">Download</a>
           </td>

           <c:if test="${((sessionScope.roleName == 'admin') || (sessionScope.id == userEntry.value.getId()))}">
             <td>

                <form id="fillUpdate">
                   <input id="idd" type='hidden' name='id' value="${userEntry.value.getId()}">
                   <input type='button' value='update' onclick="fillUpdateform(${userEntry.value.getId()})"/>
                </form>

             </td>
             <td>
                 <form action="${pageContext.servletContext.contextPath}/" method='post'>
                   <input type='hidden' name='id' value="${userEntry.value.getId()}">
                   <input type='submit' value='delete'>
                 </form>
             </td>
           </c:if>
       </tr>
    </c:forEach>

    </table>

    <table>
        <tr><td>
                <c:if test="${sessionScope.roleName == 'admin'}">
                    <form action='create' method='get'>
                        <input type='submit' value='Create new user'/>
                    </form>
                </c:if>
            </td>
            <td>
                <form action='signout' method='get'>
                    <input type='submit' value='Sign Out'/>
                </form>
            </td>
        </tr>
    </table>


</div>

<div class="container-md themed-container">

    <form id="editForm"  class="form-signin" method='post' action="${pageContext.servletContext.contextPath}/update">
        <h1 class="h3 mb-3 font-weight-normal">User edit</h1>

        <label for="idInput" class="sr-only">Id</label>
        <input type="text" name='idInput' id="idInput" class="form-control" placeholder="id" disabled />
        <input type='hidden' name='idReal' id="idReal"/><br>

        <b>Name:</b><br>
        <label for="nameInput" class="sr-only">Name</label>
        <input type="text" name='nameInput' id="nameInput" class="form-control" placeholder="Name" required autofocus/>

        <b>Login:</b><br>
        <label for="loginInput" class="sr-only">Login</label>
        <input type="text" name='loginInput' id="loginInput" class="form-control" placeholder="Login" required/>

        <b>Password:</b><br>
        <label for="passInput" class="sr-only">Password</label>
        <input type="password" name='passInput' id="passInput" class="form-control" placeholder="Password" required/>

        <b>Email:</b><br>
        <label for="emailInput" class="sr-only">Email</label>
        <input type="text" name='emailInput' id="emailInput" class="form-control" placeholder="Email" required/>

        <b>Country:</b><br>
        <label for="CountryInput" class="sr-only">Country</label>
        <input type="text" name='CountryInput' id="CountryInput" class="form-control" placeholder="Country" required/>

        <b>City:</b><br>
        <select name="CityInput" id="CityInput" class="form-control" placeholder="City" >

        </select>


        <c:if test="${sessionScope.roleName == 'admin'}">
            <b>Role:</b><br>
            <select name="roleInput" id="roleInput"  class="form-control"  placeholder="Role" >

            </select>
        </c:if>


        <button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>

    </form>

</div>

</body>
</html>
