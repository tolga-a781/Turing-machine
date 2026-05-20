package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.TuringMachineRunner;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Runs a machine from its current position until it halts or hits the step limit.
 * Usage: run id [max=n]
 * The machine must have been initialised with init first. Default limit is 10000 steps.
 */
public class RunCommand extends AbstractCommand {
    public RunCommand(MachineRegistry r) {
        super(r);
    }
    /** Runs the machine from its current position until it halts or hits the step limit. */
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: run <id> [max=<n>]";
        }
        int max = parseMax(args, 1, 10000);
        TuringMachineRunner turingMachineRunner = getRegistry().getExecution(Integer.parseInt(args[0]));
        turingMachineRunner.run(max);
        return "Halted after " + turingMachineRunner.getSteps() + " steps. accepted=" + turingMachineRunner.isAccepted() + ". reason: " + turingMachineRunner.getHaltReason();
    }
    private int parseMax(String[] args, int from, int def) {
        for (int i = from; i < args.length; i++) {
            if (args[i].startsWith("max=")) {
                return Integer.parseInt(args[i].substring(4));
            }
        }
        return def;
    }
}
