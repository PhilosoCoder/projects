package codewars.highestscoringword;

public class Kata {

    public static String high(String s) {
        String[] words = s.split(" ");
        String result = "";
        int highestScore = 0;

        for (String word : words) {
            int wordScore = word.chars().map(c -> c - 'a' + 1).sum();
            if (wordScore > highestScore) {
                highestScore = wordScore;
                result = word;
            }
        }

        return result;
    }

}
