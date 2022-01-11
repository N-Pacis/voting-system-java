package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainService {
    protected static FileInputStream inputStream;
    protected static FileOutputStream outputStream;

    public List<String> readWholeFileData(String filename) throws IOException {
        inputStream = new FileInputStream("storage/"+filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Integer count = 0;
        List<String> Data = new ArrayList<String>();
        while(reader.ready()){
            count++;
            String line = reader.readLine();
            Data.add(line);
        }
        return Data;
    }

    public void saveToFile(String filename, String data,Boolean append) throws IOException{
        outputStream = new FileOutputStream("storage/"+filename, append);
        byte[] bytesArray = data.getBytes();
        outputStream.write(bytesArray);
    }
}
