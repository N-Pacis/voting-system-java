package com.votingSystem;

import exceptions.EmailExistsException;
import exceptions.InvalidUserException;
import services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User {
    private static UserService userService = new UserService();
    private static Scanner scan = new Scanner(System.in);

    private static String name;
    private static String nationalId;
    private static String nationality;
    private static String email;
    private static String password;

    public User() {}
    public User(String name, String nationalId, String nationality, String email, String password) throws InvalidUserException, EmailExistsException {
        if(name.length() < 5){
            throw new InvalidUserException("Your name must be at least 5 characters");
        }
        if(name.split(" ").length < 2){
            throw new InvalidUserException("You must provide your full names");
        }
        if(!(nationalId.length() == 16)){
            throw new InvalidUserException("You must provide a valid national id");
        }
        String[] splitted = email.split("@");
        List<String> emailSplitted = new ArrayList<String>(Arrays.asList(splitted));

        if(email.length() < 5 || !emailSplitted.contains("gmail.com")){
            throw new InvalidUserException("Invalid email address");
        }
        if(userService.checkEmail(email)){
            throw new EmailExistsException("Email Already Exists");
        }
        if(password.length() < 5){
            throw  new InvalidUserException("Password must be atleast 5 characters");
        }

    }
    public void registerUser() throws IOException{
        System.out.println("### \t\tPLEASE FILL THE REQUIRED INFORMATION ###");
        System.out.println("\t\t - ENTER YOUR NAME:");
        name = scan.nextLine();
        System.out.println("\t\t - ENTER YOUR NATIONAL ID");
        nationalId = scan.nextLine();
        System.out.println("\t\t - ENTER YOUR NATIONALITY");
        nationality = (scan.nextLine()).toUpperCase();
        System.out.println("\t\t - ENTER YOUR EMAIL");
        email = scan.nextLine().toLowerCase();
        System.out.println("\t\t - ENTER YOUR PASSWORD");
        password = scan.nextLine();
        try{
            new User(name,nationalId,nationality,email,password);
        }
        catch(InvalidUserException | EmailExistsException Ex){
            System.out.println(Ex.getMessage());
        }
    }
}
