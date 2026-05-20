package bg.tu_varna.turing_machine.models;

import bg.tu_varna.turing_machine.enums.Direction;

import java.util.*;

/**
 * Holds the full definition of one Turing Machine: ID, name, states, and transitions.
 * States are stored in a LinkedHashMap so they iterate in insertion order.
 * Adding a state that already exists returns the existing object without creating a duplicate.
 * isDeterministic checks that no two transitions share the same source state and read symbol.
 */
public class TuringMachine {
    /** Numeric identifier, unique within a registry session. */
    private int id;
    /** Human-readable name shown in list and report output. */
    private String name;
    /** All states in insertion order, keyed by name. */
    private final Map<String, State> states = new LinkedHashMap<>();
    /** The designated start state, or null if none has been set. */
    private State startState;
    /** All transition rules in the order they were added. */
    private final List<Transition> transitions = new ArrayList<>();

    /**
     * Creates a machine with the given ID and name, with no states or transitions yet.
     * @param id the unique numeric identifier for this machine.
     * @param name the display name of this machine.
     */
    public TuringMachine(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /** Returns the numeric ID of this machine. */
    public int getId() {
        return id;
    }
    /** Returns the display name of this machine. */
    public String getName() {
        return name;
    }
    /**
     * Sets a new display name for this machine.
     * @param name the new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a state with the given name. If a state with that name already exists, returns it unchanged.
     * @param name the name of the state to add or retrieve.
     */
    public State addState(String name) {
        State existing = states.get(name);
        if (existing != null){
            return existing;
        }
        State created = new State(name);
        states.put(name, created);
        return created;
    }

    /**
     * Returns the state with the given name. Throws IllegalArgumentException if it does not exist.
     * @param name the name of the state to look up.
     */
    public State getState(String name) {
        State s = states.get(name);
        if (s == null) {
            throw new IllegalArgumentException("Unknown state: " + name);
        }
        return s;
    }

    /** Returns all states in the order they were added. */
    public Collection<State> getStates() {
        return states.values();
    }

    /**
     * Sets the start state. If a different start state was already set, its starting flag is cleared.
     * @param name the name of the state to mark as start; must already exist.
     */
    public void setStart(String name) {
        State s = getState(name);
        if (startState != null && !startState.equals(s)) {
            startState.setStarting(false);
        }
        s.setStarting(true);
        startState = s;
    }

    /** Returns the current start state, or null if none has been set yet. */
    public State getStartState() {
        return startState;
    }

    /** Marks the named state as accepting. The state must already exist. */
    public void markAccepting(String name) {
        getState(name).setAccepting(true);
    }
    /** Marks the named state as rejecting. The state must already exist. */
    public void markRejecting(String name) {
        getState(name).setRejecting(true);
    }

    /** Returns a list of all states that are marked as accepting. */
    public List<State> getAcceptStates() {
        List<State> r = new ArrayList<>();
        for (State s : states.values()) {
            if (s.isAccepting()) {
                r.add(s);
            }
        }
        return r;
    }

    /** Returns a list of all states that are marked as rejecting. */
    public List<State> getRejectStates() {
        List<State> r = new ArrayList<>();
        for (State s : states.values()) {
            if (s.isRejecting()) {
                r.add(s);
            }
        }
        return r;
    }

    /**
     * Validates both symbols and adds a new transition rule.
     * @param from the source state name.
     * @param read the symbol to read.
     * @param to the destination state name.
     * @param write the symbol to write.
     * @param direction the direction to move the head.
     */
    public void addTransition(String from, char read, String to, char write, Direction direction) {
        Alphabet.validate(read);
        Alphabet.validate(write);
        transitions.add(new Transition(getState(from), read, getState(to), write, direction));
    }

    /**
     * Removes all transitions from the given state on the given read symbol.
     * @param from the source state name.
     * @param read the symbol that was read.
     */
    public void removeTransition(String from, char read) {
        State f = getState(from);
        transitions.removeIf(t -> t.getFrom().equals(f) && t.getRead() == read);
    }

    /**
     * Finds and returns the first transition that matches the given state and symbol. Returns null if none found.
     * @param state the current machine state.
     * @param symbol the symbol currently under the head.
     */
    public Transition findTransition(State state, char symbol) {
        for (Transition t : transitions) {
            if (t.matches(state, symbol)) {
                return t;
            }
        }
        return null;
    }

    /** Returns the full list of transition rules in the order they were added. */
    public List<Transition> getTransitions() {
        return transitions;
    }

    /** Returns true if no two transitions share the same source state and read symbol. */
    public boolean isDeterministic() {
        Set<String> seen = new HashSet<>();
        for (Transition t : transitions) {
            String key = t.getFrom().getName() + "|" + t.getRead();
            if (!seen.add(key)) {
                return false;
            }
        }
        return true;
    }
}