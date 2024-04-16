package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private String url = "jdbc:mysql://localhost:3306/user";
    private String username = "root";
    private String password = "0000";


    public Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;

        } catch (SQLException ex) {
            System.err.println("The connection to DB has failed: " + ex.toString());
        }
        return null;
    }
}
