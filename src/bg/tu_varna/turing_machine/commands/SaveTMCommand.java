package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class SaveTMCommand implements Command {
    private MachineRegistry registry;
    private MachineFile fileIO;
    public SaveTMCommand(MachineRegistry r, MachineFile f) {
        this.registry = r; this.fileIO = f;
    }
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: saveTM <id> <file>";
        }
        try {
            fileIO.save(registry.get(Integer.parseInt(args[0])), args[1]);
            return "Saved machine " + args[0] + " to " + args[1];
        } catch (Exception e) { return "Error: " + e.getMessage(); }
    }
}