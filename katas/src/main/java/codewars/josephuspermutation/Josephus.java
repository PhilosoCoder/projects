package codewars.josephuspermutation;

import java.util.LinkedList;
import java.util.List;

public class Josephus {

    public static <T> List<T> josephusPermutation(final List<T> items, final int k) {
        List<T> finalList = new LinkedList<>(items);
        List<T> result = new LinkedList<>();

        int index = k - 1;

        while (!finalList.isEmpty()) {
            index = index % finalList.size();
            result.add(finalList.remove(index));
            index += k - 1;
        }

        return result;
    }

}
