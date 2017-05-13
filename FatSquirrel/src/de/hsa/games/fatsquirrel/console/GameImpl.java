package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.XYsupport;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.core.entity.EntityType;
import de.hsa.games.fatsquirrel.core.entity.character.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.entity.character.StandardMiniSquirrel;
import de.hsa.games.fatsquirrel.util.ui.Command;
import de.hsa.games.fatsquirrel.util.ui.CommandTypeInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GameImpl extends Game {

    private HandOperatedMasterSquirrel handOperatedMasterSquirrel;


    public GameImpl() {
        this.setUi(new ConsoleUI());
        this.setState(new State());
        this.handOperatedMasterSquirrel = this.getState().getBoard().getHandOperatedMasterSquirrel();
    }

    protected void processInput() {

        Command command = this.getUi().getCommand();

        try {
            Method method = this.getClass().getDeclaredMethod(((GameCommandType) command.getCommandTypeInfo()).getMethodName(), command.getCommandTypeInfo().getParamTypes());
            method.invoke(this, command.getParams());
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
        } catch (NoSuchMethodException nsme) {
            System.out.println("Methode nicht gefunden");
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
        } catch(NullPointerException npe){

        }

    }

    private void exit() {
        System.exit(0);
    }

    private void help() {
        for (CommandTypeInfo i : GameCommandType.values()) {
            System.out.println(i.getName() + " " + i.getHelpText());
        }
    }

    private void moveUp() {
        handOperatedMasterSquirrel.setCommand(MoveCommand.NORTH);
    }

    private void moveDown() {
        handOperatedMasterSquirrel.setCommand(MoveCommand.SOUTH);
    }

    private void moveLeft() {
        handOperatedMasterSquirrel.setCommand(MoveCommand.WEST);
    }

    private void moveRight() {
        handOperatedMasterSquirrel.setCommand(MoveCommand.EAST);
    }

    private void masterEnergy() {
        System.out.println("Energy vom MasterSquirrel: " + handOperatedMasterSquirrel.getEnergy());
    }

    private void spawnMini(int energy) throws NotEnoughEnergyException {

        XY locationOfMaster = handOperatedMasterSquirrel.getCoordinate();

        for (XY xy : XYsupport.directions()) {
            //Wenn dieses Feld leer ist....
            if (handOperatedMasterSquirrel.getEnergy() >= energy) {
                if (this.getState().flattenBoard().getEntityType(locationOfMaster.plus(xy)) == EntityType.NONE) {

                    //Füge neues StandardMiniSquirrel hinzu zum Board
                    this.getState().getBoard().add(
                            new StandardMiniSquirrel(
                                    this.getState().getBoard().setID(),
                                    (locationOfMaster.plus(xy)),
                                    energy,
                                    handOperatedMasterSquirrel));
                    return;
                }
            } else {
                throw new NotEnoughEnergyException();
            }
        }

    }

    private void doNothing(){

    }

    protected void render() {
        this.getUi().render(this.getState().flattenBoard());
    }

    protected void update() {
        getState().update();
    }
}
