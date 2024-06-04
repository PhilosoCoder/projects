package codewars.countthesmileyfaces;

import java.util.List;

public class SmileFaces {

    public static int countSmileys(List<String> arr) {
        return (int) arr.stream().filter(SmileFaces::isValidSmiley).count();
    }

    private static boolean isValidSmiley(String s) {
        int sLength = s.length();
        if (sLength < 2 || sLength > 3) {
            return false;
        }

        char eyes = s.charAt(0);
        boolean validEyes = eyes == ':' || eyes == ';';

        char mouth = s.charAt(sLength - 1);
        boolean validMouth = mouth == ')' || mouth == 'D';

        if (!validEyes || !validMouth) {
            return false;
        }

        char nose = s.charAt(1);
        boolean validNose = nose == '~' || nose == '-';

        if (sLength == 3) {
            return validNose;
        }

        return true;
    }

}