package bg.tu_varna.turing_machine.interfaces;

/**
 * Contract that every command must implement.
 * Receives the argument tokens that followed the command keyword
 * and returns a string to be printed to the user.
 * Returning an empty string means nothing is printed.
 */
public interface Command {
    /** Runs the command with the given argument tokens and returns the output to display. */
    String execute(String[] args);
}