package database;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/case4?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "MySQL0435565216@bc";
    public static UserDAO instance= new UserDAO();

    private static final String INSERT_USERS_SQL = "call signIn(?, ?, ?, ?, ?)";
//name - username- password- email- address
    private static final String SELECT_USER_BY_ID = "select * from user where user.userId =?";
    private static final String SELECT_ALL_USERS = "select * from user";
    private static final String DELETE_USERS_SQL = "delete from user where id = ?";
    private static final String UPDATE_USERS_SQL = "update user set name = ?,email= ?, address =? where id = ?";
    private static final String SELECT_USER_BY_LOGIN= "call logIn(?, ?)";
    private static final String FIND_FRIENDS_BY_NAM_OR_USERNAME= "select * from user where name like ? or username like ?";
    // username - password

    public UserDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            //name - username- password- email- address
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public User selectUser(int id) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String username= rs.getString("username");
                String password= rs.getString("password");
                user = new User(id, name, username, password, email, address);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List<User> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<User> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("userId");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String username= rs.getString("username");
                String password= rs.getString("password");
                users.add(new User(id, name, username, password, email, address));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getAddress());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public User logIn(String username, String password){
        User user= null;
        Connection conn= getConnection();
        try {
            PreparedStatement preparedStatement= conn.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                int userId= rs.getInt("userId");
                String name= rs.getString("name");
                String email= rs.getString("email");
                String address= rs.getString("address");
                user= new User(userId, name, username, password, email, address);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public List<User> findFriends(String findString){
        List<User> list= new ArrayList<>();
        Connection connection= getConnection();
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = connection.prepareStatement(FIND_FRIENDS_BY_NAM_OR_USERNAME);
            preparedStatement.setString(1, "%"+ findString +"%");
            preparedStatement.setString(2,"%"+ findString +"%");
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("userId");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String username= rs.getString("username");
                String password= rs.getString("password");
                list.add(new User(id, name, username, password, email, address));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
