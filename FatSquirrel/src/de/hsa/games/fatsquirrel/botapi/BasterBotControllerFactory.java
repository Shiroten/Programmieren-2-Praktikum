package de.hsa.games.fatsquirrel.botapi;

public class BasterBotControllerFactory implements de.hsa.games.fatsquirrel.botapi.BotControllerFactory{

    public BasterBotControllerFactory(){

    }

    public BotController createMasterBotController(){
        return  new MasterBotControllerImplBaster();
    }

    public BotController createMiniBotController(){
        return new MiniBotControllerImplBaster();
    }
}
