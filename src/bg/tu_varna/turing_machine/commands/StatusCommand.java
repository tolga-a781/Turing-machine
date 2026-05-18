package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.TuringMachineRunner;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class StatusCommand extends AbstractCommand {
    public StatusCommand(MachineRegistry r) {

        super(r);
    }
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: status <id>";
        }
        TuringMachineRunner turingMachineRunner = getRegistry().getExecution(Integer.parseInt(args[0]));
        if (!turingMachineRunner.isInitialized()) {
            return "Not initialized";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("steps=").append(turingMachineRunner.getSteps()).append(" halted=").append(turingMachineRunner.isHalted());
        if (turingMachineRunner.isHalted()){
            sb.append(" accepted=").append(turingMachineRunner.isAccepted()).append(" reason=").append(turingMachineRunner.getHaltReason());
        }
        sb.append("\n").append(turingMachineRunner.snapshotConfig());
        return sb.toString();
    }
}