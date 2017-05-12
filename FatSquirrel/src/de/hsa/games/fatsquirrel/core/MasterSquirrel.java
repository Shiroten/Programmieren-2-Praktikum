package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 29.03.2017.
 */
public class MasterSquirrel extends PlayerEntity {

    public static final int START_ENERGY = 1000;
    public static final EntityType type = EntityType.MASTERSQUIRREL;
    protected int moveCounter;

    public EntityType getEntityType() {
        return type;
    }

    public MasterSquirrel(int id, XY coordinate) {
        super(id, coordinate);
        this.energy = START_ENERGY;
    }

    public MasterSquirrel() {
    }

    public void nextStep(EntityContext context) {

        if (moveCounter == 0) {
            if (stunTime > 0)
                stunTime--;
            else {
                Vector distance = Vector.randomDirection();
                context.tryMove(this, distance);
            }
            moveCounter++;
        } else if (moveCounter == 2)
            moveCounter = 0;
        else
            moveCounter++;

    }

    public boolean mySquirrel(MiniSquirrel squirrelToCheck) {
        if (this == squirrelToCheck.getDaddy()) {
            return true;
        }
        return false;
    }

}
