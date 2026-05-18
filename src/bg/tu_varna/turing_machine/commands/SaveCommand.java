package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class SaveCommand extends AbstractFileCommand {
    public SaveCommand(MachineRegistry r, MachineFile f) {
        super(r, f);
    }
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