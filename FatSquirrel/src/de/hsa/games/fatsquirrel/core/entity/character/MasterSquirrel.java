package de.hsa.games.fatsquirrel.core.entity.character;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.XYsupport;
import de.hsa.games.fatsquirrel.core.entity.EntityContext;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public class MasterSquirrel extends PlayerEntity {

    private static final int START_ENERGY = 1000;
    public static final EntityType type = EntityType.MASTERSQUIRREL;
    private int moveCounter;

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
                XY distance = XYsupport.randomDirection();
                context.tryMove(this, distance);
            }
            moveCounter++;
        } else if (moveCounter == 2)
            moveCounter = 0;
        else
            moveCounter++;

    }

    public boolean mySquirrel(MiniSquirrel squirrelToCheck) {
        return this == squirrelToCheck.getDaddy();
    }

}
