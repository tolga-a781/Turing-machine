package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.TuringMachineRunner;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Checks whether a machine accepts a given input word.
 * Usage: accepts id word [max=n]
 * Initialises the machine, runs it up to max steps (default 10000),
 * and returns "Accepted" or "Rejected" with the halt reason.
 */
public class AcceptsCommand extends AbstractCommand {
    public AcceptsCommand(MachineRegistry r) {
        super(r);
    }
    /** Runs the machine on the given word and returns Accepted or Rejected with the halt reason. */
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: accepts <id> <word> [max=<n>]";
        }
        int max = 10000;
        for (int i = 2; i < args.length; i++)
            if (args[i].startsWith("max=")) {
            max = Integer.parseInt(args[i].substring(4));
        }
        TuringMachineRunner turingMachineRunner = getRegistry().getExecution(Integer.parseInt(args[0]));
        turingMachineRunner.init(args[1]);
        turingMachineRunner.run(max);
        if (turingMachineRunner.isAccepted()) {
            return "Accepted";
        }
        return "Rejected (" + turingMachineRunner.getHaltReason() + ")";
    }
}
