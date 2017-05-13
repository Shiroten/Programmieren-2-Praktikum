package de.hsa.games.fatsquirrel.botapi;

public class BotControllerFactoryImpl implements de.hsa.games.fatsquirrel.botapi.BotControllerFactory{

    public BotControllerFactoryImpl(){

    }

    public BotController createMasterBotController(String masterBotControllerName){
        try {
            Class clazz = Class.forName(masterBotControllerName);
            return (BotController) clazz.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BotController createMiniBotController(String miniBotControllerName){
        try {
            Class clazz = Class.forName(miniBotControllerName);
            return (BotController) clazz.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
