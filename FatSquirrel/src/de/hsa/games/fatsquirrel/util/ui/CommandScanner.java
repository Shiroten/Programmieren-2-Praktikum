package de.hsa.games.fatsquirrel.util.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by tillm on 28.04.2017.
 */
public class CommandScanner {
    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private PrintStream outputStream;

    public CommandScanner(CommandTypeInfo[] commandTypeInfos, BufferedReader inputReader){
        this.commandTypeInfos = commandTypeInfos;
        this.inputReader = inputReader;
    }

    public Command next(){
        CommandTypeInfo commandType = null;
        Object[] params = null;
        System.out.println("Geben Sie Ihren Befehl ein: ");
        try {
            String input = inputReader.readLine();
            String[] inputSplit = input.split(" ");
            for(CommandTypeInfo i : commandTypeInfos){
                if(i.getName().equals(inputSplit[0])) {
                    commandType = i;
                    params = new Object[i.getParamTypes().length];
                }
            }
            if(commandType == null)
                return new Command(null, null);
            for(int i = 0; i < params.length; i++){
                try{
                    String className = commandType.getParamTypes()[i].getName();
                    Object object = Class.forName(className);
                    //TODO: Object den entsprechenden Teil des String zuweisen

                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
                params[i] = inputSplit[i];
            }

        } catch(IOException e){
            e.printStackTrace();
        }

        return new Command(commandType, params);
    }

}
