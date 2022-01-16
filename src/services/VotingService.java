package services;

import helpers.DatabaseConnection;
import helpers.ErrorMessageLogger;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VotingService{
    DatabaseConnection dbConn =  new DatabaseConnection();
    Connection conn = dbConn.myConnection;
    ErrorMessageLogger error = new ErrorMessageLogger();

    public List<String> getCandidates() throws SQLException {
        List<String> Data = new ArrayList<String>();
        String sql = "SELECT * FROM candidates";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            Integer candidateVotes =  result.getInt("candidateVotes");
            Data.add(candidateVotes.toString() +","+result.getString("candidateName"));
        }
        return Data;
    }

    public void registerCandidates(String names,Integer votes) throws SQLException{
        String sql = "INSERT INTO candidates(candidateName,candidateVotes) VALUES(?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, names);
        statement.setInt(2,votes);
        statement.executeUpdate();
    }

    public void saveVoters(String userId) throws SQLException{
        String sql = "INSERT INTO voters(userId) VALUES(?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,userId);
        if(statement.executeUpdate() <= 0){
            error.log("Unable to save some info");
            System.exit(-1);
        }
    }

    public void updateCandidateVotes(Integer candidateId) throws SQLException{
        String sql = "UPDATE candidates SET candidateVotes=candidateVotes+1 WHERE candidateId=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,candidateId);
        if(statement.executeUpdate() <= 0){
            error.log("Unable to save some info");
            System.exit(-1);
        }
    }

    public List<String> getLeadingCandidates() throws SQLException{
        List<String> Candidates = new ArrayList<String>();
        String sql = "SELECT * from candidates WHERE candidateVotes In( SELECT max(candidateVotes) as candidateVotes FROM candidates)";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            Integer candidateVotes =  result.getInt("candidateVotes");
            Candidates.add(candidateVotes.toString() +","+result.getString("candidateName"));
        }
        return Candidates;
    }

    public Boolean checkVoter(String userId) throws SQLException {
        String sql = "SELECT * FROM voters WHERE userId='"+userId+"'";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        int rowCount = 0;
        while(result.next()){
            rowCount++;
            if(rowCount == 1){
                return true;
            }
        }
        return false;
    }
}
