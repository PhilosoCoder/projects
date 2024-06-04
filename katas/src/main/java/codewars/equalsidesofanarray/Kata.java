package codewars.equalsidesofanarray;

public class Kata {

    public static int findEvenIndex(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int sumLeft = calculateSum(arr, 0, i);
            int sumRight = calculateSum(arr, i + 1, arr.length);

            if (sumLeft == sumRight) {
                return i;
            }
        }

        return -1;
    }

    private static int calculateSum(int[] arr, int start, int end) {
        int sum = 0;
        for (int j = start; j < end; j++) {
            sum += arr[j];
        }
        return sum;
    }

}
