package bg.tu_varna.turing_machine.models;

import java.util.Objects;

public class State {
    private final String name;
    private boolean starting;
    private boolean accepting;
    private boolean rejecting;

    public State(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }
    public boolean isStarting() {
        return starting;
    }
    public boolean isAccepting() {
        return accepting;
    }
    public boolean isRejecting() {
        return rejecting;
    }
    public boolean isHalting() {
        return accepting || rejecting;
    }

    public void setStarting(boolean starting) {
        this.starting = starting;
    }

    public void setAccepting(boolean accepting) {
        this.accepting = accepting;
        if (accepting) this.rejecting = false;
    }

    public void setRejecting(boolean rejecting) {
        this.rejecting = rejecting;
        if (rejecting) this.accepting = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State other = (State) o;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

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