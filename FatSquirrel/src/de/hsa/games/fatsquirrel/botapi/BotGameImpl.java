package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.core.entity.character.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.State;
import de.hsa.games.fatsquirrel.gui.FxGameImpl;
import de.hsa.games.fatsquirrel.gui.FxUI;

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

    /*Üprotected void update() {

        getState().update();
        FxUI fxUI = (FxUI) this.getUi();
        fxUI.message("MasterSquirrel Energy: " + Integer.toString(handOperatedMasterSquirrel.getEnergy()));
    }*/


}
