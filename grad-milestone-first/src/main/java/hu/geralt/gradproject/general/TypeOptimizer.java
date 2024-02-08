package hu.geralt.gradproject.general;

public class TypeOptimizer {

    @SuppressWarnings("unchecked")
    public static <T> T convertToPrimitive(String input) {
        try {
            if (Character.isLetter(input.charAt(0)) && input.length() == 1) {
                return (T) Character.valueOf(input.charAt(0));
            } else if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                return (T) Boolean.valueOf(input);
            } else {
                Number parsedValue = parseNumeric(input);
                if (parsedValue != null) {
                    switch (parsedValue) {
                        case Byte b -> {
                            return (T) b;
                        }
                        case Short i -> {
                            return (T) i;
                        }
                        case Integer i -> {
                            return (T) i;
                        }
                        case Long l -> {
                            return (T) l;
                        }
                        case Float v -> {
                            return (T) v;
                        }
                        case Double v -> {
                            return (T) v;
                        }
                        default -> {
                        }
                    }
                }
                return (T) input;
            }
        } catch (NumberFormatException | ClassCastException e) {
            System.err.println("Conversion error for input: " + input);
            return (T) input;
        }
    }

private static Number parseNumeric(String input) {
    try {
        var value = Double.parseDouble(input);
        if (!input.contains(".") && value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE) {
            return (byte) value;
        } else if (!input.contains(".") && value > Byte.MAX_VALUE && value <= Short.MAX_VALUE) {
            return (short) value;
        } else if (!input.contains(".") && value >= Short.MAX_VALUE && value <= Integer.MAX_VALUE) {
            return (int) value;
        } else if (!input.contains(".") && value >= Integer.MAX_VALUE && value <= Long.MAX_VALUE) {
            return (long) value;
        } else if (input.contains(".") && value >= Float.MIN_VALUE && value <= Float.MAX_VALUE) {
            return (float) value;
        } else if (input.contains(".") && value >= Float.MAX_VALUE && value <= Double.MAX_VALUE){
            return value;
        }
    } catch (NumberFormatException numberFormatException) {
        return null;
    }
    return null;
}

    public static void main(String[] args) {
        Character c = convertToPrimitive("h");
        String hello = convertToPrimitive("hello");
        byte b = convertToPrimitive("2");
        short s = convertToPrimitive("130");
        int i = convertToPrimitive("33333333");
        long l = convertToPrimitive("4444444444444444");
        float f = convertToPrimitive("12.3");
        double d = convertToPrimitive("444444444444444444444444444444444444444444444444.44444444");
        boolean aTrue = convertToPrimitive("true");
    }

}
