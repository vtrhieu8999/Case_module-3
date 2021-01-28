package models;

import database.FriendwithDAO;

import java.util.List;

public class Friendwith {
    private int userId;
    private List<Integer> friendList;

    public Friendwith(int userId) {
        this.userId = userId;
        this.friendList= FriendwithDAO.instance.friendList(this.userId);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Integer> friendList) {
        this.friendList = friendList;
    }
}
