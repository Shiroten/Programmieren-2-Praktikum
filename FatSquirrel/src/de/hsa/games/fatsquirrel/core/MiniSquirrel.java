package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 29.03.2017.
 */
public class MiniSquirrel extends PlayerEntity {
    public static final EntityType type = EntityType.MINISQUIRREL;
    protected MasterSquirrel daddy;
    private int moveCounter = 0;

    public EntityType getEntityType() {
        return type;
    }

    public MiniSquirrel(int id, XY coordinate, int startEnergy, MasterSquirrel daddy) {
        super(id, coordinate);
        this.daddy = daddy;
        this.energy = startEnergy;
    }

    public MasterSquirrel getDaddy(){
        return daddy;
    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.MiniSquirrel: " + super.toString() + "Luke, wer ist dein Vater? ParentID:" + daddy.getId());
    }

    public void nextStep(EntityContext context){

        if(moveCounter == 0) {
            if(stunTime > 0)
                stunTime--;
            else {
                Vector distance = Vector.randomDirection();
                context.tryMove(this, distance);
            }
            moveCounter++;
        }
        else if(moveCounter == context.getMINI_SQUIRREL_MOVE_TIME_IN_TICKS())
            moveCounter = 0;
        else
            moveCounter++;


    }
}
