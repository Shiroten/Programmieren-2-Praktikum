package de.hsa.games.fatsquirrel.botapi;

/**
 * Created by tillm on 09.05.2017.
 */
public interface BotControllerFactory {
    BotController createMiniBotController(String BotControllerName);
    BotController createMasterBotController(String BotControllerName);
}
