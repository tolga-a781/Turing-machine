package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class AddAcceptCommand implements Command {
    private MachineRegistry registry;
    public AddAcceptCommand(MachineRegistry r) {
        this.registry = r;
    }
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: addAccept <id> <state>";
        }
        registry.get(Integer.parseInt(args[0])).markAccepting(args[1]);
        return "Marked " + args[1] + " as accepting";
    }
}