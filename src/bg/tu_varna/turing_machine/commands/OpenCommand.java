package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.models.TuringMachine;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Opens a .txt machine file and marks it as currently open.
 * Usage: open file.txt
 * After this command, save writes back to the same path and close releases the reference.
 */
public class OpenCommand extends AbstractFileCommand {
    public OpenCommand(MachineRegistry r, MachineFile f) {
        super(r, f);
    }
    /** Loads a .txt file, registers the machine, and marks the file as currently open. */
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: open <file>";
        }
        try {
            TuringMachine m = machineFile.load(args[0], getRegistry().nextId());
            getRegistry().register(m);
            getRegistry().setCurrentFile(args[0], m.getId());
            return "Successfully opened " + args[0] + " (machine ID " + m.getId() + ")";
        } catch (Exception e) {
            return "Error opening file: " + e.getMessage();
        }
    }
}
