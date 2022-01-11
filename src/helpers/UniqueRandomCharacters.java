package helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UniqueRandomCharacters {
    public static String random() {
        List<String> randomCharacters = new ArrayList<String>();
        String[] characters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        List<String> letters = new ArrayList<String>(Arrays.asList(characters));
        for (int i = 1; i < 20; i++) {
            randomCharacters.add(Integer.toString(i));
        }
        Collections.shuffle(randomCharacters);
        Collections.reverse(randomCharacters);
        Collections.shuffle(randomCharacters);
        Collections.shuffle(letters);
        for(int j=0;j< 5;j++){
            if(j%2 != 0 & j != 5){
                randomCharacters.set(j,letters.get(j));
            }
        }
        return String.join("",randomCharacters).substring(0,6);
    }
}