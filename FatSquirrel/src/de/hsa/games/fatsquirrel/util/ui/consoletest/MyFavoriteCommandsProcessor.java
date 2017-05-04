package de.hsa.games.fatsquirrel.util.ui.consoletest;

import de.hsa.games.fatsquirrel.util.ui.CommandScanner;
import de.hsa.games.fatsquirrel.util.ui.Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MyFavoriteCommandsProcessor {

    PrintStream outputStream = new PrintStream(System.out);
    private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private CommandScanner commandScanner = new CommandScanner(MyFavoriteCommandType.values(), inputReader);


    public MyFavoriteCommandsProcessor() {
    }

    public void process() {
        while (true) {
            //the loop over all commands with one input line for every command
            Command command;
            command = commandScanner.next();

            Object[] params = command.getParams();
            MyFavoriteCommandType commandType = (MyFavoriteCommandType) command.getCommandTypeInfo();

            switch (commandType) {
                case EXIT:
                    System.exit(0);
                case HELP:
                    //Todo: Add HELP
                    //help();
                    break;
                case ADDI:
                    //Todo: Add ADDI
                    break;
                case ADDF:
                    //Todo: Add ADDF
                    break;
                case ECHO:
                    //Todo: Add ECHO
                    break;

                default:
                    //Todo: Add Default
                    break;

            }

        }
    }
}
