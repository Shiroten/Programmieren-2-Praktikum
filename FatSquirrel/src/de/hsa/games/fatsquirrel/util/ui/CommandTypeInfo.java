package de.hsa.games.fatsquirrel.util.ui;

/**
 * Created by tillm on 28.04.2017.
 */
public interface CommandTypeInfo {
    public String getName();
    public String getHelpText();
    public Class[] getParamTypes();
}
