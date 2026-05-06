package bg.tu_varna.turing_machine.commands;
import bg.tu_varna.turing_machine.interfaces.Command;
public class HelpCommand implements Command {
    @Override public String execute(String[] args) {
        return """
                The following commands are supported:
                open <file>                       opens <file>
                close                             closes currently opened file
                save                              saves the currently open file
                save as <file>                    saves the currently open file in <file>
                help                              prints this information
                exit                              exits the program
                list                              lists IDs of all loaded machines
                print <id>                        prints machine info and transitions
                saveTM <id> <file>                saves a specific machine
                loadTM <file>                     loads a machine and assigns a new ID
                newTM <name>                      creates a new empty machine
                addState <id> <state>             adds a state
                setStart <id> <state>             sets start state
                addAccept <id> <state>            marks state as accepting
                addReject <id> <state>            marks state as rejecting
                addTrans <id> <q> <r> <q2> <w> <m>  adds a transition
                removeTrans <id> <q> <r>          removes a transition
                checkDet <id>                     checks determinism
                init <id> <input>                 initializes execution on input
                step <id>                         executes one step
                run <id> [max=<n>]                runs until halt or max steps
                status <id>                       shows current execution state
                tape <id> [from=<a>] [to=<b>]     shows tape contents
                reset <id>                        resets execution
                accepts <id> <word> [max=<n>]     checks if word is accepted
                trace <id> <word> <k> [max=<n>]   shows first k configurations
                report <id> <word> [max=<n>]      summary of execution
                """;
    }
}