package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.models.Tape;
import bg.tu_varna.turing_machine.services.TuringMachineRunner;
import bg.tu_varna.turing_machine.services.MachineRegistry;
/**
 * Displays the tape contents of an initialised machine.
 * Usage: tape id [from=a] [to=b]
 * Defaults to the range from the leftmost to rightmost occupied cell or head position.
 * Optional from and to parameters override the displayed range.
 */
public class TapeCommand extends AbstractCommand {
    public TapeCommand(MachineRegistry r) {
        super(r);
    }
    /** Prints the tape contents between the leftmost and rightmost relevant cells, or a custom range. */
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: tape <id> [from=<a>] [to=<b>]";
        }
        TuringMachineRunner turingMachineRunner = getRegistry().getExecution(Integer.parseInt(args[0]));
        if (!turingMachineRunner.isInitialized()) {
            return "Not initialized";
        }
        Tape tape = turingMachineRunner.getTape();
        int from = tape.leftmost(), to = tape.rightmost();
        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("from=")) {
                from = Integer.parseInt(args[i].substring(5));
            }
            if (args[i].startsWith("to=")) {
                to   = Integer.parseInt(args[i].substring(3));
            }
        }
        return "[" + from + ".." + to + "] " + tape.snapshot(from, to);
    }
}