package codewars.sorttheodd;

public class Kata {

    public static int[] sortArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] % 2 != 0) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] % 2 != 0 && array[j] < array[i]) {
                        int temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }

        return array;
    }

}
