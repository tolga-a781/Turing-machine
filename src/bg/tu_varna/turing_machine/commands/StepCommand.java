package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.TuringMachineRunner;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class StepCommand extends AbstractCommand {
    public StepCommand(MachineRegistry r) {
        super(r);
    }
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