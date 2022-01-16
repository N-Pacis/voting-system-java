package helpers;

import java.sql.*;

public class DatabaseConnection {
    private static String dbUrl = "jdbc:mysql://localhost/votingSystem";
    private static String dbUser = "root";
    private static String dbPassword = "pacis123";
    public static Connection myConnection;

    public DatabaseConnection() {
        try{
            myConnection = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
    }

}
