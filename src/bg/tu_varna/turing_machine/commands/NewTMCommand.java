package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.models.TuringMachine;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class NewTMCommand extends AbstractCommand {
    public NewTMCommand(MachineRegistry r) {

        super(r);
    }
    @Override public String execute(String[] args) {
        if (args.length < 1) {
            return "Usage: newTM <name>";
        }
        TuringMachine m = getRegistry().create(args[0]);
        return "Created machine " + m.getName() + " with ID " + m.getId();
    }
}