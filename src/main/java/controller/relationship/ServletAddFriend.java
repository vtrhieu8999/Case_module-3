package controller.relationship;

import controller.ATTRIBUTEs;
import controller.URLs;
import database.FriendwithDAO;
import database.UserDAO;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletAddFriend", urlPatterns = "/request")
public class ServletAddFriend extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user= UserDAO.instance.selectUser(Integer.parseInt(request.getParameter(ATTRIBUTEs.USERID)));
        request.setAttribute("addFriendRequest", FriendwithDAO.instance.showAddFriendRequest(user.getId()));
        request.setAttribute(ATTRIBUTEs.USER, user);
        RequestDispatcher dispatcher= request.getRequestDispatcher("view/friend_request.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId= Integer.parseInt(request.getParameter(ATTRIBUTEs.USERID)) ;
        int requestId= Integer.parseInt(request.getParameter("requestId"));
        FriendwithDAO.instance.confirmFriendRequest(userId, requestId);
        RequestDispatcher dispatcher= request.getRequestDispatcher(URLs.LOGIN);
        User user= UserDAO.instance.selectUser(userId);
        request.setAttribute(ATTRIBUTEs.USERNAME, user.getUsername());
        request.setAttribute(ATTRIBUTEs.PASSWORD, user.getPassword());
        dispatcher.forward(request, response);
    }
}
