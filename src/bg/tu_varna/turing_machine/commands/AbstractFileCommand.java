package bg.tu_varna.turing_machine.commands;

import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Base class for commands that need both the registry and the file service.
 * Extends AbstractCommand and adds a MachineFile reference for reading
 * and writing .txt machine definition files.
 */
public abstract class AbstractFileCommand extends AbstractCommand {
    /** The file service used to read and write .txt machine definition files. */
    public MachineFile machineFile;

    public AbstractFileCommand(MachineRegistry registry, MachineFile machineFile) {
        super(registry);
        this.machineFile = machineFile;
    }
}
