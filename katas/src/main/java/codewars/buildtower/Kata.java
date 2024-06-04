package codewars.buildtower;

public class Kata {

    public static String[] towerBuilder(int nFloors) {
        String[] result = new String[nFloors];
        int width = (nFloors - 1) * 2 + 1;

        for (int i = 0; i < nFloors; i++) {
            int asterisksCount = i * 2 + 1;
            int spacesCount = (width - asterisksCount) / 2;

            String row = " ".repeat(spacesCount) + "*".repeat(asterisksCount) + " ".repeat(spacesCount);
            result[i] = row;
        }

        return result;
    }

}
