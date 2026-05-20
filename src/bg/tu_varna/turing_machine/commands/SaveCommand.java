package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Saves the currently open machine back to its original .txt file.
 * Usage: save
 * Overwrites the file that was loaded with open, including any changes made during the session.
 * Returns an error if no file is currently open.
 */
public class SaveCommand extends AbstractFileCommand {
    public SaveCommand(MachineRegistry r, MachineFile f) {
        super(r, f);
    }
    /** Overwrites the currently open .txt file with the latest state of the machine. */
    @Override public String execute(String[] args) {
        String err = requireOpenFile();
        if (err != null) {
            return err;
        }
        try {
            machineFile.save(getRegistry().get(getRegistry().getCurrentFileMachineId()), getRegistry().getCurrentFilePath());
            return "Successfully saved " + getRegistry().getCurrentFilePath();
        } catch (Exception e) {
            return "Error saving: " + e.getMessage();
        }
    }
}
