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
import java.util.List;

@WebServlet(name = "ServletSearchFriend", urlPatterns = URLs.SEARCH_FRIEND)
public class ServletSearchFriend extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id1= Integer.parseInt(request.getParameter("id1"));
        int id2= Integer.parseInt(request.getParameter("id2"));
        FriendwithDAO.instance.addFriend(id1, id2);
        RequestDispatcher dispatcher= request.getRequestDispatcher(URLs.LOGIN);
        User user= UserDAO.instance.selectUser(id1);
        request.setAttribute(ATTRIBUTEs.USERNAME, user.getUsername());
        request.setAttribute(ATTRIBUTEs.PASSWORD, user.getPassword());
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getParameter("action");
        if(action==null) action="";
        switch(action){
            case "search":
                String searchString= request.getParameter("searchString");
                List<User> list= UserDAO.instance.findFriends(searchString);
                request.setAttribute("searchResult", list);
                break;
            default:
                break;
        }
        toView(request, response);
    }
    private void toView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        User user= UserDAO.instance.selectUser(Integer.parseInt(request.getParameter(ATTRIBUTEs.USERID)));
        request.getSession().setAttribute(ATTRIBUTEs.USER, user);
//        List<Timeline> timeline= Timeline.getTimeLine(user);
//        request.getSession().setAttribute("timeline", timeline);
        RequestDispatcher dispatcher= request.getRequestDispatcher("view/searchFriend.jsp");
        dispatcher.forward(request, response);
    }
}
