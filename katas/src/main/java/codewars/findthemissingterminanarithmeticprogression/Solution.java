package codewars.findthemissingterminanarithmeticprogression;

public class Solution {

    public static int findMissing(int[] numbers) {
        final int firstNumber = numbers[0];
        final int secondNumber = numbers[1];
        final int diffNumber = secondNumber - firstNumber;

        for (int i = 1; i < numbers.length; i++) {
            int actualNumber = numbers[i];
            int lastNumber = numbers[i - 1];
            if (actualNumber == lastNumber || lastNumber + diffNumber != actualNumber)
                return actualNumber - diffNumber;
        }
        return 0;
    }

    public static void main(String[] args) {
        int result = findMissing(new int[]{1, 3, 4, 5, 6});
        System.out.println("Missing number: " + result);
    }

}
