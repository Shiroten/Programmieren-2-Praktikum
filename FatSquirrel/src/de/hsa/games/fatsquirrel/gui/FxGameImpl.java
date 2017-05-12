package de.hsa.games.fatsquirrel.gui;

import de.hsa.games.fatsquirrel.*;
import de.hsa.games.fatsquirrel.console.NotEnoughEnergyException;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.core.entity.EntityType;
import de.hsa.games.fatsquirrel.core.character.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.character.StandardMiniSquirrel;
import de.hsa.games.fatsquirrel.util.ui.Command;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FxGameImpl extends Game {

    protected HandOperatedMasterSquirrel handOperatedMasterSquirrel;
    private Command spawnMiniSquirrel = null;

    protected FxGameImpl() {
    }

    public FxGameImpl(FxUI fxUI, State state) {

        this.setUi(fxUI);
        this.setState(state);
        this.handOperatedMasterSquirrel = this.getState().getBoard().getHandOperatedMasterSquirrel();
    }

    protected void processInput() {

        if (handOperatedMasterSquirrel != null) {
            Command cmd = this.getUi().getCommand();
            switch (cmd.getCommandTypeInfo().getName()) {
                case "w":
                    handOperatedMasterSquirrel.setCommand(MoveCommand.NORTH);
                    break;
                case "a":
                    handOperatedMasterSquirrel.setCommand(MoveCommand.WEST);
                    break;
                case "d":
                    handOperatedMasterSquirrel.setCommand(MoveCommand.EAST);
                    break;
                case "s":
                    handOperatedMasterSquirrel.setCommand(MoveCommand.SOUTH);
                    break;
                case "m":
                    spawnMiniSquirrel = cmd;
                    break;
                case "p":
                    handOperatedMasterSquirrel.updateEnergy(1000);
                    break;
                default:
                    handOperatedMasterSquirrel.setCommand(MoveCommand.NOWHERE);
            }
        } else {
            System.out.println("No HandOperatedMasterSquirrel found");
        }
    }

    protected void render() {
        this.getUi().render(this.getState().flattenBoard());
    }

    protected void update() {

        if (spawnMiniSquirrel != null) {
            Logger logger = Logger.getLogger(Launcher.class.getName());
            logger.log(Level.FINER, "try to Spawn MiniSquirrel in update() from FxGameImpl");
            try {
                spawnMini((Integer) spawnMiniSquirrel.getParams()[0]);
            } catch (NotEnoughEnergyException neee) {
                neee.printStackTrace();
            }
            spawnMiniSquirrel = null;
        }

        getState().update();
        FxUI fxUI = (FxUI) this.getUi();
        fxUI.message("MasterSquirrel Energy: " + Integer.toString(handOperatedMasterSquirrel.getEnergy()));
    }

    private void spawnMini(int energy) throws NotEnoughEnergyException {

        Logger logger = Logger.getLogger(Launcher.class.getName());
        logger.log(Level.FINE, "Spawning Mini");

        XY locationOfMaster = handOperatedMasterSquirrel.getCoordinate();
        for (MoveCommand offset : MoveCommand.values()) {
            //Wenn dieses Feld leer ist....
            if (handOperatedMasterSquirrel.getEnergy() >= energy) {
                if (this.getState().flattenBoard().getEntityType(locationOfMaster.addVector(Vector.moveCommandToVector(offset))) == EntityType.EMPTY) {

                    //Füge neues StandardMiniSquirrel hinzu zum Board
                    handOperatedMasterSquirrel.updateEnergy(-energy);
                    this.getState().getBoard().add(
                            new StandardMiniSquirrel(
                                    this.getState().getBoard().setID(),
                                    (locationOfMaster.addVector(Vector.moveCommandToVector(offset))),
                                    energy,
                                    handOperatedMasterSquirrel));
                    return;
                }
            } else {
                throw new NotEnoughEnergyException();
            }
        }
    }
}
