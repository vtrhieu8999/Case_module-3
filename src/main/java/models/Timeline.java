package models;

import database.TimelineDAO;
import database.UserDAO;

import java.util.List;

public class Timeline {
    private int logId;
    private String content;
    private String privacy;
    private User user;
    public Timeline(int logId, User user, String content, String privacy){
        this.logId=logId;
        this.content=content;
        this.privacy=privacy;
        this.user= user;
    }

    public static Timeline getPostByLogId(int logId){
        return TimelineDAO.getInstance().getPostByLogID(logId);
    }

    public static void createPost(User user, String content, String privacy){
        TimelineDAO.getInstance().addPost(user.getId(), content, privacy);
    }
    public static List<Timeline> getTimeLine(User user){
        return TimelineDAO.getInstance().getTimelineByUser(user);
    }
    public void update(){
        TimelineDAO.getInstance().updatePost(this);
    }
    public void delete() { TimelineDAO.getInstance().deletePost(this.logId);}



    public int getLogId() {
        return logId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
