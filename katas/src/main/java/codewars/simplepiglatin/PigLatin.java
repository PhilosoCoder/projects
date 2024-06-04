package codewars.simplepiglatin;

public class PigLatin {

    public static String pigIt(String str) {
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (Character.isAlphabetic(words[i].charAt(0))) {
                result.append(words[i].substring(1))
                        .append(words[i].charAt(0))
                        .append("ay");
            } else {
                result.append(words[i]);
            }

            if (i + 1 != words.length) {
                result.append(" ");
            }
        }

        return result.toString();
    }

}