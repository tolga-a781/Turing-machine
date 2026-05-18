package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class SaveAsCommand extends AbstractFileCommand {
    public SaveAsCommand(MachineRegistry r, MachineFile f) {
        super(r, f);
    }
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