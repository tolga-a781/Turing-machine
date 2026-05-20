package bg.tu_varna.turing_machine.commands;

import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Base class for commands that need the machine registry.
 * Provides requireOpenFile, which returns an error string when no file is open
 * or null when a file is open and the command may proceed.
 */
public abstract class AbstractCommand implements Command {
    /** The shared registry all commands use to look up machines and runners. */
    private MachineRegistry registry;

    /** Returns the shared registry used by all commands. */
    public MachineRegistry getRegistry() {
        return registry;
    }

    /**
     * Stores the registry reference for use by subclasses.
     * @param registry the shared machine registry.
     */
    public AbstractCommand(MachineRegistry registry) {

        this.registry = registry;
    }

    /** Returns null if a file is currently open, or an error message string if not. */
    public String requireOpenFile() {
        if (!registry.hasOpenFile()) {
            return "No file is currently open";
        }
        return null;
    }
}
