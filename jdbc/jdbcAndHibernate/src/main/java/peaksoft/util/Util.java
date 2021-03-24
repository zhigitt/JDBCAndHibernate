package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:postgresql://localhost:5432/lm-jdbc-user";
    private static final String user = "postgres";
    private static final String password = "zhigit";

    public static Connection getConnection(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
