package codewars.breakcamelcase;

import java.util.Arrays;

public class Solution {

    public static String camelCase(String input) {
        return Arrays.toString(
                input.split("(?=[A-Z])"))
                .replace(",", "")
                .replace("[", "")
                .replace("]", "");
    }

}
