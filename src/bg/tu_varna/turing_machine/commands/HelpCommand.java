package bg.tu_varna.turing_machine.commands;

import bg.tu_varna.turing_machine.interfaces.Command;

public class HelpCommand implements Command {


    @Override
    public String execute() {
        return """
                Th`e following commands are supported:
                open <file>      opens <file>
                close            closes currently opened file
                save             saves the currently open file
                save as <file>   saves the currently open file in <file>
                help             prints this information
                exit             exits the program
                """;
    }
}