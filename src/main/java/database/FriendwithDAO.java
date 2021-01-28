package database;

import controller.ATTRIBUTEs;
import models.Friendwith;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendwithDAO {
    public static FriendwithDAO instance= new FriendwithDAO();
    public FriendwithDAO(){}
    private Connection getConnection(){
        return UserDAO.instance.getConnection();
    }

    private static final String GET_FRIEND_WITH_USER_ID= "call friendList(?)";
    private static final String ADD_FRIEND= "call addFriend(?,?)";
    private static final String GET_ADD_FRIEND_REQUEST= "call showAddFriendRequest(?)";
    private static final String CONFIRM_TOBE_FRIEND=
            "update friendwith set status='success'" +
                    "where id1=? and id2=? or id2=? and id1=?";
    private static final String DELETE_FRIEND=
            "delete fromm friendwith where id1=? and id2=? or id2=? and id1=?";


    public List<Integer> friendList(int userId){
        List<Integer> friendList= new ArrayList<>();
        Connection connection= getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(GET_FRIEND_WITH_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet rs= preparedStatement.executeQuery();
            while ((rs.next())){
                int friendId= rs.getInt(ATTRIBUTEs.USERID);
                friendList.add(friendId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return friendList;
    }
    public void addFriend(int id1, int id2){
        Connection conn= getConnection();
        try {
            PreparedStatement preparedStatement= conn.prepareStatement(ADD_FRIEND);
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public List<Integer> showAddFriendRequest(int userId){
        List<Integer> friendRequest= new ArrayList<>();
        Connection connection= getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(GET_ADD_FRIEND_REQUEST);
            preparedStatement.setInt(1, userId);
            ResultSet rs= preparedStatement.executeQuery();
            while ((rs.next())){
                int friendId= rs.getInt(ATTRIBUTEs.USERID);
                friendRequest.add(friendId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return friendRequest;
    }
    public void confirmFriendRequest(int id1, int id2){
        Connection conn= getConnection();
        try {
            PreparedStatement preparedStatement= conn.prepareStatement(CONFIRM_TOBE_FRIEND);
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);
            preparedStatement.setInt(3, id1);
            preparedStatement.setInt(4, id2);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void deleteFriend(int id1, int id2){
        Connection conn= getConnection();
        try {
            PreparedStatement preparedStatement= conn.prepareStatement(DELETE_FRIEND);
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);
            preparedStatement.setInt(3, id1);
            preparedStatement.setInt(4, id2);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
