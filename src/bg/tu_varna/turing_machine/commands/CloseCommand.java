package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class CloseCommand implements Command {
    private MachineRegistry registry;
    public CloseCommand(MachineRegistry r) {
        this.registry = r;
    }
    @Override public String execute(String[] args) {
        if (!registry.hasOpenFile()){
            return "No file is currently open";
        }
        String path = registry.getCurrentFilePath();
        registry.clearCurrentFile();
        return "Successfully closed " + path;
    }
}