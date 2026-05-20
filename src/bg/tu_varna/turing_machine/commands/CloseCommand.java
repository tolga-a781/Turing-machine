package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Closes the currently open .txt file.
 * Usage: close
 * Clears the file path from the registry. The machine stays in memory
 * and can still be used by its ID. Returns an error if no file is open.
 */
public class CloseCommand extends AbstractCommand {
    public CloseCommand(MachineRegistry r) {
        super(r);
    }
    /** Clears the currently open file from the registry and confirms the path that was closed. */
    @Override public String execute(String[] args) {
        if (!getRegistry().hasOpenFile()) {
            return "No file is currently open";
        }
        String path = getRegistry().getCurrentFilePath();
        getRegistry().clearCurrentFile();
        return "Successfully closed " + path;
    }
}
