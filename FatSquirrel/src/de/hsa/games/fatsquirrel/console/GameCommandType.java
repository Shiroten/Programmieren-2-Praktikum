package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.util.ui.CommandTypeInfo;

/**
 * Created by tillm on 05.05.2017.
 */
public enum GameCommandType implements CommandTypeInfo {
    HELP("h", "* list all commands"),
    EXIT("e", "* exit the game"),
    ALL("all", "* i have no idea what this does"),
    LEFT("a", "* move the squirrel left if possible"),
    RIGHT("d", "* move the squirrel right if possible"),
    UP("w", "* move the squirrel up if possible"),
    DOWN("s", "* move the squirrel down if possible"),
    MASTER_ENERGY("i", "* the energy of the mastersquirrel"),
    SPAWN_MINI("m", "<param1> Integer  *spawn a mini-squirrel with param1 Energy", int.class);

    private String commandName, helpText;
    private Class[] params;

    private GameCommandType(String name, String helpText){
        commandName = name;
        this.helpText = helpText;
    }

    private GameCommandType(String name, String helpText, Class... params){
        commandName = name;
        this.helpText = helpText;
        this.params = params;
    }

    public String getName() {
        return commandName;
    }

    public String getHelpText() {
        return helpText;
    }

    public Class<?>[] getParamTypes(){
        return params;
    }
}
