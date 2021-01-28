package controller;

import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletLogin", urlPatterns = URLs.LOGIN)
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username= request.getParameter(ATTRIBUTEs.USERNAME);
        String password= request.getParameter(ATTRIBUTEs.PASSWORD);
        if(username==null){
            username= (String) request.getAttribute(ATTRIBUTEs.USERNAME);
            password= (String) request.getAttribute(ATTRIBUTEs.PASSWORD);
        }
        User user= User.getUserByLogIn(username, password);
        request.getSession().setAttribute("user", user);
        RequestDispatcher dispatcher= request.getRequestDispatcher(URLs.TIMELINE);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username= request.getParameter(ATTRIBUTEs.USERNAME);
        String password= request.getParameter(ATTRIBUTEs.PASSWORD);
        if(username==null){
            username= (String) request.getAttribute(ATTRIBUTEs.USERNAME);
            password= (String) request.getAttribute(ATTRIBUTEs.PASSWORD);
        }
        User user= User.getUserByLogIn(username, password);
        request.getSession().setAttribute("user", user);
        RequestDispatcher dispatcher= request.getRequestDispatcher(URLs.TIMELINE);
        dispatcher.forward(request, response);
    }
}
