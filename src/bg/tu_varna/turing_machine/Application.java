package bg.tu_varna.turing_machine;

import bg.tu_varna.turing_machine.services.MachineFile;
import bg.tu_varna.turing_machine.services.MachineRegistry;

import java.util.Scanner;

public class Application {
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