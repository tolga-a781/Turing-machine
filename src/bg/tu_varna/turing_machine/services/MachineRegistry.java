package bg.tu_varna.turing_machine.services;

import bg.tu_varna.turing_machine.models.TuringMachine;

import java.util.*;

/**
 * Keeps track of all loaded Turing Machines and their runners during one session.
 * Machine IDs start at 1 and increment automatically; loading a .txt file preserves
 * its existing ID and adjusts the counter so future IDs do not collide.
 * Remembers which file is currently "open" so the save command knows where to write.
 * Each machine has exactly one TuringMachineRunner stored separately from its definition.
 */
public class MachineRegistry {
    private final Map<Integer, TuringMachine> machines = new LinkedHashMap<>();
    private final Map<Integer, TuringMachineRunner> executions = new HashMap<>();
    private int nextId = 1;
    private String currentFilePath;
    private Integer currentFileMachineId;

    /**
     * Creates a new empty machine with an auto-incremented ID and registers it.
     * @param name the display name for the new machine.
     */
    public TuringMachine create(String name) {
        int id = nextId++;
        TuringMachine m = new TuringMachine(id, name);
        machines.put(id, m);
        executions.put(id, new TuringMachineRunner(m));
        return m;
    }

    /**
     * Adds a machine that was loaded from a .txt file, preserving its existing ID.
     * Adjusts the auto-increment counter so future IDs do not collide.
     * @param machine the machine to register.
     */
    public TuringMachine register(TuringMachine machine) {
        machines.put(machine.getId(), machine);
        executions.put(machine.getId(), new TuringMachineRunner(machine));
        if (machine.getId() >= nextId){
            nextId = machine.getId() + 1;
        }
        return machine;
    }

    /**
     * Returns the machine with the given ID. Throws if no machine with that ID exists.
     * @param id the numeric ID of the machine.
     */
    public TuringMachine get(int id) {
        TuringMachine m = machines.get(id);
        if (m == null) throw new IllegalArgumentException("Unknown machine ID: " + id);
        return m;
    }

    /**
     * Returns the runner for the machine with the given ID. Throws if not found.
     * @param id the numeric ID of the machine.
     */
    public TuringMachineRunner getExecution(int id) {
        TuringMachineRunner c = executions.get(id);
        if (c == null) throw new IllegalArgumentException("Unknown machine ID: " + id);
        return c;
    }

    /** Returns all registered machines in the order they were added. */
    public Collection<TuringMachine> all() {
        return machines.values();
    }

    /** Returns the ID that will be assigned to the next machine created with create. */
    public int nextId() {
        return nextId;
    }

    /** Returns true if a .txt file is currently open (loaded with the open command). */
    public boolean hasOpenFile() {
        return currentFilePath != null;
    }

    /** Returns the path of the currently open .txt file, or null if none is open. */
    public String getCurrentFilePath() {
        return currentFilePath;
    }

    /** Returns the ID of the machine loaded from the currently open file. */
    public Integer getCurrentFileMachineId() {
        return currentFileMachineId;
    }

    /**
     * Records a .txt file as currently open.
     * @param path the file path that was opened.
     * @param machineId the ID of the machine loaded from that file.
     */
    public void setCurrentFile(String path, int machineId) {
        this.currentFilePath = path;
        this.currentFileMachineId = machineId;
    }

    /** Clears the currently open file record so save and close have no target. */
    public void clearCurrentFile() {
        this.currentFilePath = null;
        this.currentFileMachineId = null;
    }
}