<%@ page import="models.Friendwith" %>
<%@ page import="models.User" %>
<%@ page import="controller.ATTRIBUTEs" %>
<%@ page import="database.UserDAO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/13/2020
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% List<Integer> list= (List<Integer>) request.getAttribute("addFriendRequest"); %>
<% User user= (User) request.getAttribute(ATTRIBUTEs.USER); %>
<head>
    <title>Add friend request</title>
</head>
<body>
<% for(int requestId: list){%>
<form action="/request" method="get">
    <%=UserDAO.instance.selectUser(requestId).getUsername()%>
    <input type="text" name="<%=ATTRIBUTEs.USERID%>" value="<%=user.getId()%>" hidden>
    <input type="text" name="requestId" value="<%=requestId%>" hidden>
    <button>Approve</button>
</form>
<%}%>
</body>
</html>
