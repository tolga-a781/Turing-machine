package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.TuringMachineRunner;
import bg.tu_varna.turing_machine.services.MachineRegistry;
/**
 * Executes exactly one step of a machine.
 * Usage: step id
 * Prints the new state, head position, and tape after the step.
 * If the step causes a halt, prints the halt reason and accept/reject result instead.
 */
public class StepCommand extends AbstractCommand {
    public StepCommand(MachineRegistry r) {
        super(r);
    }
    /** Executes one step and prints the new configuration, or the halt reason if the machine stopped. */
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: step <id>";
        }
        TuringMachineRunner turingMachineRunner = getRegistry().getExecution(Integer.parseInt(args[0]));
        boolean moved = turingMachineRunner.step();
        if (!moved && turingMachineRunner.isHalted()){
            return "Halted: " + turingMachineRunner.getHaltReason() + " (accepted=" + turingMachineRunner.isAccepted() + ")";
        }
        return turingMachineRunner.snapshotConfig();
    }
}