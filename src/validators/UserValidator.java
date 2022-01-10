package validators;

import services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserValidator{
    private static UserService userService = new UserService();
    private static Scanner scan = new Scanner(System.in);

    private static String fullName;
    private static String validNationalId;
    private static String userEmail;
    private static String userPassword;

    public String checkName(String name){
        if(name.length() < 5){
            System.out.println("Error: Name must be at least 5 characters");
            fullName = generateInput("name");
        }
        else if(name.split(" ").length < 2){
            System.out.println("Error: You must provide your full names");
            fullName = generateInput("name");
        }
        else{
            fullName = name;
        }
        return fullName;
    }

    public String checkNationalId(String nationalId){
        if(!(nationalId.length() == 16)){
            System.out.println("Error: NationalId must be valid");
            validNationalId = generateInput("nationalId");
        }
        else{
            validNationalId = nationalId;
        }
        return validNationalId;
    }

    public String checkEmail(String email){
        String[] splitted = email.split("@");
        List<String> emailSplitted = new ArrayList<String>(Arrays.asList(splitted));

        if(email.length() < 5 || !emailSplitted.contains("gmail.com")){
            System.out.println("Error: Email must be valid");
            userEmail = generateInput("email");
        }
        else if(userService.checkEmail(email)){
            System.out.println("Error: Email already exists");
            userEmail = generateInput("email");
        }
        else{
            userEmail = email;
        }
        return userEmail;
    }

    public String checkPassword(String password){
        if(password.length() < 5){
            System.out.println("Error: Password must be at least 5 characters");
            userPassword = generateInput("password");
        }
        else{
            userPassword = password;
        }
        return userPassword;
    }

    public String generateInput(String fieldName){
        System.out.println("\t\t - ENTER YOUR "+fieldName.toUpperCase());
        String value = "";
        switch(fieldName){
            case "name":
                fullName = scan.nextLine();
                checkName(fullName);
                value = fullName;
                break;
            case "nationalId":
                validNationalId = scan.nextLine();
                checkNationalId(validNationalId);
                value = validNationalId;
                break;
            case "email":
                userEmail = scan.nextLine().toLowerCase();
                checkEmail(userEmail);
                value = userEmail;
                break;
            case "password":
                userPassword = scan.nextLine();
                checkPassword(userPassword);
                value = userPassword;
                break;
            default:
                System.out.println("Invalid field");
        }
        return value;
    }
}
