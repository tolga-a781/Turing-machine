package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class SetStartCommand extends AbstractCommand {
    public SetStartCommand(MachineRegistry r) {
        super(r);
    }
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: setStart <id> <state>";
        }
        getRegistry().get(Integer.parseInt(args[0])).setStart(args[1]);
        return "Set start state to " + args[1];
    }
}