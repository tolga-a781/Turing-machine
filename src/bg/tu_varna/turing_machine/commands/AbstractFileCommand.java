package bg.tu_varna.turing_machine.commands;

import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;

public abstract class AbstractFileCommand extends AbstractCommand {
    public MachineFile machineFile;

    public AbstractFileCommand(MachineRegistry registry, MachineFile machineFile) {
        super(registry);
        this.machineFile = machineFile;
    }
}
