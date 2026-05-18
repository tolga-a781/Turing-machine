package bg.tu_varna.turing_machine.commands;

import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineRegistry;

public abstract class AbstractCommand implements Command {
    private MachineRegistry registry;

    public MachineRegistry getRegistry() {
        return registry;
    }

    public AbstractCommand(MachineRegistry registry) {

        this.registry = registry;
    }

    public String requireOpenFile() {
        if (!registry.hasOpenFile()) {
            return "No file is currently open";
        }
        return null;
    }
}
