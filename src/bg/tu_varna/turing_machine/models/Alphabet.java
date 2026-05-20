package bg.tu_varna.turing_machine.models;

/**
 * Defines valid tape symbols: the blank character '_', digits 0-9,
 * and lowercase letters a-z.
 * The validate method throws an IllegalArgumentException for anything else,
 * preventing invalid transitions from being added to a machine.
 */
public class Alphabet {
    public static final char BLANK = '_';

    /** Returns true if the symbol is blank, a digit 0-9, or a lowercase letter a-z. */
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

    /** Throws IllegalArgumentException if the symbol is not a valid tape symbol. */
    public static void validate(char symbol) {
        if (!isValid(symbol)) {
            throw new IllegalArgumentException("Invalid symbol: " + symbol);
        }
    }
}