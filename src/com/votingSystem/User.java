package com.votingSystem;

import exceptions.EmailExistsException;
import exceptions.InvalidUserException;
import services.UserService;
import validators.UserValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User {
    UserValidator validator = new UserValidator();
    private static Scanner scan = new Scanner(System.in);

    private static String name;
    private static String nationalId;
    private static String nationality;
    private static String email;
    private static String password;
    private static List<String> invalidFields = new ArrayList<String>();

    public void registerUser() throws IOException{
        String[] fields= {"name","nationalId","nationality","email","password"};
        System.out.println("### \t\tPLEASE FILL THE REQUIRED INFORMATION ###");
        for(String field : fields){
            generateInput(field);
        }
        System.out.println("Name: "+name);
        System.out.println("nationalId: "+nationalId);
        System.out.println("nationality: "+nationality);
        System.out.println("email: "+email);
        System.out.println("Password: "+password);
    }

    private void generateInput(String fieldName){
        System.out.println("\t\t - ENTER YOUR "+fieldName.toUpperCase());
        switch(fieldName){
            case "name":
                name = scan.nextLine();
                name = validator.checkName(name);
                break;
            case "nationalId":
                nationalId = scan.nextLine();
                nationalId = validator.checkNationalId(nationalId);
                break;
            case "nationality":
                nationality = (scan.nextLine()).toUpperCase();
                break;
            case "email":
                email = scan.nextLine().toLowerCase();
                email = validator.checkEmail(email);
                break;
            case "password":
                password = scan.nextLine();
                password = validator.checkPassword(password);
                break;
            default:
                System.out.println("Invalid field");
        }
    }
}
