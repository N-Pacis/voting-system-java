package helpers;

import java.sql.*;

public class DatabaseConnection {
    private static String dbUrl = "jdbc:mysql//localhost:3306/votingSystem";
    private static String dbUser = "root";
    private static String dbPassword = "pacis123";

    public DatabaseConnection() {
        try{
            Connection myConnection = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
            Statement myStatement = myConnection.createStatement();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
    }

}
