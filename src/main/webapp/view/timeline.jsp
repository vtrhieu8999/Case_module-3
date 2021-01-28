<%@ page import="models.User" %>
<%@ page import="models.Timeline" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.URLs" %>
<%@ page import="controller.ATTRIBUTEs" %>
<%@ page import="database.FriendwithDAO" %>
<%@ page import="database.UserDAO" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/9/2020
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>This is your timeline</title>
</head>
<body>
<%! List<Timeline> friendTimeline; %>
<%User user = (User) request.getSession().getAttribute(ATTRIBUTEs.USER); %>
<% List<Timeline> timeline = Timeline.getTimeLine(user); %>
<form action="">
    <input type="text" name="<%=ATTRIBUTEs.USERID%>" value="<%=user.getId()%>" hidden>
    <button>
        <h1>Hello <%= user.getUsername() %></h1>
    </button>
</form>
<form action="/request" method="post">
    <input type="text" name="<%=ATTRIBUTEs.USERID%>" value="<%=user.getId()%>" hidden>
    <button>Friend Request</button>
</form>
<form action="">
    <input type="text" name="<%=ATTRIBUTEs.USERID%>" value="<%=user.getId()%>" hidden>
    <button>Friend List</button>
</form>


<form action="<%=URLs.SEARCH_FRIEND%>" method="get">
    <input type="text" name="action" value="search" hidden>
    <input type="text" name="<%=ATTRIBUTEs.USERID%>" value="<%=user.getId()%>" hidden>
    <input type="text" name="searchString">
    <button>Search</button>
</form>



<h1>Write your thoughts</h1>
<form action="<%=URLs.TIMELINE%>" method="post">
    <input type="text" name="action" value="addPost" hidden>
    <input type="text" name="post">
    <input type="text" name="privacy">
    <button>Post</button>
</form>

<% List<Integer> friendPost= FriendwithDAO.instance.friendList(user.getId()); %>
<% for(int friendId: friendPost){ %>
    <% friendTimeline= Timeline.getTimeLine(UserDAO.instance.selectUser(friendId)); %>
    <% for(Timeline post: friendTimeline){ %>
        <h3><%=post.getContent()%></h3>
    <%}%>
<%}%>

<% for (Timeline post : timeline) { %>
<table>
    <form action="<%=URLs.TIMELINE%>" method="post">

        <input type="text" name="action" value="delete" hidden>
        <input type="text" name="logId" value="<%=post.getLogId()%>" hidden>

        <tr>
            <td>
                <button>Delete</button>
            </td>
        </tr>

    </form>

    <form action="<%=URLs.TIMELINE%>" method="post">
        <input type="text" name="action" value="update" hidden>
        <input type="text" name="logId" value="<%=post.getLogId()%>" hidden>
        <tr>
            <td>
                <input type="text" name="content"
                       id="post<%=post.getLogId()%>"
                       value="<%=post.getContent()%>" disabled="true">
            </td>
        </tr>
        <tr>
            <td>
                <button type="button" onclick="update(<%=post.getLogId()%> ,this)">
                    Edit
                </button>
            </td>
        </tr>
    </form>
</table>
<%}%>


</body>


<script>
    function update(id, btn) {
        let content = document.getElementById('post' + id);
        if (content.disabled) {
            content.disabled = false;
            btn.innerText = "Save";
        } else btn.type = "submit";
    }
</script>


</html>
