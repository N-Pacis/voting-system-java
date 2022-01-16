package com.votingSystem;

import helpers.DatabaseConnection;
import helpers.ErrorMessageLogger;
import services.VotingService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Integer choice;
    private static Boolean loggedIn = false;
    private static String userId;
    private static VotingService votingService = new VotingService();
    private static ErrorMessageLogger error = new ErrorMessageLogger();

    public static void main(String[] args) throws IOException, SQLException {
        new DatabaseConnection();
        do{
            System.out.println("##################################################");
            System.out.println(" ###### Welcome to Election/Voting 2022 #####");
            System.out.println("\t\t\t\t 1. Register to the system");
            System.out.println("\t\t\t\t 2. Login to the system");
            System.out.println("\t\t\t\t 0. Exit");

            Scanner scan = new Scanner(System.in);
            choice = scan.nextInt();
            User user = new User();

            switch(choice){
                case 1:
                    if(user.registerUser()){
                        userId = user.getUserId();
                        loggedIn = true;
                    }
                    break;
                case 2:
                    if(!user.login()){
                        error.log("### INVALID CREDENTIALS ###");
                        user.login();
                    }
                    userId = user.getUserId();
                    loggedIn = true;
                    break;
                case 0:
                    System.exit(0);
                default:
                    error.log("!!! Invalid choice !!!");
            }

        }while(!loggedIn);
        if(loggedIn){
            do{
                System.out.println("### CHOOSE AN ACTION BELOW ###");
                Integer count = 1;
                if(!votingService.checkVoter(userId)){
                    System.out.println("\t\t\t\t "+count+". Cast your vote");
                    count++;
                }
                System.out.println("\t\t\t\t "+count+". Find Vote Count");
                count++;
                System.out.println("\t\t\t\t "+count+". Find the leading candidate");
                count++;
                System.out.println("\t\t\t\t 0. Exit");
                System.out.println("PLEASE ENTER YOUR CHOICE:");

                Scanner scan = new Scanner(System.in);
                choice = scan.nextInt();

                Voting voting = new Voting();
                if(votingService.checkVoter(userId)) choice +=1;
                switch(choice){
                    case 1:
                        voting.castVote(userId);
                        break;
                    case 2:
                        voting.votesCount();
                        break;
                    case 3:
                        voting.leadingCandidate();
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        error.log("!!! Invalid choice !!!");
                }

            }while(!choice.equals(0));
        }

    }
}
