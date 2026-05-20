package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Saves any loaded machine to a specified .txt file by its ID.
 * Usage: saveTM id file.txt
 * Does not require a file to be currently open and does not change the open file record.
 */
public class SaveTMCommand extends AbstractFileCommand {
    public SaveTMCommand(MachineRegistry r, MachineFile f) {
        super(r, f);
    }
    /** Saves any registered machine by ID to the specified .txt file path. */
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: saveTM <id> <file>";
        }
        try {
            machineFile.save(getRegistry().get(Integer.parseInt(args[0])), args[1]);
            return "Saved machine " + args[0] + " to " + args[1];
        } catch (Exception e) { return "Error: " + e.getMessage(); }
    }
}
