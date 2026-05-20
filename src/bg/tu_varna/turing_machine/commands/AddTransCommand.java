package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.models.DirectionChecker;
import bg.tu_varna.turing_machine.services.MachineRegistry;

/**
 * Adds a transition rule to a machine.
 * Usage: addTrans id fromState readSymbol toState writeSymbol direction
 * Both symbols are validated against the alphabet. Direction must be L, R, or S.
 * Both states must already exist in the machine.
 */
public class AddTransCommand extends AbstractCommand {
    public AddTransCommand(MachineRegistry r) {
        super(r);
    }
    /** Validates the symbols and direction, then adds the transition rule. */
    @Override public String execute(String[] args) {
        if (args.length < 6) {
            return "Usage: addTrans <id> <q> <read> <q2> <write> <move>";
        }
        getRegistry().get(Integer.parseInt(args[0])).addTransition(args[1], args[2].charAt(0), args[3], args[4].charAt(0), DirectionChecker.parse(args[5]));
        return "Added transition";
    }
}
