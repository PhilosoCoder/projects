package codewars.splitstrings;

import java.util.ArrayList;
import java.util.List;

public class StringSplit {

    public static String[] solution(String s) {
        List<String> tempList = new ArrayList<>();
        int firstIndex = 0;
        int secondIndex = 2;
        int length = s.length() / 2;

        for (int i = 0; i < length; i++) {
            tempList.add(s.substring(firstIndex, secondIndex));
            firstIndex += 2;
            secondIndex += 2;
        }

        if (s.length() % 2 != 0) {
            tempList.add(s.charAt(s.length() - 1) + "_");
        }

        return tempList.toArray(new String[0]);
    }

}
