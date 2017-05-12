package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.XYsupport;

public class BotControllerFactoryImpl implements de.hsa.games.fatsquirrel.botapi.BotControllerFactory{
    public BotController createMasterBotController(){
        return new BotController() {
            @Override
            public void nextStep(ControllerContext view) {

                view.move(XYsupport.randomDirection());
            }
        };
    }

    public BotController createMiniBotController(){
        return new BotController() {
            @Override
            public void nextStep(ControllerContext view) {

                view.move(XYsupport.randomDirection());
            }
        };
    }
}
