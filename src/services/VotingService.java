package services;

import helpers.DatabaseConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VotingService{
    DatabaseConnection dbConn =  new DatabaseConnection();
    Connection conn = dbConn.myConnection;
    public List<String> getCandidates() throws IOException {
        List<String> Data = new ArrayList<String>();
        return Data;
    }

    public void registerCandidates(String names,Integer votes) throws SQLException{
        String sql = "INSERT INTO candidates(candidateName,candidateVotes) VALUES(?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, names);
        statement.setInt(2,votes);
        statement.executeUpdate();
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
