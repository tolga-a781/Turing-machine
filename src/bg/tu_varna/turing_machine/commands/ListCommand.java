package bg.tu_varna.turing_machine.commands;

import bg.tu_varna.turing_machine.interfaces.Command;

public class ListCommand implements Command {
    @Override
    public String execute() {
        return "List of Machine IDs";
    }
}
