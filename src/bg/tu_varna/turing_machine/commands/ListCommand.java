package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.models.TuringMachine;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class ListCommand implements Command {
    private MachineRegistry registry;
    public ListCommand(MachineRegistry r) {
        this.registry = r;
    }
    @Override public String execute(String[] args) {
        if (registry.all().isEmpty()) {
            return "No machines loaded";
        }
        StringBuilder sb = new StringBuilder("Loaded machines:");
        for (TuringMachine m : registry.all()) sb.append("\n  ").append(m.getId()).append(" - ").append(m.getName());
        return sb.toString();
    }
}