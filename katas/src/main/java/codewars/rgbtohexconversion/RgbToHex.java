package codewars.rgbtohexconversion;

public class RgbToHex {

    public static String rgb(int r, int g, int b) {
        int red = constrainColorValue(r);
        int green = constrainColorValue(g);
        int blue = constrainColorValue(b);

        return String.format("%02X%02X%02X", red, green, blue);
    }

    private static int constrainColorValue(int value) {
        return Math.min(255, Math.max(0, value));
    }

}