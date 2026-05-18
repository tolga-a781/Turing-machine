package bg.tu_varna.turing_machine.models;


import bg.tu_varna.turing_machine.enums.Direction;

public class DirectionChecker {
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