package de.hsa.games.fatsquirrel.gui;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.console.GameCommandType;
import de.hsa.games.fatsquirrel.console.NotEnoughEnergyException;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.util.ui.Command;

public class FxGameImpl extends Game {

    private HandOperatedMasterSquirrel masterSquirrel;
    private Command spawnMiniSquirrel = null;

    public FxGameImpl(FxUI fxUI, State state) {

        this.setUi(fxUI);
        this.setState(state);
        this.masterSquirrel = this.getState().getBoard().getMasterSquirrel();
    }

    protected void processInput() {

        Command cmd = this.getUi().getCommand();
        switch (cmd.getCommandTypeInfo().getName()) {
            case "w":
                masterSquirrel.setCommand(MoveCommand.NORTH);
                break;
            case "a":
                masterSquirrel.setCommand(MoveCommand.WEST);
                break;
            case "d":
                masterSquirrel.setCommand(MoveCommand.EAST);
                break;
            case "s":
                masterSquirrel.setCommand(MoveCommand.SOUTH);
                break;
            case "m":
                spawnMiniSquirrel = cmd;
                break;
            case "p":
                masterSquirrel.updateEnergy(1000);
                break;
            default:
                masterSquirrel.setCommand(MoveCommand.NOWHERE);
        }
    }

    protected void render() {
        this.getUi().render(this.getState().flattenBoard());
    }

    protected void update() {

        if (spawnMiniSquirrel != null) {
            try {
                spawnMini((Integer) spawnMiniSquirrel.getParams()[0]);
            } catch (NotEnoughEnergyException neee) {

            }
            spawnMiniSquirrel = null;
        }

        getState().update();
        FxUI fxUI = (FxUI) this.getUi();
        fxUI.message("MasterSquirrel Energy: " + Integer.toString(masterSquirrel.getEnergy()));
    }

    private void spawnMini(int energy) throws NotEnoughEnergyException {

        XY locationOfMaster = masterSquirrel.getCoordinate();
        for (MoveCommand offset : MoveCommand.values()) {
            //Wenn dieses Feld leer ist....
            if (masterSquirrel.getEnergy() >= energy) {
                if (this.getState().flattenBoard().getEntityType(locationOfMaster.addVector(Vector.moveCommandToVector(offset))) == EntityType.EMPTY) {

                    //Füge neues StandardMiniSquirrel hinzu zum Board
                    masterSquirrel.updateEnergy(-energy);
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
}
