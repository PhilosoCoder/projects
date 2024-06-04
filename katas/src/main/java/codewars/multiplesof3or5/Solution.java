package codewars.multiplesof3or5;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int solution(int number) {
        Set<Integer> multiples = new HashSet<>();

        for (int i = 3; i < number; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                multiples.add(i);
            }
        }

        return multiples.stream().mapToInt(Integer::intValue).sum();
    }

}
