package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
public class ExitCommand implements Command {
    @Override
    public String execute(String[] args) {
        return "Exiting the program...";
    }
}