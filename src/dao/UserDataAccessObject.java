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

    public void update(User user) {
        try {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE users SET");
            List<Object> params = new ArrayList<>();
            int paramCount = 1; // Começamos em 1 para o parâmetro do ID

            if (user.getUserName() != null && !user.getUserName().isEmpty() && !user.getUserName().equals("0")) {
                sqlBuilder.append(" name = ?,");
                params.add(user.getUserName());
                paramCount++;
            }

            if (user.getUserEmail() != null && !user.getUserEmail().isEmpty() && !user.getUserEmail().equals("0")) {
                sqlBuilder.append(" email = ?,");
                params.add(user.getUserEmail());
                paramCount++;
            }

            if (user.getUserPassword() != null && !user.getUserPassword().isEmpty() && !user.getUserPassword().equals("0")) {
                sqlBuilder.append(" password = ?,");
                params.add(user.getUserPassword());
                paramCount++;
            }

            // Remove a vírgula extra no final, se houver
            if (sqlBuilder.charAt(sqlBuilder.length() - 1) == ',') {
                sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
            }

            if (paramCount > 1) { // Verifica se há algum atributo para atualizar (além do ID)
                sqlBuilder.append(" WHERE id = ?");
                params.add(user.getUserId());

                PreparedStatement sql = this.driver.prepareStatement(sqlBuilder.toString());

                // Define os parâmetros na consulta
                for (int i = 0; i < paramCount; i++) {
                    sql.setObject(i + 1, params.get(i));
                }

                sql.execute();
            } else {
                System.out.println("Nenhum atributo válido para atualizar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
