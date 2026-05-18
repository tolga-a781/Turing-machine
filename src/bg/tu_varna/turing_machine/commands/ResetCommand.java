package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class ResetCommand extends AbstractCommand {
    public ResetCommand(MachineRegistry r) {

        super(r);
    }
    @Override public String execute(String[] args) {
        if (args.length < 1){
            return "Usage: reset <id>";
        }
        getRegistry().getExecution(Integer.parseInt(args[0])).reset();
        return "Execution reset";
    }
}