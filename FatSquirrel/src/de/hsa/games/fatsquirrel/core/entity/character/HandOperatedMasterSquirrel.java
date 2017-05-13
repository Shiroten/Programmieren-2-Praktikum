package de.hsa.games.fatsquirrel.core.entity.character;

import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.entity.EntityContext;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public class HandOperatedMasterSquirrel extends MasterSquirrel {

    public static final EntityType type = EntityType.MASTERSQUIRREL;
    private MoveCommand command = MoveCommand.NOWHERE;

    public HandOperatedMasterSquirrel(int id, XY coordinate) {
        super(id, coordinate);
    }

    public void setCommand(MoveCommand command) {
        this.command = command;
    }

    public EntityType getEntityType() {
        return type;
    }

    public void nextStep(EntityContext context){
        if(stunTime > 0)
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
            command = MoveCommand.NOWHERE;
        }
    }
}
