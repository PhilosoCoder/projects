package codewars.countingduplicates;

public class CountingDuplicates {

    public static int duplicateCount(String text) {
        String lC = text.toLowerCase();

        return (int) text.toLowerCase()
                .chars()
                .distinct()
                .filter(c -> lC.indexOf(c) != lC.lastIndexOf(c))
                .count();
    }

}
