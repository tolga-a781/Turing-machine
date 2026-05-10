package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class CheckDetCommand implements Command {
    private MachineRegistry registry;
    public CheckDetCommand(MachineRegistry r) {
        this.registry = r;
    }
    @Override public String execute(String[] args) {
        if (args.length < 1){
            return "Usage: checkDet <id>";
        }
        boolean det = registry.get(Integer.parseInt(args[0])).isDeterministic();
        if (det) return "Machine is deterministic";
        return "Machine is NOT deterministic";
    }
}