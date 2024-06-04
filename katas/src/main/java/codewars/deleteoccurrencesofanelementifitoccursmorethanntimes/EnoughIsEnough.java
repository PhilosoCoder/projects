package codewars.deleteoccurrencesofanelementifitoccursmorethanntimes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnoughIsEnough {

    public static int[] deleteNth(int[] elements, int maxOccurrences) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int num : elements) {
            int count = countMap.getOrDefault(num, 0);
            if (count < maxOccurrences) {
                result.add(num);
                countMap.put(num, count + 1);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}