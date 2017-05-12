package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.console.NotEnoughEnergyException;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.MasterSquirrelBot;
import de.hsa.games.fatsquirrel.core.State;
import de.hsa.games.fatsquirrel.gui.FxGameImpl;
import de.hsa.games.fatsquirrel.gui.FxUI;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tillm on 12.05.2017.
 */
public class BotGameImpl extends FxGameImpl {
    private MasterSquirrelBot masterSquirrel;

    public BotGameImpl(FxUI fxUI, State state){
        this.setState(state);
        this.setUi(fxUI);
        masterSquirrel = (MasterSquirrelBot) state.getBoard().getMasterSquirrel();
    }

    @Override
    protected void processInput(){

    }

    /*protected void render() {
        this.getUi().render(this.getState().flattenBoard());
    }*/

    protected void update() {

        getState().update();
        FxUI fxUI = (FxUI) this.getUi();
        fxUI.message("MasterSquirrel Energy: " + Integer.toString(masterSquirrel.getEnergy()));
    }


}
