package bg.tu_varna.turing_machine.models;

import bg.tu_varna.turing_machine.enums.Direction;

import java.util.Objects;

/**
 * Represents one transition rule: from a source state on a read symbol,
 * go to a destination state, write a symbol, and move the head.
 * The matches method checks whether this rule applies to a given state and symbol.
 * toString produces standard delta-notation, e.g. delta(q0, 1) = (q1, 0, R).
 */
public class Transition {
    /** The state the machine must be in for this rule to apply. */
    private  State from;
    /** The tape symbol that must be under the head for this rule to apply. */
    private  char read;
    /** The state the machine moves into after applying this rule. */
    private  State to;
    /** The symbol written to the tape before the head moves. */
    private  char write;
    /** The direction the head moves after writing. */
    private  Direction direction;

    /**
     * Creates a transition rule with all five components.
     * @param from the source state.
     * @param read the symbol that must be on the tape to trigger this rule.
     * @param to the destination state to move into.
     * @param write the symbol to write on the tape.
     * @param direction the direction to move the head after writing.
     */
    public Transition(State from, char read, State to, char write, Direction direction) {
        this.from = Objects.requireNonNull(from);
        this.to = Objects.requireNonNull(to);
        this.direction = Objects.requireNonNull(direction);
        this.read = read;
        this.write = write;
    }

    /** Returns the source state of this transition. */
    public State getFrom() {

        return from;
    }
    /** Returns the symbol that must be read for this transition to apply. */
    public char getRead() {

        return read;
    }
    /** Returns the destination state this transition moves the machine into. */
    public State getTo() {

        return to;
    }
    /** Returns the symbol that is written on the tape when this transition fires. */
    public char getWrite() {

        return write;
    }
    /** Returns the direction the head moves after this transition fires. */
    public Direction getDirection() {

        return direction;
    }

    /**
     * Returns true if this transition applies to the given state and tape symbol.
     * @param state the current state of the machine.
     * @param symbol the symbol currently under the head.
     */
    public boolean matches(State state, char symbol) {

        return from.equals(state) && read == symbol;
    }

    /** Returns this rule in delta-notation, e.g. delta(q0, 1) = (q1, 0, R). */
    @Override
    public String toString() {
        return "delta(" + from.getName() + ", " + read + ") = (" + to.getName() + ", " + write + ", " + direction + ")";
    }
}