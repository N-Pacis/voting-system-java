package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VotingService extends MainService{

    public List<String> readFileData(String filename) throws IOException{
        inputStream = new FileInputStream("storage/"+filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Integer count = 0;
        List<String> Candidates = new ArrayList<String>();
        while(reader.ready()){
            count++;
            String line = reader.readLine();
            String[] splittedLine = line.split(",");
            Candidates.add(splittedLine[0]);
        }
        return Candidates;
    }

    public Boolean checkVoter(String userId) throws IOException {
        inputStream = new FileInputStream("storage/voters.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while(reader.ready()){
            String line = reader.readLine();
            if(line.equals(userId)){
                return true;
            }
        }
        return false;
    }
}
