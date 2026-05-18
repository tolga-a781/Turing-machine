package bg.tu_varna.turing_machine.services;

import bg.tu_varna.turing_machine.enums.Direction;
import bg.tu_varna.turing_machine.models.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MachineFile {

    public TuringMachine load(String path, int newId) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line.trim());
        }
        reader.close();

        String machineName = null;
        for (String l : lines) {
            if (l.startsWith("NAME ")) {
                machineName = l.substring(5).trim();
                break;
            }
        }
        if (machineName == null || machineName.isEmpty()) {
            machineName = nameFromPath(path);
        }

        TuringMachine machine = new TuringMachine(newId, machineName);

        for (String l : lines) {
            if (l.startsWith("STATE ")) {
                String[] parts = l.split(" ");
                if (parts.length < 2) {
                    throw new IOException("Invalid STATE line: " + l);
                }
                String stateName = parts[1];
                machine.addState(stateName);
                for (int i = 2; i < parts.length; i++) {
                    if (parts[i].equals("start")) {
                        machine.setStart(stateName);
                    } else if (parts[i].equals("accept")) {
                        machine.markAccepting(stateName);
                    } else if (parts[i].equals("reject")) {
                        machine.markRejecting(stateName);
                    }
                }
            }
        }

        for (String l : lines) {
            if (l.startsWith("TRANS ")) {
                String[] parts = l.split(" ");
                if (parts.length < 6) {
                    throw new IOException("Invalid TRANS line: " + l);
                }
                String from = parts[1];
                char read = parts[2].charAt(0);
                String to = parts[3];
                char write = parts[4].charAt(0);
                Direction dir = DirectionChecker.parse(parts[5]);
                machine.addState(from);
                machine.addState(to);
                machine.addTransition(from, read, to, write, dir);
            }
        }

        return machine;
    }

    public void save(TuringMachine m, String path) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(path));

        out.println("NAME " + m.getName());

        for (State s : m.getStates()) {
            String stateLine = "STATE " + s.getName();
            if (s.isStarting()) {
                stateLine = stateLine + " start";
            }
            if (s.isAccepting()) {
                stateLine = stateLine + " accept";
            }
            if (s.isRejecting()) {
                stateLine = stateLine + " reject";
            }
            out.println(stateLine);
        }

        for (Transition t : m.getTransitions()) {
            out.println("TRANS " + t.getFrom().getName() + " " + t.getRead() + " "
                    + t.getTo().getName() + " " + t.getWrite() + " "
                    + t.getDirection().toString());
        }

        out.close();
    }

    private String nameFromPath(String path) {
        String name = new File(path).getName();
        int dot = name.lastIndexOf('.');
        if (dot > 0) {
            return name.substring(0, dot);
        }
        return name;
    }
}
