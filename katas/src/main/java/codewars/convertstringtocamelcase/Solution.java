package codewars.convertstringtocamelcase;

class Solution {

    public static String toCamelCase(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        String sign = extractSign(chars);

        String[] temp = s.split(sign);
        sb.append(temp[0]);

        for (int i = 1; i < temp.length; i++) {
            appendCamelCasePart(sb, temp[i]);
        }

        return sb.toString();
    }

    private static String extractSign(char[] chars) {
        String sign = "";
        for (char c : chars) {
            if (!Character.isAlphabetic(c)) {
                sign = c + "";
            }
        }
        return sign;
    }

    private static void appendCamelCasePart(StringBuilder sb, String part) {
        sb.append(Character.toUpperCase(part.charAt(0)));
        sb.append(part.substring(1));
    }
}