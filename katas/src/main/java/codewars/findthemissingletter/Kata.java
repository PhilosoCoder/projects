package codewars.findthemissingletter;

public class Kata
{
    public static char findMissingLetter(char[] array) {
        char startChar = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] != startChar + i) {
                return (char) (startChar + i);
            }
        }
        return '0';
    }
}