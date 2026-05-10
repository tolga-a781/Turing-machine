package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.models.TuringMachine;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class LoadTMCommand implements Command {
    private MachineRegistry registry;
    private MachineFile fileIO;
    public LoadTMCommand(MachineRegistry r, MachineFile f) { this.registry = r; this.fileIO = f; }
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: loadTM <file>";
        }
        try {
            TuringMachine m = fileIO.load(args[0], registry.nextId());
            registry.register(m);
            return "Loaded machine from " + args[0] + " (ID " + m.getId() + ")";
        } catch (Exception e) { return "Error: " + e.getMessage(); }
    }
}