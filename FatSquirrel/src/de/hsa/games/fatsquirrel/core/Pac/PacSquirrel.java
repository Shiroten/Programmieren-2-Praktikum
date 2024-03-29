package de.hsa.games.fatsquirrel.core.Pac;

import de.hsa.games.fatsquirrel.ActionCommand;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.entity.EntityContext;
import de.hsa.games.fatsquirrel.core.entity.EntityType;
import de.hsa.games.fatsquirrel.core.entity.character.HandOperatedMasterSquirrel;

public class PacSquirrel extends HandOperatedMasterSquirrel {
    public static final int START_ENERGY = 1000;
    public static final EntityType type = EntityType.MASTERSQUIRREL;
    private ActionCommand command = ActionCommand.NOWHERE;

    public PacSquirrel(int id, XY coordinate){super(id, coordinate);}
    public void nextStep(EntityContext context){
        /*if(stunTime > 0)
            stunTime--;
        else {
            switch (command) {
                case EAST:
                    context.tryMove(this, XY.RIGHT);
                    break;
                case WEST:
                    context.tryMove(this, XY.LEFT);
                    break;
                case NORTH:
                    context.tryMove(this, XY.UP);
                    break;
                case SOUTH:
                    context.tryMove(this, XY.DOWN);
                    break;
                case NORTHEAST:
                    context.tryMove(this, XY.RIGHT_UP);
                    break;
                case NORTHWEST:
                    context.tryMove(this, XY.LEFT_UP);
                    break;
                case SOUTHEAST:
                    context.tryMove(this, XY.RIGHT_DOWN);
                    break;
                case SOUTHWEST:
                    context.tryMove(this, XY.LEFT_DOWN);
                    break;
                case NOWHERE:
                    break;
                default:
                    break;
            }
            command = ActionCommand.NOWHERE;
        }*/
    }
}
