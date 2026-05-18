package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.models.TuringMachine;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class LoadTMCommand extends AbstractFileCommand {
    public LoadTMCommand(MachineRegistry r, MachineFile f) {

        super(r, f);
    }
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: loadTM <file>";
        }
        try {
            TuringMachine m = machineFile.load(args[0], getRegistry().nextId());
            getRegistry().register(m);
            return "Loaded machine from " + args[0] + " (ID " + m.getId() + ")";
        } catch (Exception e) { return "Error: " + e.getMessage(); }
    }
}