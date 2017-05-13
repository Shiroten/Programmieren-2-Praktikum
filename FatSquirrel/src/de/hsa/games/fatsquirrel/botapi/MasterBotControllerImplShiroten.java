package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.XY;

public class MasterBotControllerImplShiroten implements BotController{
    @Override
    public void nextStep(ControllerContext view) {

            view.move(XY.UP);


    }
}
