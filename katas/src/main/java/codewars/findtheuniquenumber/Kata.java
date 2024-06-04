package codewars.findtheuniquenumber;

import java.util.*;

public class Kata {
    public static double findUniq(double arr[]) {
        List<Double> uniqueValues = new ArrayList<>();
        List<Double> duplicateValues = new ArrayList<>();

        categorizeValues(arr, uniqueValues, duplicateValues);

        return findUniqueValue(uniqueValues, duplicateValues);
    }

    private static void categorizeValues(double[] arr, List<Double> uniqueValues, List<Double> duplicateValues) {
        for (double value : arr) {
            (uniqueValues.contains(value) ? duplicateValues : uniqueValues).add(value);
        }
    }

    private static double findUniqueValue(List<Double> uniqueValues, List<Double> duplicateValues) {
        for (double uniqueValue : uniqueValues) {
            if (!duplicateValues.contains(uniqueValue)) {
                return uniqueValue;
            }
        }
        return 0;
    }

}
