package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;

/**
 * Created by tillm on 11.05.2017.
 */
public class MiniSquirrelBot {
    public class MasterSquirrelBot {
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
        private BotControllerFactory factory = new BotControllerFactory();

        public MasterSquirrelBot(){
            this.miniBotController = factory.createMiniBotController();
        }

        public void nextStep(ControllerContext view){
            miniBotController.nextStep(view);
        }

    }
}
