package de.hsa.games.fatsquirrel.core.character;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactoryImpl;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public class MasterSquirrelBot extends MasterSquirrel{

    class ControllerContextImpl implements ControllerContext{


        @Override
        public XY getViewLowerLeft() {
            return null;
        }

        @Override
        public XY getViewUpperRight() {
            return null;
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            return null;
        }

        @Override
        public void move(Vector direction) {

        }

        @Override
        public void spawnMiniBot(Vector direction, int energy) {

        }

        @Override
        public int getEnergy() {
            return 0;
        }
    }

    private BotController masterBotController;
    private BotControllerFactoryImpl factory = new BotControllerFactoryImpl();

    public MasterSquirrelBot(int id, XY position){
        super(id, position);
        this.masterBotController = factory.createMasterBotController();
    }

    public void nextStep(ControllerContext view){

        masterBotController.nextStep(view);
    }

}
