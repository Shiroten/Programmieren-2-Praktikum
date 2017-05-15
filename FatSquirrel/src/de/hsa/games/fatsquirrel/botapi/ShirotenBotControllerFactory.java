package de.hsa.games.fatsquirrel.botapi;

/**
 * Created by tillm on 15.05.2017.
 */
public class ShirotenBotControllerFactory implements BotControllerFactory {
    public BotController createMasterBotController(){
        return new MasterBotControllerImplShiroten();
    }

    public BotController createMiniBotController(){
        return new MiniBotControllerImplShiroten();
    }
}
