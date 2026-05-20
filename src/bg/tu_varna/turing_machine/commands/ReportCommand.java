package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.TuringMachineRunner;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Runs a machine on a word and prints a full execution summary.
 * Usage: report id word [max=n]
 * Shows input, total steps, accept/reject result, halt reason, and final tape state.
 */
public class ReportCommand extends AbstractCommand {
    public ReportCommand(MachineRegistry r) {
        super(r);
    }
    /** Runs the machine and returns a summary of input, steps, result, reason, and final tape. */
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: report <id> <word> [max=<n>]";
        }
        int max = 10000;
        for (int i = 2; i < args.length; i++) {
            if (args[i].startsWith("max=")) {
                max = Integer.parseInt(args[i].substring(4));
            }
        }
        TuringMachineRunner turingMachineRunner = getRegistry().getExecution(Integer.parseInt(args[0]));
        turingMachineRunner.init(args[1]);
        turingMachineRunner.run(max);
        return "Input: " + args[1] + "\nSteps: " + turingMachineRunner.getSteps() + "\nResult: " + (turingMachineRunner.isAccepted() ? "ACCEPTED" : "REJECTED")
                + "\nReason: " + turingMachineRunner.getHaltReason() + "\nFinal " + turingMachineRunner.snapshotConfig();
    }
}
