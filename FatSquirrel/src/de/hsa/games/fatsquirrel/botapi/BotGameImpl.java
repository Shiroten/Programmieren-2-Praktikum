package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.core.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.MasterSquirrelBot;
import de.hsa.games.fatsquirrel.core.State;
import de.hsa.games.fatsquirrel.gui.FxGameImpl;
import de.hsa.games.fatsquirrel.gui.FxUI;
import de.hsa.games.fatsquirrel.util.ui.Command;

/**
 * Created by tillm on 12.05.2017.
 */
public class BotGameImpl extends FxGameImpl {
    private MasterSquirrel masterSquirrel[];


    public BotGameImpl(FxUI fxUI, State state) {
        this.setState(state);
        this.setUi(fxUI);
        handOperatedMasterSquirrel = state.getBoard().getHandOperatedMasterSquirrel();
    }

    /*@Override
    protected void processInput(){

    }*/

    /*protected void render() {
        this.getUi().render(this.getState().flattenBoard());
    }*/

    protected void update() {

        getState().update();
        FxUI fxUI = (FxUI) this.getUi();
        fxUI.message("MasterSquirrel Energy: " + Integer.toString(handOperatedMasterSquirrel.getEnergy()));
    }


}
