package controller;

import models.Timeline;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletTimeline", urlPatterns = URLs.TIMELINE)
public class ServletTimeline extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getParameter("action");
        String content;
        User user= (User) request.getSession().getAttribute("user");
        if(action==null) action="";
        int logId;

        switch (action){
            case "addPost":
                content= request.getParameter("post");
                String privacy= request.getParameter("privacy");
                Timeline.createPost(user, content, privacy);
                break;
            case "delete":
                logId= Integer.parseInt(request.getParameter("logId"));
                Timeline.getPostByLogId(logId).delete();
                break;
            case "update":
                content= request.getParameter("content");
                logId= Integer.parseInt(request.getParameter("logId")) ;
                Timeline temp= Timeline.getPostByLogId(logId);
                temp.setContent(content);
                temp.update();

        }
        toView(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void toView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        User user= (User) request.getSession().getAttribute("user");
        RequestDispatcher dispatcher= request.getRequestDispatcher("view/timeline.jsp");
        dispatcher.forward(request, response);
    }
}
