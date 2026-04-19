package bg.tu_varna.turing_machine.models;

public class Transition {
    private String fromState;
    private String read;
    private String toState;
    private String write;
    private Direction direction;

    public Transition(String fromState, String read, String toState, String write, Direction direction) {
        this.fromState = fromState;
        this.read = read;
        this.toState = toState;
        this.write = write;
        this.direction = direction;
    }

    public String getFromState() { return fromState; }
    public String getRead() { return read; }
    public String getToState() { return toState; }
    public String getWrite() { return write; }
    public Direction getDirection() { return direction; }
}