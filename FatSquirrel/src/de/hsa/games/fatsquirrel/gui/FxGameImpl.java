package de.hsa.games.fatsquirrel.gui;

import de.hsa.games.fatsquirrel.*;
import de.hsa.games.fatsquirrel.console.NotEnoughEnergyException;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.core.entity.Entity;
import de.hsa.games.fatsquirrel.core.entity.EntityType;
import de.hsa.games.fatsquirrel.core.entity.character.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.entity.character.MiniSquirrel;
import de.hsa.games.fatsquirrel.core.entity.character.StandardMiniSquirrel;
import de.hsa.games.fatsquirrel.util.ui.Command;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FxGameImpl extends Game {

    protected HandOperatedMasterSquirrel handOperatedMasterSquirrel;
    private Command spawnMiniSquirrel = null;
    private Command imploadMiniSquirrel = null;

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
                case "f":
                    spawnMiniSquirrel = cmd;
                    break;
                case "p":
                    handOperatedMasterSquirrel.updateEnergy(1000);
                    break;
                case "t":
                    imploadMiniSquirrel = cmd;
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

        if (imploadMiniSquirrel != null) {
            imploadMiniSquirrel();
            imploadMiniSquirrel = null;
        }

        getState().update();
        FxUI fxUI = (FxUI) this.getUi();
        fxUI.message("MasterSquirrel Energy: " + Integer.toString(handOperatedMasterSquirrel.getEnergy()));
    }

    private void spawnMini(int energy) throws NotEnoughEnergyException {

        Logger logger = Logger.getLogger(Launcher.class.getName());
        logger.log(Level.FINE, "Spawning Mini");

        XY locationOfMaster = handOperatedMasterSquirrel.getCoordinate();
        for (XY xy : XYsupport.directions()) {
            //Wenn dieses Feld leer ist....
            if (handOperatedMasterSquirrel.getEnergy() >= energy) {
                if (this.getState().flattenBoard().getEntityType(locationOfMaster.plus(xy)) == EntityType.NONE) {

                    //Füge neues StandardMiniSquirrel hinzu zum Board
                    handOperatedMasterSquirrel.updateEnergy(-energy);
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

    private void imploadMiniSquirrel() {
        for (Entity e : getState().getEntitySet()){
            if (e!=null){
                if (e.getEntityType()== EntityType.MINISQUIRREL){
                    if (((MiniSquirrel)e).getDaddy() == handOperatedMasterSquirrel){
                        ((MiniSquirrel)e).impload();
                    }

                }
            }
        }
    }
}
