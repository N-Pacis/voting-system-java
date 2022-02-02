package helpers;

import java.sql.*;

public class DatabaseConnection {
    private static final String dbUrl = "jdbc:mysql://localhost/votingSystem";
    private static final String dbUser = "root";
    private static final String dbPassword = "pacis123";
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
