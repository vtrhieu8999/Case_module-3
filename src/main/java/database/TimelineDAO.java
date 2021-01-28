package database;


import models.Timeline;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimelineDAO {
    private static TimelineDAO instance = new TimelineDAO();
    private TimelineDAO(){}
    public static TimelineDAO getInstance(){
        return TimelineDAO.instance;
    }
    private Connection getConnection(){
        return UserDAO.instance.getConnection();
    }

    private static final String GET_TIMELINE_BY_USER_ID= "select * from timeline where userId=?";
    private static final String GET_POST_BY_LOG_ID= "select * from timeline where logId=?";
    private static final String ADD_POST_BY_USER_ID= "call addPost(?, ?, ?)";
    private static final String DELETE_POST= "delete from timeline where logId=?";
    private static final String UPDATE_POST= "update timeline set content= ?, privacy= ? where logId= ?";

    public List<Timeline> getTimelineByUser(User user){
        List<Timeline> list= new ArrayList<>();
        Connection conn= getConnection();
        try {
            PreparedStatement preparedStatement= conn.prepareStatement(GET_TIMELINE_BY_USER_ID);
            preparedStatement.setInt(1, user.getId());
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                int logId= rs.getInt("logId");
                String content= rs.getString("content");
                String privacy= rs.getString("privacy");
                list.add(new Timeline(logId, user, content, privacy));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
    public Timeline getPostByLogID(int logId){
        Timeline timeline= null;

        Connection conn= getConnection();
        try {
            PreparedStatement preparedStatement= conn.prepareStatement(GET_POST_BY_LOG_ID);
            preparedStatement.setInt(1, logId);
            ResultSet rs= preparedStatement.executeQuery();
            rs.next();
            int userId= rs.getInt("userId");
            User user= UserDAO.instance.selectUser(userId);
            String content= rs.getString("content");
            String privacy= rs.getString("privacy");
            timeline= new Timeline(logId, user, content, privacy);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return timeline;
    }
    public void addPost(int userId, String content, String privacy){
        Connection conn= getConnection();
        try {
            PreparedStatement preparedStatement= conn.prepareStatement(ADD_POST_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, privacy);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void deletePost(int logId){
        Connection conn= getConnection();
        try {
            PreparedStatement preparedStatement= conn.prepareStatement(DELETE_POST);
            preparedStatement.setInt(1, logId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void updatePost(Timeline post){
        Connection conn= getConnection();
        try {
            PreparedStatement preparedStatement= conn.prepareStatement(UPDATE_POST);
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setString(2, post.getPrivacy());
            preparedStatement.setInt(3, post.getLogId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
