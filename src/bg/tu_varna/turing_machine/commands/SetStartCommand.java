package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Sets the start state of a machine.
 * Usage: setStart id state
 * The state must already exist. If another start state was set before,
 * its starting flag is cleared automatically.
 */
public class SetStartCommand extends AbstractCommand {
    public SetStartCommand(MachineRegistry r) {
        super(r);
    }
    /** Sets the start state of the machine, clearing any previously set start state. */
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: setStart <id> <state>";
        }
        getRegistry().get(Integer.parseInt(args[0])).setStart(args[1]);
        return "Set start state to " + args[1];
    }
}
