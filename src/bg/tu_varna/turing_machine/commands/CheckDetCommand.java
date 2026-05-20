package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Checks whether a machine is deterministic.
 * Usage: checkDet id
 * A machine is deterministic if no two transitions share the same source state
 * and read symbol.
 */
public class CheckDetCommand extends AbstractCommand {
    public CheckDetCommand(MachineRegistry r) {
        super(r);
    }
    /** Checks all transitions for duplicate state+symbol pairs and reports the result. */
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: checkDet <id>";
        }
        boolean det = getRegistry().get(Integer.parseInt(args[0])).isDeterministic();
        if (det) return "Machine is deterministic";
        return "Machine is NOT deterministic";
    }
}
