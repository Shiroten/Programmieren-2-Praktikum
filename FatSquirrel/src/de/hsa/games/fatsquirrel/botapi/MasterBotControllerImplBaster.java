package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.XY;

public class MasterBotControllerImplBaster implements BotController{
    @Override
    public void nextStep(ControllerContext view) {

        //Default random move
        view.move(new XY((int) (Math.random()*3)-1,(int) (Math.random() * 3)-1));

    }
}
