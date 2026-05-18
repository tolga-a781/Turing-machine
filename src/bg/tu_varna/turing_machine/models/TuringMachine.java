package bg.tu_varna.turing_machine.models;

import bg.tu_varna.turing_machine.enums.Direction;

import java.util.*;

public class TuringMachine {
    private int id;
    private String name;
    private final Map<String, State> states = new LinkedHashMap<>();
    private State startState;
    private final List<Transition> transitions = new ArrayList<>();

    public TuringMachine(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public State addState(String name) {
        State existing = states.get(name);
        if (existing != null){
            return existing;
        }
        State created = new State(name);
        states.put(name, created);
        return created;
    }

    public State getState(String name) {
        State s = states.get(name);
        if (s == null) {
            throw new IllegalArgumentException("Unknown state: " + name);
        }
        return s;
    }

    public Collection<State> getStates() {
        return states.values();
    }

    public void setStart(String name) {
        State s = getState(name);
        if (startState != null && !startState.equals(s)) {
            startState.setStarting(false);
        }
        s.setStarting(true);
        startState = s;
    }

    public State getStartState() {
        return startState;
    }

    public void markAccepting(String name) {
        getState(name).setAccepting(true);
    }
    public void markRejecting(String name) {
        getState(name).setRejecting(true);
    }

    public List<State> getAcceptStates() {
        List<State> r = new ArrayList<>();
        for (State s : states.values()) {
            if (s.isAccepting()) {
                r.add(s);
            }
        }
        return r;
    }

    public List<State> getRejectStates() {
        List<State> r = new ArrayList<>();
        for (State s : states.values()) {
            if (s.isRejecting()) {
                r.add(s);
            }
        }
        return r;
    }

    public void addTransition(String from, char read, String to, char write, Direction direction) {
        Alphabet.validate(read);
        Alphabet.validate(write);
        transitions.add(new Transition(getState(from), read, getState(to), write, direction));
    }

    public void removeTransition(String from, char read) {
        State f = getState(from);
        transitions.removeIf(t -> t.getFrom().equals(f) && t.getRead() == read);
    }

    public Transition findTransition(State state, char symbol) {
        for (Transition t : transitions) {
            if (t.matches(state, symbol)) {
                return t;
            }
        }
        return null;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

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