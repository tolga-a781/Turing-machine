package bg.tu_varna.turing_machine;

import bg.tu_varna.turing_machine.commands.*;
import bg.tu_varna.turing_machine.enums.CommandType;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CommandDispatcher {
    private Map<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    public CommandDispatcher(MachineRegistry registry, MachineFile fileIO) {
        commands.put(CommandType.OPEN,new OpenCommand(registry, fileIO));
        commands.put(CommandType.CLOSE,new CloseCommand(registry));
        commands.put(CommandType.SAVE,new SaveCommand(registry, fileIO));
        commands.put(CommandType.SAVEAS,new SaveAsCommand(registry, fileIO));
        commands.put(CommandType.HELP,new HelpCommand());
        commands.put(CommandType.EXIT,new ExitCommand());
        commands.put(CommandType.LIST,new ListCommand(registry));
        commands.put(CommandType.PRINT,new PrintCommand(registry));
        commands.put(CommandType.SAVETM,new SaveTMCommand(registry, fileIO));
        commands.put(CommandType.LOADTM,new LoadTMCommand(registry, fileIO));
        commands.put(CommandType.NEWTM,new NewTMCommand(registry));
        commands.put(CommandType.ADDSTATE,new AddStateCommand(registry));
        commands.put(CommandType.SETSTART,new SetStartCommand(registry));
        commands.put(CommandType.ADDACCEPT,new AddAcceptCommand(registry));
        commands.put(CommandType.ADDREJECT,new AddRejectCommand(registry));
        commands.put(CommandType.ADDTRANS,new AddTransCommand(registry));
        commands.put(CommandType.REMOVETRANS,new RemoveTransCommand(registry));
        commands.put(CommandType.CHECKDET,new CheckDetCommand(registry));
        commands.put(CommandType.INIT,new InitCommand(registry));
        commands.put(CommandType.STEP,new StepCommand(registry));
        commands.put(CommandType.RUN,new RunCommand(registry));
        commands.put(CommandType.STATUS,new StatusCommand(registry));
        commands.put(CommandType.TAPE,new TapeCommand(registry));
        commands.put(CommandType.RESET,new ResetCommand(registry));
        commands.put(CommandType.ACCEPTS,new AcceptsCommand(registry));
        commands.put(CommandType.TRACE,new TraceCommand(registry));
        commands.put(CommandType.REPORT,new ReportCommand(registry));
    }

    public String dispatch(String line) {
        if (line == null) {
            return "";
        }
        line = line.trim();
        if (line.isEmpty()){
            return "";
        }
        String[] tokens = tokenize(line);
        String key = tokens[0].toLowerCase();
        if (key.equals(CommandType.SAVE.getKey()) && tokens.length >= 2 && tokens[1].equalsIgnoreCase("as")) {
            String[] rest = new String[tokens.length - 2];
            System.arraycopy(tokens, 2, rest, 0, rest.length);
            return safeExecute(commands.get(CommandType.SAVEAS), rest);
        }
        CommandType type = CommandType.fromString(key);
        if (type == null) {
            return "Unknown command: " + tokens[0];
        }
        Command cmd = commands.get(type);
        if (cmd == null) {
            return "Unknown command: " + tokens[0];
        }
        String[] args = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, args, 0, args.length);
        return safeExecute(cmd, args);
    }

    private String safeExecute(Command cmd, String[] args) {
        try {
            return cmd.execute(args);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String[] tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ' ' && !inQuotes) {
                if (current.length() > 0) {
                    tokens.add(current.toString()); current.setLength(0);
                }
            } else {
                current.append(c);
            }
        }
        if (current.length() > 0) {
            tokens.add(current.toString());
        }
        return tokens.toArray(new String[0]);
    }
}
