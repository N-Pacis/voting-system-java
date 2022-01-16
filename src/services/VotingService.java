package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VotingService{
    public List<String> getCandidates() throws IOException {
        List<String> Data = new ArrayList<String>();
        return Data;
    }

    public void registerCandidate(String names,Integer votes) throws IOException{
    }

    public void saveVoters(String userId) throws IOException{

    }

    public void updateCandidateVotes(String userId) throws IOException{

    }

    public List<String> getLeadingCandidates() throws IOException{
        List<String> Candidates = new ArrayList<String>();
        return Candidates;
    }

    public Boolean checkVoter(String userId) throws IOException {
        return false;
    }
}
