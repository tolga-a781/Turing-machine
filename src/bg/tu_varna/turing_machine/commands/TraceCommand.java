package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.services.MachineRegistry;
import java.util.List;
/**
 * Shows the first k configurations of a machine running on a word.
 * Usage: trace id word k [max=n]
 * Re-initialises on the word and prints up to k numbered snapshots,
 * stopping early if the machine halts before k steps.
 */
public class TraceCommand extends AbstractCommand {
    public TraceCommand(MachineRegistry r) {
        super(r);
    }
    /** Reinitializes on the word and prints up to k numbered configuration snapshots. */
    @Override public String execute(String[] args) {
        if (args.length < 3){
            return "Usage: trace <id> <word> <k> [max=<n>]";
        }
        int max = 10000;
        for (int i = 3; i < args.length; i++) {
            if (args[i].startsWith("max=")){
                max = Integer.parseInt(args[i].substring(4));
            }
        }
        List<String> configs = getRegistry().getExecution(Integer.parseInt(args[0])).trace(args[1], Integer.parseInt(args[2]), max);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < configs.size(); i++) sb.append(i).append(": ").append(configs.get(i)).append("\n");
        return sb.toString();
    }
}