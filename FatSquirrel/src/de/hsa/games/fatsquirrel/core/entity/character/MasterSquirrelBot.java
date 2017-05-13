package de.hsa.games.fatsquirrel.core.entity.character;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactoryImpl;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public class MasterSquirrelBot extends MasterSquirrel{

    static class ControllerContextImpl implements ControllerContext{

        @Override
        public XY locate() {
            return null;
        }

        @Override
        public boolean isMine(XY xy) {
            return false;
        }

        @Override
        public void implode(int impactRadius) {
        }

        @Override
        public XY directionOfMaster() {
            return null;
        }

        @Override
        public long getRemainingSteps() {
            return 0;
        }

        //Todo: 6.2 Sichteinschränken vom MasterSquirrel
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
        public void move(XY direction) {

        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {

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
        this.masterBotController = factory.createMasterBotController("de.hsa.games.fatsquirrel.botapi.MasterBotControllerImplShiroten");
    }

        @Override
        public void nextStep(ControllerContext view){
        System.out.println("nextStep of MasterSquirrelBot");
        masterBotController.nextStep(view);

    }

}
