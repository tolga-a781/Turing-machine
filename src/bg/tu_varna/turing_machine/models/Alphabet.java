package bg.tu_varna.turing_machine.models;

public class Alphabet {
    public static final char BLANK = '_';

    public static boolean isValid(char symbol) {
        if (symbol == BLANK){
            return true;
        }
        if (symbol >= '0' && symbol <= '9') {
            return true;
        }
        if (symbol >= 'a' && symbol <= 'z') {
            return true;
        }
        return false;
    }

    public static void validate(char symbol) {
        if (!isValid(symbol)) {
            throw new IllegalArgumentException("Invalid symbol: " + symbol);
        }
    }
}