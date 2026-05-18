package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class AddRejectCommand extends AbstractCommand {
    public AddRejectCommand(MachineRegistry r) {

        super(r);
    }
    @Override public String execute(String[] args) {
        if (args.length < 2) {
            return "Usage: addReject <id> <state>";
        }
        getRegistry().get(Integer.parseInt(args[0])).markRejecting(args[1]);
        return "Marked " + args[1] + " as rejecting";
    }
}