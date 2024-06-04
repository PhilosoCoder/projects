package codewars.wholikesit;

public class Solution {

    private static final String AND = " and ";

    public static String whoLikesIt(String... names) {
        int length = names.length;

        return switch (length) {
            case 0 -> "no one likes this";
            case 1 -> names[0] + " likes this";
            case 2 -> String.format("%s%s%s like this", names[0], AND, names[1]);
            case 3 -> String.format("%s, %s%s%s like this", names[0], names[1], AND, names[2]);
            default -> String.format("%s, %s%s%d others like this", names[0], names[1], AND, (length - 2));
        };
    }

}
