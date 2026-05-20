package bg.tu_varna.turing_machine.services;

import bg.tu_varna.turing_machine.models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls step-by-step execution of a Turing Machine on an input string.
 * Call init first to validate the input, create the tape, and set the start state.
 * step applies one transition rule and halts the machine if no rule matches.
 * run loops step until the machine halts or the max step limit is reached.
 * trace re-initialises on an input and collects up to k configuration snapshots.
 * reset restores the tape and start state without needing a new init call.
 */
public class TuringMachineRunner {
    /** The machine definition this runner executes. */
    private final TuringMachine machine;
    /** The tape for the current execution; null until init is called. */
    private Tape tape;
    /** The state the machine is currently in. */
    private State currentState;
    /** Number of transitions applied since the last init or reset. */
    private int steps;
    /** True once the machine has entered a halting condition. */
    private boolean halted;
    /** True if the machine halted by entering an accepting state. */
    private boolean accepted;
    /** Human-readable explanation of why the machine stopped. */
    private String haltReason;

    /** Creates a runner for the given machine. init must be called before execution can start. */
    public TuringMachineRunner(TuringMachine machine) { this.machine = machine; }

    /**
     * Prepares the machine to run on the given input string.
     * Validates every symbol, creates a fresh tape, and resets all execution state.
     * @param input the string to place on the tape; every character must be a valid symbol.
     */
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

    /** Returns true if init has been called and the tape exists. */
    public boolean isInitialized() { return tape != null; }

    /**
     * Applies one transition rule. Returns true if a step was taken, or false if the machine halted.
     * Halts if the current state is accepting or rejecting, or if no transition matches.
     */
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

    /**
     * Repeatedly calls step until the machine halts or maxSteps is reached.
     * @param maxSteps the maximum number of steps before forcing a reject halt.
     */
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

    /** Restores the tape to the original input and returns to the start state. Does nothing if not initialised. */
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

    /**
     * Re-initialises on the given input and collects up to k configuration snapshots.
     * @param input the input string to run on.
     * @param k the maximum number of snapshots to collect.
     * @param maxSteps the step limit to prevent infinite loops.
     */
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

    /** Returns the current configuration as a string showing the state name, head position, and tape contents. */
    public String snapshotConfig() {
        if (tape == null) {
            return "<not initialized>";
        }
        int left = tape.leftmost();
        int right = tape.rightmost();
        return "state=" + currentState.getName() + " head=" + tape.getHead() + " tape=[" + tape.snapshot(left, right) + "]";
    }

    /** Sets the halted flag and records whether the machine accepted and why it stopped. */
    private void halt(boolean accepted, String reason) {
        this.halted = true;
        this.accepted = accepted;
        this.haltReason = reason;
    }

    /** Throws IllegalStateException if init has not been called yet. */
    private void ensureInitialized() {
        if (tape == null) {
            throw new IllegalStateException("Execution not initialized: use init <id> <input>");
        }
    }

    /** Returns the machine this runner is executing. */
    public TuringMachine getMachine() {
        return machine;
    }
    /** Returns the current tape, or null if init has not been called. */
    public Tape getTape() {
        return tape;
    }
    /** Returns the state the machine is currently in. */
    public State getCurrentState() {
        return currentState;
    }
    /** Returns the number of steps taken since the last init or reset. */
    public int getSteps() {
        return steps;
    }
    /** Returns true if the machine has stopped (accepted, rejected, or hit the step limit). */
    public boolean isHalted() {
        return halted;
    }
    /** Returns true if the machine halted in an accepting state. */
    public boolean isAccepted() {
        return accepted;
    }
    /** Returns a short description of why the machine stopped, or null if still running. */
    public String getHaltReason() {
        return haltReason;
    }
}