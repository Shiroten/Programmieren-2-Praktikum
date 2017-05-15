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

    private HandOperatedMasterSquirrel handOperatedMasterSquirrel;
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
                    handOperatedMasterSquirrel.setCommand(ActionCommand.NORTH);
                    break;
                case "a":
                    handOperatedMasterSquirrel.setCommand(ActionCommand.WEST);
                    break;
                case "d":
                    handOperatedMasterSquirrel.setCommand(ActionCommand.EAST);
                    break;
                case "s":
                    handOperatedMasterSquirrel.setCommand(ActionCommand.SOUTH);
                    break;
                case "f":
                    handOperatedMasterSquirrel.setCommand(ActionCommand.SPAWN);
                    handOperatedMasterSquirrel.setMiniSquirrelSpawnEnergy((Integer) (cmd.getParams())[0]);
                    break;
                case "p":
                    handOperatedMasterSquirrel.updateEnergy(1000);
                    break;
                case "t":
                    imploadMiniSquirrel = cmd;
                default:
                    handOperatedMasterSquirrel.setCommand(ActionCommand.NOWHERE);
            }
        } else {
            System.out.println("No HandOperatedMasterSquirrel found");
        }
    }

    protected void render() {
        this.getUi().render(this.getState().flattenBoard());
    }

    protected void update() {

        if (imploadMiniSquirrel != null) {
            imploadMiniSquirrel();
            imploadMiniSquirrel = null;
        }

        getState().update();
        FxUI fxUI = (FxUI) this.getUi();
        fxUI.message("MasterSquirrel Energy: " + Integer.toString(handOperatedMasterSquirrel.getEnergy()));
    }


    private void imploadMiniSquirrel() {
        for (Entity e : getState().getEntitySet()) {
            if (e != null) {
                if (e.getEntityType() == EntityType.MINISQUIRREL) {
                    if (((MiniSquirrel) e).getDaddy() == handOperatedMasterSquirrel) {
                        ((MiniSquirrel) e).impload();
                    }

                }
            }
        }
    }
}
