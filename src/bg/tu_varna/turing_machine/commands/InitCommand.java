package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class InitCommand implements Command {
    private MachineRegistry registry;
    public InitCommand(MachineRegistry r) {
        this.registry = r;
    }
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: init <id> <input>";
        }
        registry.getExecution(Integer.parseInt(args[0])).init(args[1]);
        return "Initialized execution with input \"" + args[1] + "\"";
    }
}