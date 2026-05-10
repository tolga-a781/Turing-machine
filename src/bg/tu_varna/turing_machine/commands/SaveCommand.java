package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class SaveCommand implements Command {
    private  MachineRegistry registry;
    private  MachineFile fileIO;
    public SaveCommand(MachineRegistry r, MachineFile f) { this.registry = r; this.fileIO = f; }
    @Override public String execute(String[] args) {
        if (!registry.hasOpenFile()) {
            return "No file is currently open";
        }
        try {
            fileIO.save(registry.get(registry.getCurrentFileMachineId()), registry.getCurrentFilePath());
            return "Successfully saved " + registry.getCurrentFilePath();
        } catch (Exception e) {
            return "Error saving: " + e.getMessage();
        }
    }
}