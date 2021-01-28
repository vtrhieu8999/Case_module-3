<%@ page import="controller.URLs" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/8/2020
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>LogIn</title>
  </head>
  <body>
  <form action=<%=URLs.LOGIN%> method="post">
    <table>
      <tr>
        <td>User's name</td>
        <td>Password</td>
      </tr>
      <tr>
        <input type="text" name="username">
        <input type="password" name="password">
      </tr>
    </table>
    <button>Login</button>
  </form>
  <a href=<%=URLs.SIGNUP%>>SignUp</a>
  </body>
</html>
