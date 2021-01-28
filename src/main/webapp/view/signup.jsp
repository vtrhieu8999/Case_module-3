<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/8/2020
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUp</title>
</head>
<body>
<form action="/signup" method="post">
    <table>
        <tr>
            <td>Name</td>
            <td>User's name</td>
            <td>Password</td>
            <td>Repeat Password</td>
            <td>Email</td>
            <td>Address</td>
        </tr>
        <tr>
            <td><input type="text" name="name"></td>
            <td><input type="text" name="username"></td>
            <td><input type="password" name="password"></td>
            <td><input type="password" name="repeatPass"></td>
            <td><input type="text" name="email"></td>
            <td><input type="text" name="address"></td>
        </tr>
        <button>SignUp</button>
    </table>
</form>

</body>
</html>
