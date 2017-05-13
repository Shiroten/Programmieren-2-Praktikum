package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by Shiroten on 13.05.2017.
 */
public class MasterBotControllerImplShiroten implements BotController{
    @Override
    public void nextStep(ControllerContext view) {

            view.move(XY.DOWN);


    }
}
