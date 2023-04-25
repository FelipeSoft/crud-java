package dao;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDataAccessObject {
    private Connection driver;
    public UserDataAccessObject(Connection driver){
        this.driver = driver;
    }
    public void create(User user){
        try{
            PreparedStatement sql = this.driver.prepareStatement("INSERT INTO users (name, email, password) VALUES (?, ?, ?)");
            sql.setString(1, user.getUserName());
            sql.setString(2, user.getUserEmail());
            sql.setString(3, user.getUserPassword());
            sql.execute();
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }

    public List<User> all(){
        List<User> users = new ArrayList<User>();
        try{
            PreparedStatement sql = this.driver.prepareStatement("SELECT * FROM users");
            ResultSet data = sql.executeQuery();
            while(data.next()){
                User user = new User();
                user.setUserId(data.getInt("id"));
                user.setUserName(data.getString("name"));
                user.setUserEmail(data.getString("email"));
                user.setUserPassword(data.getString("password"));
                users.add(user);
            }
        } catch (Exception error) {
            error.getStackTrace();
        }
        return users;
    }

    public User find(int id){
        User user = new User();
        try {
            PreparedStatement sql = this.driver.prepareStatement("SELECT * FROM users WHERE id = ?");
            sql.setInt(1, id);
            ResultSet data = sql.executeQuery();

            if(data != null){
               while(data.next()){
                   user.setUserId(data.getInt("id"));
                   user.setUserName(data.getString("name"));
                   user.setUserEmail(data.getString("email"));
                   user.setUserPassword(data.getString("password"));
               }

                return user;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }
}
