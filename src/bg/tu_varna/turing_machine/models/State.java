package bg.tu_varna.turing_machine.models;

import java.util.Objects;

/**
 * Represents a single state in a Turing Machine.
 * Holds an immutable name and three flags: starting, accepting, and rejecting.
 * Setting accepting clears rejecting and vice versa, so both can never be true at once.
 * Two states are equal if their names match, which allows correct use in maps and sets.
 * The toString method appends role labels such as "[start,accept]" after the name.
 */
public class State {
    private final String name;
    private boolean starting;
    private boolean accepting;
    private boolean rejecting;

    /** Creates a state with the given name. Throws if the name is null. */
    public State(String name) {
        this.name = Objects.requireNonNull(name);
    }

    /** Returns the name of this state. */
    public String getName() {
        return name;
    }
    /** Returns true if this is the start state of the machine. */
    public boolean isStarting() {
        return starting;
    }
    /** Returns true if the machine accepts when it enters this state. */
    public boolean isAccepting() {
        return accepting;
    }
    /** Returns true if the machine rejects when it enters this state. */
    public boolean isRejecting() {
        return rejecting;
    }
    /** Returns true if the machine halts when it enters this state (accepting or rejecting). */
    public boolean isHalting() {
        return accepting || rejecting;
    }

    /** Sets or clears the starting flag. */
    public void setStarting(boolean starting) {
        this.starting = starting;
    }

    /** Marks this state as accepting and automatically clears the rejecting flag. */
    public void setAccepting(boolean accepting) {
        this.accepting = accepting;
        if (accepting) this.rejecting = false;
    }

    /** Marks this state as rejecting and automatically clears the accepting flag. */
    public void setRejecting(boolean rejecting) {
        this.rejecting = rejecting;
        if (rejecting) this.accepting = false;
    }

    /** Two states are equal if and only if they have the same name. */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State other = (State) o;
        return name.equals(other.name);
    }

    /** Hash code is based on the name only, consistent with equals. */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /** Returns the state name followed by role labels in brackets, e.g. "q0 [start,accept]". */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        if (starting || accepting || rejecting) {
            sb.append(" [");
            boolean first = true;
            if (starting)  {
                sb.append("start");
                first = false;
            }
            if (accepting) {
                if (!first) {
                    sb.append(",");
                }
                sb.append("accept"); first = false;
            }
            if (rejecting) {
                if (!first){
                    sb.append(",");
                }
                sb.append("reject");
            }
            sb.append("]");
        }
        return sb.toString();
    }
}