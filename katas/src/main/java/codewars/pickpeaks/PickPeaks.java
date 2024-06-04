package codewars.pickpeaks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PickPeaks {

    public static Map<String, List<Integer>> getPeaks(int[] arr) {
        Map<String, List<Integer>> map = initializeMap();

        int i = 1;

        while (i < arr.length - 1) {
            processPeaks(arr, i, map);
            i++;
        }

        return map;
    }

    private static Map<String, List<Integer>> initializeMap() {
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("pos", new ArrayList<>());
        map.put("peaks", new ArrayList<>());
        return map;
    }

    private static void processPeaks(int[] arr, int i, Map<String, List<Integer>> map) {
        if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
            map.get("pos").add(i);
            map.get("peaks").add(arr[i]);
        }
        if (arr[i] > arr[i - 1] && arr[i] == arr[i + 1]) {
            int pos = i;
            int peaks = arr[i];
            i = findPeakEndIndex(arr, i);
            if (arr[i] < peaks) {
                map.get("pos").add(pos);
                map.get("peaks").add(peaks);
            }
        }
    }

    private static int findPeakEndIndex(int[] arr, int currentIndex) {
        int i = currentIndex;
        while (i < arr.length - 1 && arr[i] == arr[currentIndex]) {
            i++;
        }
        return i;
    }

}
