package codewars.snail;


public class Snail {

    public static int[] snail(int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return new int[0];
        }

        int length = array.length;
        int[] result = new int[length * length];
        int index = 0;

        for (int layer = 0; layer <= length / 2; layer++) {
            for (int i = layer; i < length - layer; i++) {
                result[index++] = array[layer][i];
            }
            for (int i = layer + 1; i < length - layer; i++) {
                result[index++] = array[i][length - layer - 1];
            }
            for (int i = layer + 1; i < length - layer; i++) {
                result[index++] = array[length - layer - 1][length - i - 1];
            }
            for (int i = layer + 1; i < length - layer - 1; i++) {
                result[index++] = array[length - i - 1][layer];
            }
        }

        return result;
    }

}