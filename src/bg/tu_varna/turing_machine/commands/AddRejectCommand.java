package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Marks an existing state as rejecting.
 * Usage: addReject id state
 * The state must already exist. Setting it as rejecting clears any accepting flag it had.
 */
public class AddRejectCommand extends AbstractCommand {
    public AddRejectCommand(MachineRegistry r) {
        super(r);
    }
    /** Marks the named state as rejecting and confirms it. */
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: addReject <id> <state>";
        }
        getRegistry().get(Integer.parseInt(args[0])).markRejecting(args[1]);
        return "Marked " + args[1] + " as rejecting";
    }
}
