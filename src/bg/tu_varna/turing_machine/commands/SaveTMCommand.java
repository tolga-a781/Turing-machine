package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class SaveTMCommand extends AbstractFileCommand {
    public SaveTMCommand(MachineRegistry r, MachineFile f) {
        super(r, f);
    }
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