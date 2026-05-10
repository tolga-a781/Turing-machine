package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class ResetCommand implements Command {
    private MachineRegistry registry;
    public ResetCommand(MachineRegistry r) {
        this.registry = r;
    }
    @Override public String execute(String[] args) {
        if (args.length < 1){
            return "Usage: reset <id>";
        }
        registry.getExecution(Integer.parseInt(args[0])).reset();
        return "Execution reset";
    }
}