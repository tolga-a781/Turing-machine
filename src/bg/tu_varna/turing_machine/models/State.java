package bg.tu_varna.turing_machine.models;

import java.util.Objects;

public class State {
    private String name;
    private boolean acceptingState;
    private boolean rejectingState;
    private boolean startingState;

    public State(String name) {
        this.name = name;
        this.startingState = false;
        this.acceptingState = false;
        this.rejectingState = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAcceptingState(boolean acceptingState) {
        this.acceptingState = acceptingState;
        if(acceptingState) {
            this.rejectingState = false;
        }
    }

    public void setRejectingState(boolean rejectingState) {
        this.rejectingState = rejectingState;
        if(rejectingState) {
            this.startingState = false;
        }
    }

    public void setStartingState(boolean startingState) {
        this.startingState = startingState;
    }
    public boolean isAcceptingState() {
        return acceptingState;
    }
    public boolean isRejectingState() {
        return rejectingState;
    }
    public boolean isStartingState() {
        return startingState;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ");
        if(startingState == true){
            sb.append("Starting State \n");
        }
        if(acceptingState == true){
            sb.append("Accepting State \n");
        }
        if(rejectingState == true){
            sb.append("Rejecting State \n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof State state)) return false;
        return Objects.equals(name, state.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

}


