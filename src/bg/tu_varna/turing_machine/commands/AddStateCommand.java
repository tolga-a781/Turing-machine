package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class AddStateCommand implements Command {
    private MachineRegistry registry;
    public AddStateCommand(MachineRegistry r) {
        this.registry = r;
    }
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: addState <id> <state>";
        }
        registry.get(Integer.parseInt(args[0])).addState(args[1]);
        return "Added state " + args[1];
    }
}