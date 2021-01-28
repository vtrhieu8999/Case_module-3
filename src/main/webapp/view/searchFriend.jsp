<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.URLs" %>
<%@ page import="controller.ATTRIBUTEs" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/11/2020
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% List<User> searchResult= (List<User>) request.getAttribute("searchResult"); %>
<% User thisUser= (User) request.getSession().getAttribute(ATTRIBUTEs.USER);%>
<head>
    <title>Result</title>
</head>
<body>
<h1>Here</h1>
<% for(User person: searchResult){ %>
<table>
    <tr>
        <form action="<%=URLs.SEARCH_FRIEND%>" method="post">
            <input type="text" name="id1" value="<%=thisUser.getId()%>" hidden>
            <input type="text" name="id2" value="<%=person.getId()%>" hidden>
            <td>Name: <%=person.getName()%></td>
            <td>UserName <%=person.getUsername()%></td>
            <td><button>Add friend</button></td>
        </form>
    </tr>

</table>

<%}%>

</body>
</html>
