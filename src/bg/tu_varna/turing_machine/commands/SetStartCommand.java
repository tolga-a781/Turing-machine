package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class SetStartCommand implements Command {
    private MachineRegistry registry;
    public SetStartCommand(MachineRegistry r) { this.registry = r; }
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: setStart <id> <state>";
        }
        registry.get(Integer.parseInt(args[0])).setStart(args[1]);
        return "Set start state to " + args[1];
    }
}