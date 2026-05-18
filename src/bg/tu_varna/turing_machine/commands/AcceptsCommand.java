package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.TuringMachineRunner;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class AcceptsCommand extends AbstractCommand {
    public AcceptsCommand(MachineRegistry r) {
        super(r);
    }
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