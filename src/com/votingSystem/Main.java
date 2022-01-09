package com.votingSystem;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Integer choice;
    public static void main(String[] args) throws IOException {
	    do{
            System.out.println("##################################################");
            System.out.println(" ###### Welcome to Election/Voting 2022 #####");
            System.out.println("### CHOOSE AN ACTION BELOW ###");
            System.out.println("\t\t\t\t 1. Cast your vote");
            System.out.println("\t\t\t\t 2. Find Vote Count");
            System.out.println("\t\t\t\t 3. Find the leading candidate");
            System.out.println("\t\t\t\t 4. Register to the system");
            System.out.println("\t\t\t\t 0. Exit");
            System.out.println("PLEASE ENTER YOUR CHOICE:");

            Scanner scan = new Scanner(System.in);
            choice = scan.nextInt();

            Voting voting = new Voting();
            User user = new User();
            switch(choice){
                case 1:
                    voting.castVote();
                    break;
                case 2:
                    voting.votesCount();
                    break;
                case 3:
                    voting.leadingCandidate();
                    break;
                case 4:
                    user.registerUser();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("!!! Invalid choice !!!");
            }

        }while(!choice.equals(0));
    }
}
