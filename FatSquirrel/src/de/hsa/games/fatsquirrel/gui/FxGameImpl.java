package de.hsa.games.fatsquirrel.gui;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.console.GameCommandType;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.util.ui.Command;

public class FxGameImpl extends Game {

    private HandOperatedMasterSquirrel masterSquirrel;


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
            case "":
                masterSquirrel.setCommand(MoveCommand.NOWHERE);
                break;
            default:
                masterSquirrel.setCommand(MoveCommand.NOWHERE);
        }

    }

    protected void render() {
        this.getUi().render(this.getState().flattenBoard());
    }

    protected void update() {
        getState().update();
        FxUI fxUI = (FxUI) this.getUi();
        fxUI.message("MasterSquirrel Energy: " + Integer.toString(masterSquirrel.getEnergy()));
    }
}
