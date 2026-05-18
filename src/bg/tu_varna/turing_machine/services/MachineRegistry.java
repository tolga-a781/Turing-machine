package bg.tu_varna.turing_machine.services;

import bg.tu_varna.turing_machine.models.TuringMachine;

import java.util.*;

public class MachineRegistry {
    private final Map<Integer, TuringMachine> machines = new LinkedHashMap<>();
    private final Map<Integer, TuringMachineRunner> executions = new HashMap<>();
    private int nextId = 1;
    private String currentFilePath;
    private Integer currentFileMachineId;

    public TuringMachine create(String name) {
        int id = nextId++;
        TuringMachine m = new TuringMachine(id, name);
        machines.put(id, m);
        executions.put(id, new TuringMachineRunner(m));
        return m;
    }

    public TuringMachine register(TuringMachine machine) {
        machines.put(machine.getId(), machine);
        executions.put(machine.getId(), new TuringMachineRunner(machine));
        if (machine.getId() >= nextId){
            nextId = machine.getId() + 1;
        }
        return machine;
    }

    public TuringMachine get(int id) {
        TuringMachine m = machines.get(id);
        if (m == null) throw new IllegalArgumentException("Unknown machine ID: " + id);
        return m;
    }

    public TuringMachineRunner getExecution(int id) {
        TuringMachineRunner c = executions.get(id);
        if (c == null) throw new IllegalArgumentException("Unknown machine ID: " + id);
        return c;
    }

    public Collection<TuringMachine> all() {
        return machines.values();
    }

    public int nextId() {
        return nextId;
    }

    public boolean hasOpenFile() {
        return currentFilePath != null;
    }

    public String getCurrentFilePath() {
        return currentFilePath;
    }

    public Integer getCurrentFileMachineId() {
        return currentFileMachineId;
    }

    public void setCurrentFile(String path, int machineId) {
        this.currentFilePath = path;
        this.currentFileMachineId = machineId;
    }

    public void clearCurrentFile() {
        this.currentFilePath = null;
        this.currentFileMachineId = null;
    }
}