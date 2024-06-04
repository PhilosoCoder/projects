package codewars.validbraces;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;

public class BraceChecker {

    private static final Map<Character, Character> BRACES = Map.of(
            '(', ')',
            '[', ']',
            '{', '}'
    );

    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (BRACES.containsKey(c)) {
                stack.push(c);
            } else if (BRACES.containsValue(c)
                    && (stack.isEmpty() || !Objects.equals(c, BRACES.get(stack.pop())))) {
                return false;
            }
        }

        return stack.isEmpty();
    }

}