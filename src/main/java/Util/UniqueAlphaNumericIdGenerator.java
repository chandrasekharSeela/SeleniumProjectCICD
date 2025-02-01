package Util;

import java.util.Random;
import java.util.UUID;

public class UniqueAlphaNumericIdGenerator {

    public static String AlphaNumericIdGenerator(int length) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid.substring(0, length).toUpperCase();
    }

    public static String randomLetterString(int length){

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallLetters = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder myString = new StringBuilder();
        for(int j= 0; j<length;j++){
            if (j == 0) {
                myString.append(letters.charAt(random.nextInt(letters.length()-1)));
            } else {
                int num = random.nextInt(2)+1;
                switch (num) {
                    case 1 ->  myString.append(letters.charAt(random.nextInt(letters.length())));

                    case 2 -> myString.append(smallLetters.charAt(random.nextInt(smallLetters.length())));

                }
            }
        }
        return myString.toString();
    }
}
