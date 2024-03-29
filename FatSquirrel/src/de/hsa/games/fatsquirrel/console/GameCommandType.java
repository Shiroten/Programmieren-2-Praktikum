package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.util.ui.CommandTypeInfo;
public enum GameCommandType implements CommandTypeInfo {
    HELP("h", "help", "* list all commands"),
    EXIT("e", "exit", "* exit the game"),
    ALL("all", "all", "* i have no idea what this does"),
    LEFT("a", "moveLeft" ,"* move the squirrel left if possible"),
    RIGHT("d", "moveRight" ,"* move the squirrel right if possible"),
    UP("w", "moveUp" ,"* move the squirrel up if possible"),
    DOWN("s","moveDown" ,"* move the squirrel down if possible"),
    MASTER_ENERGY("i", "masterEnergy" ,"* the energy of the mastersquirrel"),
    SPAWN_MINI("f", "spawnMini" ,"<param1> Integer  *spawn a mini-squirrel with param1 Energy", int.class),
    CHEAT_ENERGY("p", "addEnergy" ,"Adds 1000 Energy to MasterSquirrel"),
    IMPLODE_MINISQUIRRELS("t", "implode" ,"Implode all MiniSquirrel of Player"),
    NOTHING("", "doNothing", "* Just press Enter");

    private String commandName, helpText, methodName;
    private Class[] params;

    private GameCommandType(String name, String methodName, String helpText){
        commandName = name;
        this.helpText = helpText;
        this.methodName = methodName;
    }

    private GameCommandType(String name, String methodName, String helpText, Class... params){
        commandName = name;
        this.helpText = helpText;
        this.methodName = methodName;
        this.params = params;
    }

    public String getName() {
        return commandName;
    }

    public String getHelpText() {
        return helpText;
    }

    public String getMethodName(){ return methodName; }

    public Class<?>[] getParamTypes(){
        return params;
    }
}
