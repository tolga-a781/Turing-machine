package bg.tu_varna.turing_machine.models;

import bg.tu_varna.turing_machine.enums.Direction;

import java.util.TreeMap;

/**
 * Models the infinite tape using a TreeMap that only stores non-blank cells.
 * Any position absent from the map reads as blank ('_'), making the tape
 * effectively infinite without wasting memory.
 * Writing a blank removes the cell from the map instead of storing it.
 * The reset method restores the original input and moves the head back to position 0.
 */
public class Tape {
    /** Stores only the non-blank cells; absent positions read as blank. */
    private final TreeMap<Integer, Character> cells = new TreeMap<>();
    /** Current head position; can be negative or positive. */
    private int head = 0;
    /** The input string loaded at construction time, used by reset. */
    private String originalInput;

    /**
     * Creates a tape and loads the given input string starting at position 0.
     * @param input the initial tape content; treated as empty if null.
     */
    public Tape(String input) {
        if (input == null) {
            this.originalInput = "";
        }
        else{
            this.originalInput = input;
        }
        loadInput();
    }

    /** Clears the tape and reloads the original input from position 0. */
    private void loadInput() {
        cells.clear();
        head = 0;
        for (int i = 0; i < originalInput.length(); i++) {
            cells.put(i, originalInput.charAt(i));
        }
    }

    /** Returns the symbol at the current head position, or blank if the cell is empty. */
    public char read() {
        return cells.getOrDefault(head, Alphabet.BLANK);
    }

    /**
     * Writes a symbol at the current head position.
     * @param symbol the symbol to write; writing blank removes the cell from the map.
     */
    public void write(char symbol) {
        if (symbol == Alphabet.BLANK) {
            cells.remove(head);
        }
        else{
            cells.put(head, symbol);
        }
    }

    /**
     * Moves the head one cell left (L), one cell right (R), or keeps it in place (S).
     * @param d the direction to move.
     */
    public void move(Direction d) {
        switch (d) {
            case L -> head--;
            case R -> head++;
            case S -> { }
        }
    }

    /** Returns the current head position as a cell index. */
    public int getHead() {
        return head;
    }

    /** Returns the leftmost position that should be shown: the minimum of the head and the first occupied cell. */
    public int leftmost() {
        if (cells.isEmpty()){
            return head;
        }
        return Math.min(head, cells.firstKey());
    }

    /** Returns the rightmost position that should be shown: the maximum of the head and the last occupied cell. */
    public int rightmost() {
        if (cells.isEmpty()) {
            return head;
        }
        return Math.max(head, cells.lastKey());
    }

    /**
     * Returns a string of tape symbols from position from to position to (inclusive).
     * @param from the start cell index.
     * @param to the end cell index.
     */
    public String snapshot(int from, int to) {
        StringBuilder sb = new StringBuilder();
        for (int i = from; i <= to; i++){
            sb.append(cells.getOrDefault(i, Alphabet.BLANK));
        }
        return sb.toString();
    }

    /** Restores the tape to the original input and moves the head back to position 0. */
    public void reset() {
        loadInput(); }
}