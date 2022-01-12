package services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserService extends MainService{
    private static FileInputStream inputStream;

    public Boolean checkEmail(String email){
        if(email.equals("abcd@gmail.com")){
            return true;
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
