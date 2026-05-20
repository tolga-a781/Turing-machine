package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Initialises a machine on a given input string.
 * Usage: init id input
 * Must be called before step, run, status, tape, or reset can be used.
 */
public class InitCommand extends AbstractCommand {
    public InitCommand(MachineRegistry r) {
        super(r);
    }
    /** Initialises the runner on the given input and confirms the input that was set. */
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: init <id> <input>";
        }
        getRegistry().getExecution(Integer.parseInt(args[0])).init(args[1]);
        return "Initialized execution with input \"" + args[1] + "\"";
    }
}
