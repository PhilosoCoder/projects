package codewars.duplicateencoder;

import java.util.HashMap;
import java.util.Map;

public class DuplicateEncoder {

    public static String encode(String word) {
        Map<Character, Integer> charCounts = countCharacterOccurrences(word.toLowerCase());
        return buildEncodedString(word.toLowerCase(), charCounts);
    }

    private static Map<Character, Integer> countCharacterOccurrences(String word) {
        Map<Character, Integer> charCounts = new HashMap<>();

        for (char c : word.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        return charCounts;
    }

    private static String buildEncodedString(String word, Map<Character, Integer> charCounts) {
        StringBuilder output = new StringBuilder();

        for (char c : word.toCharArray()) {
            output.append(charCounts.get(c) > 1 ? ")" : "(");
        }

        return output.toString();
    }

}
