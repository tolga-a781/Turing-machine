package bg.tu_varna.turing_machine;

import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;

import java.util.Scanner;

/**
 * Entry point of the Turing Machine simulator.
 * Starts a read-print loop that reads one line at a time from standard input,
 * passes it to the dispatcher, and prints the result.
 * Exits when the user types "exit" or when input ends.
 */
public class Application {

    /** Creates all dependencies and runs the REPL until the user exits. */

    public static void main(String[] args) {
        MachineRegistry registry = new MachineRegistry();
        MachineFile fileIO = new MachineFile();
        CommandDispatcher dispatcher = new CommandDispatcher(registry, fileIO);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Turing Machine CLI. Type 'help' for commands.");
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String line = scanner.nextLine();
            String result = dispatcher.dispatch(line);
            if (!result.isEmpty()){
                System.out.println(result);
            }
            if (line.trim().equalsIgnoreCase("exit")){
                break;
            }

        }
    }
}