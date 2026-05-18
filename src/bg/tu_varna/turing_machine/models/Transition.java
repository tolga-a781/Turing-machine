package bg.tu_varna.turing_machine.models;

import bg.tu_varna.turing_machine.enums.Direction;

import java.util.Objects;

public class Transition {
    private  State from;
    private  char read;
    private  State to;
    private  char write;
    private  Direction direction;

    public Transition(State from, char read, State to, char write, Direction direction) {
        this.from = Objects.requireNonNull(from);
        this.to = Objects.requireNonNull(to);
        this.direction = Objects.requireNonNull(direction);
        this.read = read;
        this.write = write;
    }

    public State getFrom() {

        return from;
    }
    public char getRead() {

        return read;
    }
    public State getTo() {

        return to;
    }
    public char getWrite() {

        return write;
    }
    public Direction getDirection() {

        return direction;
    }

    public boolean matches(State state, char symbol) {

        return from.equals(state) && read == symbol;
    }

    @Override
    public String toString() {
        return "delta(" + from.getName() + ", " + read + ") = (" + to.getName() + ", " + write + ", " + direction + ")";
    }
}