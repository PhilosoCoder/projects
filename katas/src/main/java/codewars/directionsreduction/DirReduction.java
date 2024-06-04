package codewars.directionsreduction;

import java.util.ArrayDeque;
import java.util.Deque;

public class DirReduction {

    static final String NORTH = "NORTH";
    static final String SOUTH = "SOUTH";
    static final String WEST = "WEST";
    static final String EAST = "EAST";

    public static String[] dirReduc(String[] arr) {
        Deque<String> stack = new ArrayDeque<>();

        for (String direction : arr) {
            if (!stack.isEmpty() && isOpposite(stack.peek(), direction)) {
                stack.pop();
            } else {
                stack.push(direction);
            }
        }

        return stack.toArray(new String[0]);
    }

    private static boolean isOpposite(String dir1, String dir2) {
        return (dir1.equals(NORTH) && dir2.equals(SOUTH)) ||
                (dir1.equals(SOUTH) && dir2.equals(NORTH)) ||
                (dir1.equals(EAST) && dir2.equals(WEST)) ||
                (dir1.equals(WEST) && dir2.equals(EAST));
    }

}
