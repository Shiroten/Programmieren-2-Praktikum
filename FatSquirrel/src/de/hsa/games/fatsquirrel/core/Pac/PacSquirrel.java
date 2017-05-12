package de.hsa.games.fatsquirrel.core.Pac;

import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.EntityContext;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.HandOperatedMasterSquirrel;

public class PacSquirrel extends HandOperatedMasterSquirrel {
    public static final int START_ENERGY = 1000;
    public static final EntityType type = EntityType.PACSQUIRREL;
    private MoveCommand command = MoveCommand.NOWHERE;

    public PacSquirrel(int id, XY coordinate){super(id, coordinate);}
    public void nextStep(EntityContext context){
        if(stunTime > 0)
            stunTime--;
        else {
            switch (command) {
                case EAST:
                    context.tryMove(this, new Vector(1, 0));
                    break;
                case WEST:
                    context.tryMove(this, new Vector(-1, 0));
                    break;
                case NORTH:
                    context.tryMove(this, new Vector(0, -1));
                    break;
                case SOUTH:
                    context.tryMove(this, new Vector(0, 1));
                    break;
                case NORTHEAST:
                    context.tryMove(this, new Vector(1, -1));
                    break;
                case NORTHWEST:
                    context.tryMove(this, new Vector(-1, -1));
                    break;
                case SOUTHEAST:
                    context.tryMove(this, new Vector(1, 1));
                    break;
                case SOUTHWEST:
                    context.tryMove(this, new Vector(-1, 1));
                    break;
                case NOWHERE:
                    break;
                default:
                    break;
            }
            command = MoveCommand.NOWHERE;
        }
    }
}
