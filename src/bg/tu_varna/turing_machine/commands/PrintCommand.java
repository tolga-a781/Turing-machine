package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.models.*;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class PrintCommand implements Command {
    private MachineRegistry registry;
    public PrintCommand(MachineRegistry r) {
        this.registry = r;
    }
    @Override public String execute(String[] args) {
        if (args.length < 1){
            return "Usage: print <id>";
        }
        TuringMachine m = registry.get(Integer.parseInt(args[0]));
        StringBuilder sb = new StringBuilder();
        sb.append("Machine ").append(m.getId()).append(": ").append(m.getName()).append("\n");
        sb.append("States:\n");
        for (State s : m.getStates()) sb.append("  ").append(s).append("\n");
        sb.append("Transitions:\n");
        for (Transition t : m.getTransitions()) sb.append("  ").append(t).append("\n");
        return sb.toString();
    }
}