package services;

import exceptions.EmailExistsException;

public class UserService {
    public Boolean checkEmail(String email){
        if(email.equals("abcd@gmail.com")){
            return true;
        }
        return false;
    }
}
