package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class RemoveTransCommand implements Command {
    private MachineRegistry registry;
    public RemoveTransCommand(MachineRegistry r) { this.registry = r; }
    @Override public String execute(String[] args) {
        if (args.length < 3) {
            return "Usage: removeTrans <id> <q> <read>";
        }
        registry.get(Integer.parseInt(args[0])).removeTransition(args[1], args[2].charAt(0));
        return "Removed transition";
    }
}