package bg.tu_varna.turing_machine.models;

import bg.tu_varna.turing_machine.enums.Direction;

import java.util.TreeMap;

public class Tape {
    private final TreeMap<Integer, Character> cells = new TreeMap<>();
    private int head = 0;
    private String originalInput;

    public Tape(String input) {
        if (input == null) {
            this.originalInput = "";
        }
        else{
            this.originalInput = input;
        }
        loadInput();
    }

    private void loadInput() {
        cells.clear();
        head = 0;
        for (int i = 0; i < originalInput.length(); i++) {
            cells.put(i, originalInput.charAt(i));
        }
    }

    public char read() {
        return cells.getOrDefault(head, Alphabet.BLANK);
    }

    public void write(char symbol) {
        if (symbol == Alphabet.BLANK) {
            cells.remove(head);
        }
        else{
            cells.put(head, symbol);
        }
    }

    public void move(Direction d) {
        switch (d) {
            case L -> head--;
            case R -> head++;
            case S -> { }
        }
    }

    public int getHead() {
        return head;
    }

    public int leftmost() {
        if (cells.isEmpty()){
            return head;
        }
        return Math.min(head, cells.firstKey());
    }

    public int rightmost() {
        if (cells.isEmpty()) {
            return head;
        }
        return Math.max(head, cells.lastKey());
    }

    public String snapshot(int from, int to) {
        StringBuilder sb = new StringBuilder();
        for (int i = from; i <= to; i++){
            sb.append(cells.getOrDefault(i, Alphabet.BLANK));
        }
        return sb.toString();
    }

    public void reset() {
        loadInput(); }
}