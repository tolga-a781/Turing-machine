package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.models.Tape;
import bg.tu_varna.turing_machine.services.TuringMachineRunner;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class TapeCommand implements Command {
    private MachineRegistry registry;
    public TapeCommand(MachineRegistry r) { this.registry = r; }
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: tape <id> [from=<a>] [to=<b>]";
        }
        TuringMachineRunner turingMachineRunner = registry.getExecution(Integer.parseInt(args[0]));
        if (!turingMachineRunner.isInitialized()) {
            return "Not initialized";
        }
        Tape tape = turingMachineRunner.getTape();
        int from = tape.leftmost(), to = tape.rightmost();
        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("from=")) from = Integer.parseInt(args[i].substring(5));
            if (args[i].startsWith("to="))   to   = Integer.parseInt(args[i].substring(3));
        }
        return "[" + from + ".." + to + "] " + tape.snapshot(from, to);
    }
}