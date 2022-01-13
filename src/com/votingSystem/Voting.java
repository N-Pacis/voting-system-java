package com.votingSystem;

import helpers.ErrorMessageLogger;
import helpers.SuccessMessageLogger;
import services.VotingService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Voting {
    private static Integer candidateChoice;
    private static Scanner scan;
    private static VotingService fileOperations = new VotingService();
    private static ErrorMessageLogger error = new ErrorMessageLogger();
    private static SuccessMessageLogger success = new SuccessMessageLogger();


    private void updateVotes(String candidate) throws IOException {
        FileInputStream inputStream = new FileInputStream("storage/candidates.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Integer votes;

        while(reader.ready()){
            String line = reader.readLine();
            String[] splittedLine = line.split(",");

            if(splittedLine[0].equals(candidate)){
                votes = Integer.parseInt(splittedLine[1]) + 1;
                splittedLine[1] = Integer.toString(votes);
                String updatedCandidate = splittedLine[0] + ","+ splittedLine[1]+"\n";
                fileOperations.saveToFile("temp.txt",updatedCandidate,true);
            }
            else{
                String updatedCandidate = splittedLine[0] + ","+splittedLine[1]+"\n";
                fileOperations.saveToFile("temp.txt",updatedCandidate,true);
            }
            List<String> updatedCandidates = new ArrayList<String>(fileOperations.readWholeFileData("temp.txt"));
            fileOperations.saveToFile("candidates.csv","",false);
            for(String updatedCandidate: updatedCandidates){
                updatedCandidate += "\n";
                fileOperations.saveToFile("candidates.csv",updatedCandidate,true);
            }
        }
        fileOperations.saveToFile("temp.txt","",false);
    }

    public void votesCount() throws  IOException{
        System.out.println("##### VOTES COUNT #####");
        List<String> Candidates = new ArrayList<String>(fileOperations.readWholeFileData("candidates.csv"));
        for(String Candidate : Candidates){
            System.out.println("\t\t\t- "+Candidate.split(",")[0] +" with "+ Candidate.split(",")[1]+" votes");
        }
    }

    public void leadingCandidate() throws IOException {
        System.out.println("##### LEADING CANDIDATE #####");
        Integer highestVotes = 0;
        List<String> Candidates = new ArrayList<String>(fileOperations.readWholeFileData("candidates.csv"));
        for(String Candidate : Candidates){
            Integer votes = Integer.parseInt(Candidate.split(",")[1]);
            if( votes > highestVotes){
                highestVotes = votes;
            }
        }
        for(String Candidate : Candidates){
            Integer votes = Integer.parseInt(Candidate.split(",")[1]);
            if(votes.equals(highestVotes)){
                success.log("\t\t\t - "+Candidate.split(",")[0]+" with "+Candidate.split(",")[1]+" votes");
            }
        }
    }

    public void castVote(String userId) throws IOException {
        if(fileOperations.checkVoter(userId)){
            error.log("## You Have Already voted ##");
            System.exit(0);
        }
        System.out.println("### PLEASE CHOOSE YOUR CANDIDATE ###");
        List<String> Candidates = new ArrayList<String>(fileOperations.readFileData("candidates.csv"));
        Integer count = 0;
        for(String candidate : Candidates){
            count++;
            System.out.println("\t\t\t "+count+". "+candidate);
        }
        scan = new Scanner(System.in);
        candidateChoice = scan.nextInt();
        Integer length = Candidates.size();
        candidateChoice -= 1;
        if(!(candidateChoice  >= 0 && candidateChoice < length)){
            error.log("\n !!! ERROR: INVALID CHOICE !!!");
        }
        else{
            updateVotes(Candidates.get(candidateChoice));
            success.log("#### THANKS FOR YOUR VOTE ####");
        }
        fileOperations.saveToFile("voters.csv",userId+"\n",true);
    }
}
