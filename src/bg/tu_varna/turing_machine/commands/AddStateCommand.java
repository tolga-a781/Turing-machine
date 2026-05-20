package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Adds a new state to a machine.
 * Usage: addState id state
 * If a state with the same name already exists the call is silently ignored.
 */
public class AddStateCommand extends AbstractCommand {
    public AddStateCommand(MachineRegistry r) {
        super(r);
    }
    /** Adds a state to the machine and confirms the addition. */
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: addState <id> <state>";
        }
        getRegistry().get(Integer.parseInt(args[0])).addState(args[1]);
        return "Added state " + args[1];
    }
}
