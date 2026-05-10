package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.TuringMachineRunner;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class ReportCommand implements Command {
    private MachineRegistry registry;
    public ReportCommand(MachineRegistry r) { this.registry = r; }
    @Override public String execute(String[] args) {
        if (args.length < 2) return "Usage: report <id> <word> [max=<n>]";
        int max = 10000;
        for (int i = 2; i < args.length; i++) if (args[i].startsWith("max=")) max = Integer.parseInt(args[i].substring(4));
        TuringMachineRunner turingMachineRunner = registry.getExecution(Integer.parseInt(args[0]));
        turingMachineRunner.init(args[1]);
        turingMachineRunner.run(max);
        return "Input: " + args[1] + "\nSteps: " + turingMachineRunner.getSteps() + "\nResult: " + (turingMachineRunner.isAccepted() ? "ACCEPTED" : "REJECTED")
                + "\nReason: " + turingMachineRunner.getHaltReason() + "\nFinal " + turingMachineRunner.snapshotConfig();
    }
}