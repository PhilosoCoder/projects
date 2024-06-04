package codewars.detectpangram;

import java.util.HashSet;
import java.util.Set;

public class PangramChecker {

    public boolean check(String sentence) {
        char[] sentenceWords = sentence.toLowerCase().toCharArray();
        Set<Character> result = new HashSet<>();

        for (char c : sentenceWords) {
            if (Character.isAlphabetic(c)) {
                result.add(c);
            }
        }

        return result.size() == 26;
    }

}
