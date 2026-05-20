package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.models.TuringMachine;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Lists all machines currently loaded in the registry.
 * Usage: list
 * Prints each machine's ID and name, one per line.
 */
public class ListCommand extends AbstractCommand {
    public ListCommand(MachineRegistry r) {
        super(r);
    }
    /** Prints each machine ID and name, or a message if no machines are loaded. */
    @Override public String execute(String[] args) {
        if (getRegistry().all().isEmpty()) {
            return "No machines loaded";
        }
        StringBuilder sb = new StringBuilder("Loaded machines:");
        for (TuringMachine m : getRegistry().all()) sb.append("\n  ").append(m.getId()).append(" - ").append(m.getName());
        return sb.toString();
    }
}
