package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Saves the currently open machine to a new .txt file path.
 * Usage: save as file.txt
 * Does not change which file is considered currently open.
 * Returns an error if no file is open.
 */
public class SaveAsCommand extends AbstractFileCommand {
    public SaveAsCommand(MachineRegistry r, MachineFile f) {
        super(r, f);
    }
    /** Saves the currently open machine to the specified .txt path without changing the open file. */
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: save as <file>";
        }
        String err = requireOpenFile();
        if (err != null) {
            return err;
        }
        try {
            machineFile.save(getRegistry().get(getRegistry().getCurrentFileMachineId()), args[0]);
            return "Successfully saved " + args[0];
        } catch (Exception e) { return "Error saving: " + e.getMessage(); }
    }
}
