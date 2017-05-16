package de.hsa.games.fatsquirrel.botapi.bots.GoodBeastChaser;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.bots.Shiroten.ShirotenMaster;
import de.hsa.games.fatsquirrel.botapi.bots.Shiroten.ShirotenMini;

/**
 * Created by tillm on 15.05.2017.
 */
public class GoodBeastChaserFactory implements BotControllerFactory {
    public BotController createMasterBotController(){
        return new GoodBeastChaserMaster();
    }

    public BotController createMiniBotController(){
        return new GoodBeastChaserMini();
    }
}
