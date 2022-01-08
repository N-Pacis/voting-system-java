package com.votingSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {
    private static FileInputStream inputStream;
    private static FileOutputStream outputStream;

    public List<String> readFileData(String filename) throws IOException {
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

    public List<String> readWholeFileData(String filename) throws IOException {
        inputStream = new FileInputStream("storage/"+filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Integer count = 0;
        List<String> Candidates = new ArrayList<String>();
        while(reader.ready()){
            count++;
            String line = reader.readLine();
            Candidates.add(line);
        }
        return Candidates;
    }

    public void saveToFile(String filename, String data,Boolean append) throws IOException{
        outputStream = new FileOutputStream("storage/"+filename, append);
        byte[] bytesArray = data.getBytes();
        outputStream.write(bytesArray);
    }
}
