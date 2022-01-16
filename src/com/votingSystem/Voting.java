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
    private static VotingService votingService = new VotingService();
    private static ErrorMessageLogger error = new ErrorMessageLogger();
    private static SuccessMessageLogger success = new SuccessMessageLogger();

    private void updateVotes(String candidate) throws IOException {
        String[] splittedCandidate = candidate.split(",");
        votingService.updateCandidateVotes(splittedCandidate[0]);
 }

    public void votesCount() throws  IOException{
        System.out.println("##### VOTES COUNT #####");
        List<String> Candidates = new ArrayList<String>(votingService.getCandidates());
        for(String Candidate : Candidates){
            System.out.println("\t\t\t- "+Candidate.split(",")[0] +" with "+ Candidate.split(",")[1]+" votes");
        }
    }

    public void leadingCandidate() throws IOException {
        System.out.println("##### LEADING CANDIDATE #####");
        List<String> Candidates = votingService.getLeadingCandidates();
        for(String Candidate : Candidates){
            success.log("\t\t\t - "+Candidate.split(",")[0]+" with "+Candidate.split(",")[1]+" votes");
        }
    }

    public void castVote(String userId) throws IOException {
        if(votingService.checkVoter(userId)){
            error.log("## You Have Already voted ##");
            System.exit(0);
        }

        System.out.println("### PLEASE CHOOSE YOUR CANDIDATE ###");
        List<String> Candidates = votingService.getCandidates();
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
        votingService.saveVoters(userId);
    }
}
