package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.models.*;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Prints the full definition of a machine: name, ID, all states, and all transitions.
 * Usage: print id
 */
public class PrintCommand extends AbstractCommand {
    public PrintCommand(MachineRegistry r) {
        super(r);
    }
    /** Prints the machine name, all states with their role labels, and all transitions. */
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: print <id>";
        }
        TuringMachine m = getRegistry().get(Integer.parseInt(args[0]));
        StringBuilder sb = new StringBuilder();
        sb.append("Machine ").append(m.getId()).append(": ").append(m.getName()).append("\n");
        sb.append("States:\n");
        for (State s : m.getStates()) {
            sb.append("  ").append(s).append("\n");
        }
        sb.append("Transitions:\n");
        for (Transition t : m.getTransitions()) {
            sb.append("  ").append(t).append("\n");
        }
        return sb.toString();
    }
}
