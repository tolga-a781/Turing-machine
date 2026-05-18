package bg.tu_varna.turing_machine.services;

import bg.tu_varna.turing_machine.models.*;

import java.util.ArrayList;
import java.util.List;

public class TuringMachineRunner {
    private final TuringMachine machine;
    private Tape tape;
    private State currentState;
    private int steps;
    private boolean halted;
    private boolean accepted;
    private String haltReason;

    public TuringMachineRunner(TuringMachine machine) { this.machine = machine; }

    public void init(String input) {
        if (machine.getStartState() == null) {
            throw new IllegalStateException("Machine has no start state");
        }
        for (int i = 0; i < input.length(); i++) {
            Alphabet.validate(input.charAt(i));
        }
        this.tape = new Tape(input);
        this.currentState = machine.getStartState();
        this.steps = 0;
        this.halted = false;
        this.accepted = false;
        this.haltReason = null;
    }

    public boolean isInitialized() { return tape != null; }

    public boolean step() {
        ensureInitialized();
        if (halted) {
            return false;
        }
        if (currentState.isAccepting()) {
            halt(true, "reached accepting state " + currentState.getName());
            return false;
        }
        if (currentState.isRejecting()) {
            halt(false, "reached rejecting state " + currentState.getName());
            return false;
        }
        char symbol = tape.read();
        Transition t = machine.findTransition(currentState, symbol);
        if (t == null) {
            halt(false, "no transition for (" + currentState.getName() + ", " + symbol + ")");
            return false;
        }
        tape.write(t.getWrite());
        tape.move(t.getDirection());
        currentState = t.getTo();
        steps++;
        return true;
    }

    public void run(int maxSteps) {
        ensureInitialized();
        while (!halted && steps < maxSteps) {
            if (!step()) {
                break;
            }
        }
        if (!halted && steps >= maxSteps) {
            halt(false, "reached max steps " + maxSteps);
        }
    }

    public void reset() {
        if (tape == null) {
            return;
        }
        tape.reset();
        currentState = machine.getStartState();
        steps = 0;
        halted = false;
        accepted = false;
        haltReason = null;
    }

    public List<String> trace(String input, int k, int maxSteps) {
        init(input);
        List<String> configs = new ArrayList<>();
        configs.add(snapshotConfig());
        while (configs.size() < k && !halted && steps < maxSteps) {
            step();
            configs.add(snapshotConfig());
        }
        return configs;
    }

    public String snapshotConfig() {
        if (tape == null) {
            return "<not initialized>";
        }
        int left = tape.leftmost();
        int right = tape.rightmost();
        return "state=" + currentState.getName() + " head=" + tape.getHead() + " tape=[" + tape.snapshot(left, right) + "]";
    }

    private void halt(boolean accepted, String reason) {
        this.halted = true;
        this.accepted = accepted;
        this.haltReason = reason;
    }

    private void ensureInitialized() {
        if (tape == null) {
            throw new IllegalStateException("Execution not initialized: use init <id> <input>");
        }
    }

    public TuringMachine getMachine() {
        return machine;
    }
    public Tape getTape() {
        return tape;
    }
    public State getCurrentState() {
        return currentState;
    }
    public int getSteps() {
        return steps;
    }
    public boolean isHalted() {
        return halted;
    }
    public boolean isAccepted() {
        return accepted;
    }
    public String getHaltReason() {
        return haltReason;
    }
}