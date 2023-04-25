package Database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection connect(){
        Connection connection = null;
        try{
            String url = "jdbc:mysql://localhost:3306/crud_database?user=root&password=aluno123";
            connection = DriverManager.getConnection(url);
        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Database.DatabaseConnection: " + error.getMessage());
        }
        return connection;
    }
}
