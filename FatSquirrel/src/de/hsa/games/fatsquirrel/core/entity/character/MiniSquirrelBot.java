package de.hsa.games.fatsquirrel.core.entity.character;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactoryImpl;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public class MiniSquirrelBot extends MiniSquirrel {
    class ControllerContextImpl implements ControllerContext {

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

    private BotController miniBotController;
    private BotControllerFactoryImpl factory = new BotControllerFactoryImpl();

    public MiniSquirrelBot(int id, XY position, int energy, MasterSquirrel daddy) {
        super(id, position, energy, daddy);
        this.miniBotController = factory.createMiniBotController();
    }

    public void nextStep(ControllerContext view) {
        miniBotController.nextStep(view);
    }


}
