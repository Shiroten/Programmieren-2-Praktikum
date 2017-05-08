package de.hsa.games.fatsquirrel.gui;
import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.core.*;
import javafx.scene.Parent;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;



public class FxGameImpl extends Game {

    private HandOperatedMasterSquirrel masterSquirrel;


    public FxGameImpl(FxUI fxUI) {

        this.setUi(fxUI);
        this.setState(new State());

        for (Entity i : getState().getEntitySet()) {
            if (i != null) {
                if (i.getEntityType() == EntityType.HANDOPERATEDMASTERSQUIRREL) {
                    masterSquirrel = (HandOperatedMasterSquirrel) i;
                }
            }
        }
    }

    protected void processInput() {

    }

    protected void render() {
        this.getUi().render(this.getState().flattenBoard());
    }

    protected void update() {
        getState().update();
    }
}
