package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class RemoveTransCommand extends AbstractCommand {
    public RemoveTransCommand(MachineRegistry r) {
        super(r);
    }
    @Override public String execute(String[] args) {
        if (args.length < 3) {
            return "Usage: removeTrans <id> <q> <read>";
        }
        getRegistry().get(Integer.parseInt(args[0])).removeTransition(args[1], args[2].charAt(0));
        return "Removed transition";
    }
}