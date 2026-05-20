package bg.tu_varna.turing_machine.models;


import bg.tu_varna.turing_machine.enums.Direction;

/**
 * Converts a direction string from a .txt file or command argument into a Direction value.
 * Accepts "L", "R", or "S" in any case.
 * Throws an IllegalArgumentException if the value is null or unrecognised.
 */
public class DirectionChecker {
    /** Converts "L", "R", or "S" (any case) to the matching Direction. Throws if null or unknown. */
    public static Direction parse(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }
        String upper = text.toUpperCase();
        if (upper.equals("L")) {
            return Direction.L;
        }
        if (upper.equals("R")) {
            return Direction.R;
        }
        if (upper.equals("S")) {
            return Direction.S;
        }
        throw new IllegalArgumentException("Invalid direction: " + text);
    }
}