package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.models.TuringMachine;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Creates a new empty Turing Machine with the given name.
 * Usage: newTM name
 * The machine is given the next available ID and starts with no states or transitions.
 */
public class NewTMCommand extends AbstractCommand {
    public NewTMCommand(MachineRegistry r) {
        super(r);
    }
    /** Creates an empty machine with the given name and reports its assigned ID. */
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: newTM <name>";
        }
        TuringMachine m = getRegistry().create(args[0]);
        return "Created machine " + m.getName() + " with ID " + m.getId();
    }
}
