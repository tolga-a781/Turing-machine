package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class CloseCommand extends AbstractCommand {
    public CloseCommand(MachineRegistry r) {

        super(r);
    }
    @Override public String execute(String[] args) {
        if (!getRegistry().hasOpenFile()){
            return "No file is currently open";
        }
        String path = getRegistry().getCurrentFilePath();
        getRegistry().clearCurrentFile();
        return "Successfully closed " + path;
    }
}