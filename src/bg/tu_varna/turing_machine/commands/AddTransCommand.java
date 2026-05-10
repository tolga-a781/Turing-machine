package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
import bg.tu_varna.turing_machine.models.DirectionChecker;
import bg.tu_varna.turing_machine.services.MachineRegistry;
public class AddTransCommand implements Command {
    private MachineRegistry registry;
    public AddTransCommand(MachineRegistry r) {
        this.registry = r;
    }
    @Override public String execute(String[] args) {
        if (args.length < 6){
            return "Usage: addTrans <id> <q> <read> <q2> <write> <move>";
        }
        registry.get(Integer.parseInt(args[0])).addTransition(args[1], args[2].charAt(0), args[3], args[4].charAt(0), DirectionChecker.parse(args[5]));
        return "Added transition";
    }
}