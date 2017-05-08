package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.util.ui.Command;
import de.hsa.games.fatsquirrel.util.ui.CommandTypeInfo;
import de.hsa.games.fatsquirrel.util.ui.ScanException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tillm on 22.04.2017.
 */
public class GameImpl extends Game {


    public GameImpl() {
        this.setUi(new ConsoleUI());
        this.setState(new State());
    }

    protected void processInput() {

        boolean wasActionMade = false;

        while(!wasActionMade) {
            Command command = this.getUi().getCommand();
            while(command == null){
                command = this.getUi().getCommand();
            }
            GameCommandType commandType = (GameCommandType) command.getCommandTypeInfo();

            if(commandType == GameCommandType.DOWN || commandType == GameCommandType.LEFT ||
                    commandType == GameCommandType.RIGHT || commandType == GameCommandType.UP ||
                    commandType == GameCommandType.SPAWN_MINI){
                wasActionMade = true;
            }

            try {
                Class cl = Class.forName("de.hsa.games.fatsquirrel.console.GameImpl");
                Method method = cl.getDeclaredMethod(((GameCommandType) command.getCommandTypeInfo()).getMethodName(), command.getCommandTypeInfo().getParamTypes());
                method.invoke(this, command.getParams());
            }catch (ClassNotFoundException cfe) {
                System.out.println("Klasse nicht gefunden");
                cfe.printStackTrace();
            }catch (IllegalAccessException iae){
                iae.printStackTrace();
            }catch (NoSuchMethodException nsme){
                System.out.println("Methode nicht gefunden");
            }catch (InvocationTargetException ite){
                ite.printStackTrace();
            }

        }
    }

    private void exit(){
        System.exit(0);
    }

    private void help(){
        for (CommandTypeInfo i : GameCommandType.values()) {
            System.out.println(i.getName() + " " + i.getHelpText());
        }
    }

    private void all(){
        //Todo: ALL Befehl definieren
    }

    private void moveUp(){
        this.command = MoveCommand.NORTH;
    }

    private void moveDown(){
        this.command = MoveCommand.SOUTH;
    }

    private void moveLeft(){
        this.command = MoveCommand.WEST;
    }

    private void moveRight(){
        this.command = MoveCommand.EAST;
    }

    private void masterEnergy(){
        for (Entity i : getState().getEntitySet()) {
            if(i != null) {
                if (i.getEntityType() == EntityType.HANDOPERATEDMASTERSQUIRREL) {
                    System.out.println("Energy vom MasterSquirrel: " + i.getEnergy());
                }
            }
        }
    }

    private void spawnMini(int energy) throws NotEnoughEnergyException {

        MasterSquirrel masterSquirrel = null;
        this.command = MoveCommand.NOWHERE;

        for (Entity i : getState().getEntitySet()) {
            if(i != null) {
                if (i.getEntityType() == EntityType.HANDOPERATEDMASTERSQUIRREL) {
                    masterSquirrel = (HandOperatedMasterSquirrel) i;
                }
            }
        }

        if(masterSquirrel == null){
            return;
        }

        XY locationOfMaster = masterSquirrel.getCoordinate();
        for (MoveCommand offset : MoveCommand.values()) {
            //Wenn dieses Feld leer ist....
            if (masterSquirrel.getEnergy() >= energy) {
                if (this.getState().flattenBoard().getEntityType(locationOfMaster.addVector(Vector.moveCommandToVector(offset))) == EntityType.EMPTY) {

                    //Füge neues StandardMiniSquirrel hinzu zum Board
                    this.getState().getBoard().add(
                            new StandardMiniSquirrel(
                                    this.getState().getBoard().setID(),
                                    (locationOfMaster.addVector(Vector.moveCommandToVector(offset))),
                                    energy,
                                    masterSquirrel));
                    return;
                }
            } else {
                throw new NotEnoughEnergyException();
            }
        }

    }

    protected void render() {
        this.getUi().render(this.getState().flattenBoard());
    }

    protected void update() {
        //Todo; MiniSquirrel spawn in die Update Methode verlegen.
        getState().update(command);
    }
}
