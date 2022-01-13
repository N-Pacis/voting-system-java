package com.votingSystem;

import helpers.ErrorMessageLogger;
import helpers.SuccessMessageLogger;
import helpers.UniqueRandomCharacters;
import services.UserService;
import validators.UserValidator;

import java.io.IOException;
import java.util.Scanner;

public class User {
    UserValidator validator = new UserValidator();
    UserService userService = new UserService();
    private static Scanner scan = new Scanner(System.in);

    private static String name;
    private static String email;
    private static String password;
    private String userId;
    private static ErrorMessageLogger error = new ErrorMessageLogger();
    private static SuccessMessageLogger success = new SuccessMessageLogger();

    public Boolean registerUser() throws IOException{
        UniqueRandomCharacters randomCharacters = new UniqueRandomCharacters();
        String[] fields= {"name","email","password"};
        System.out.println("### \t\tPLEASE FILL THE REQUIRED INFORMATION ###");
        for(String field : fields){
            generateInput(field,"register");
        }
        String userId = randomCharacters.random();
        String userInfo = userId +","+name+","+email+","+password+"\n";
        userService.saveToFile("users.csv",userInfo,true);
        success.log("### REGISTERED SUCCESSFULLY ###");
        setUserId(userId);
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean login() throws IOException{
        String[] fields = {"email","password"};
        System.out.println("### \t\t ENTER YOUR ACCOUNT");
        for(String field: fields){
            generateInput(field,"login");
        }
        String checkCredentials = userService.checkCredentials(email,password);
        if(!checkCredentials.isEmpty()){
            setUserId(checkCredentials);
            return true;
        }
        return false;
    }

    private void generateInput(String fieldName,String source) throws IOException {
        System.out.println("\t\t - ENTER YOUR "+fieldName.toUpperCase());
        switch(fieldName){
            case "name":
                name = scan.nextLine();
                name = validator.checkName(name);
                break;
            case "email":
                email = scan.nextLine().toLowerCase();
                if(source.equals("register")){
                    email = validator.checkEmail(email);
                }
                break;
            case "password":
                password = scan.nextLine();
                password = validator.checkPassword(password);
                break;
            default:
                error.log("Invalid field");
        }
    }
}
