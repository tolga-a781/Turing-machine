package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Resets a machine's execution back to its initial state.
 * Usage: reset id
 * Restores the original input tape, moves the head to position 0, and clears halt flags.
 * No new init call is needed; the same input is reused.
 */
public class ResetCommand extends AbstractCommand {
    public ResetCommand(MachineRegistry r) {
        super(r);
    }
    /** Resets the tape to the original input and returns the machine to the start state. */
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: reset <id>";
        }
        getRegistry().getExecution(Integer.parseInt(args[0])).reset();
        return "Execution reset";
    }
}
