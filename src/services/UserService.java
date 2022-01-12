package services;

import java.io.*;

public class UserService extends MainService{
    private static FileInputStream inputStream;

    public Boolean checkEmail(String email) throws IOException {
        inputStream = new FileInputStream("storage/users.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while(reader.ready()){
            String line = reader.readLine();
            String[] splittedLine = line.split(",");
            if(splittedLine[2].equals(email)){
                return true;
            }
        }
        return false;
    }

    public Boolean checkCredentials(String email,String password) throws IOException {
        inputStream = new FileInputStream("storage/users.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while(reader.ready()){
            String line = reader.readLine();
            String[] splittedLine = line.split(",");
            if(splittedLine[2].equals(email) && splittedLine[3].equals(password)){
                return true;
            }
        }
        return false;
    }

}
