package bg.tu_varna.turing_machine.commands;

import bg.tu_varna.turing_machine.interfaces.Command;

public class ExitCommand implements Command {
    private String message;

    @Override
    public void execute() {
        this.message = "Exiting the app, goodbye!";
        System.exit(0);

    }

    public String getMessage() {
        return message;
    }
}
