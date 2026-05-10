package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class SaveAsCommand implements Command {
    private  MachineRegistry registry;
    private  MachineFile fileIO;
    public SaveAsCommand(MachineRegistry r, MachineFile f) { this.registry = r; this.fileIO = f; }
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: save as <file>";
        }
        if (!registry.hasOpenFile()) {
            return "No file is currently open";
        }
        try {
            fileIO.save(registry.get(registry.getCurrentFileMachineId()), args[0]);
            return "Successfully saved " + args[0];
        } catch (Exception e) { return "Error saving: " + e.getMessage(); }
    }
}