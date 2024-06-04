package codewars.persistentbugger;

class Persist {

    public static int persistence(long num) {
        int count = 0;

        while (num > 9) {
            num = calculateProduct(num);
            count++;
        }

        return count;
    }

    private static long calculateProduct(long num) {
        long product = 1;
        while (num > 0) {
            product *= num % 10;
            num /= 10;
        }
        return product;
    }
}
