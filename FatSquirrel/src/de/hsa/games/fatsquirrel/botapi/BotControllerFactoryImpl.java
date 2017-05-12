package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;

public class BotControllerFactoryImpl implements de.hsa.games.fatsquirrel.botapi.BotControllerFactory{
    public BotController createMasterBotController(){
        return new BotController() {
            @Override
            public void nextStep(ControllerContext view) {

                view.move(Vector.randomDirection());
            }
        };
    }

    public BotController createMiniBotController(){
        return new BotController() {
            @Override
            public void nextStep(ControllerContext view) {

                view.move(Vector.randomDirection());
            }
        };
    }
}
