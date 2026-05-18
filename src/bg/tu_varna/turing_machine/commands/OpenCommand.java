
package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.models.TuringMachine;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class OpenCommand extends AbstractFileCommand {
    public OpenCommand(MachineRegistry r, MachineFile f) {
        super(r, f);
    }
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: open <file>";
        }
        try {
            TuringMachine m = machineFile.load(args[0], getRegistry().nextId());
            getRegistry().register(m);
            getRegistry().setCurrentFile(args[0], m.getId());
            return "Successfully opened " + args[0] + " (machine ID " + m.getId() + ")";
        } catch (Exception e) {
            return "Error opening file: " + e.getMessage();
        }
    }
}