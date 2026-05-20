package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Marks an existing state as accepting.
 * Usage: addAccept id state
 * The state must already exist in the machine.
 */
public class AddAcceptCommand extends AbstractCommand {
    public AddAcceptCommand(MachineRegistry r) {
        super(r);
    }
    /** Marks the named state as accepting and confirms it. */
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: addAccept <id> <state>";
        }
        getRegistry().get(Integer.parseInt(args[0])).markAccepting(args[1]);
        return "Marked " + args[1] + " as accepting";
    }
}
