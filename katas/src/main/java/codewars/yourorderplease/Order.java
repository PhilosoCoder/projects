package codewars.yourorderplease;

import java.util.Arrays;
import java.util.Comparator;

public class Order {

    public static String order(String words) {
        String[] arr = words.split(" ");

        Arrays.sort(arr, Comparator.comparing(
                s -> Integer.parseInt(s.replaceAll("\\D", ""))));

        return String.join(" ", arr);
    }

}
