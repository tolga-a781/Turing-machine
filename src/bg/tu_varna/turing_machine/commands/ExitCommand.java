package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;

/**
 * Handles the exit command.
 * Usage: exit
 * Returns a farewell message; the REPL loop in Application detects this
 * keyword and terminates the program.
 */
public class ExitCommand implements Command {
    /** Returns a farewell message; the REPL loop in Application detects this and exits. */
    @Override
    public String execute(String[] args) {
        return "Exiting the program...";
    }
}
