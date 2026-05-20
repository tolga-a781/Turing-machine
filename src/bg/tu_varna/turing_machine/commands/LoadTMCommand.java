package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.models.TuringMachine;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Loads a machine from a .txt file and assigns it a new ID.
 * Usage: loadTM file.txt
 * Unlike open, this does not mark the file as currently open,
 * so save and close will not affect it.
 */
public class LoadTMCommand extends AbstractFileCommand {
    public LoadTMCommand(MachineRegistry r, MachineFile f) {
        super(r, f);
    }
    /** Loads a machine from the given .txt file and assigns it the next available ID. */
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: loadTM <file>";
        }
        try {
            TuringMachine m = machineFile.load(args[0], getRegistry().nextId());
            getRegistry().register(m);
            return "Loaded machine from " + args[0] + " (ID " + m.getId() + ")";
        } catch (Exception e) { return "Error: " + e.getMessage(); }
    }
}
