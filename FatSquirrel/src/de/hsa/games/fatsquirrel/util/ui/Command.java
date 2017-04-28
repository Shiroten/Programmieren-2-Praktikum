package de.hsa.games.fatsquirrel.util.ui;

/**
 * Created by tillm on 28.04.2017.
 */
public class Command{
    private CommandTypeInfo commandTypeInfo;
    private Object[] params;

    public Command(CommandTypeInfo commandTypeInfo, Object[] params){
        this.commandTypeInfo = commandTypeInfo;
        this.params = params;
    }

    public CommandTypeInfo getCommandTypeInfo() {
        return commandTypeInfo;
    }

    public Object[] getParams() {
        return params;
    }
}
