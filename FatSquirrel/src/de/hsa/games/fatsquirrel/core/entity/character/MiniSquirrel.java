package de.hsa.games.fatsquirrel.core.entity.character;

import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.XYsupport;
import de.hsa.games.fatsquirrel.core.entity.EntityContext;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MiniSquirrel extends PlayerEntity {
    public static final EntityType type = EntityType.MINISQUIRREL;
    private MasterSquirrel daddy;
    int moveCounter = 0;
    int implosionRadius = 5;

    boolean impload = false;

    public EntityType getEntityType() {
        return type;
    }


    public MiniSquirrel(int id, XY coordinate, int startEnergy, MasterSquirrel daddy) {
        super(id, coordinate);
        this.daddy = daddy;
        this.energy = startEnergy;
    }

    public MasterSquirrel getDaddy() {
        return daddy;
    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.entity.character.MiniSquirrel: " + super.toString() + "Luke, wer ist dein Vater? ParentID:" + daddy.getId());
    }

    public void nextStep(EntityContext context) {

        Logger logger = Logger.getLogger(Launcher.class.getName());
        logger.log(Level.FINEST, "start nextStep() of MiniSquirrel");

        if (moveCounter == 0) {
            if (stunTime > 0)
                stunTime--;
            else {
                if (impload) {
                    implode(context, implosionRadius);
                } else {

                    XY distance = XYsupport.randomDirection();
                    context.tryMove(this, distance);
                }
            }
            moveCounter++;
        } else if (moveCounter == context.getMINI_SQUIRREL_MOVE_TIME_IN_TICKS())
            moveCounter = 0;
        else
            moveCounter++;


    }

    public void impload(int implosionRadius) {
        this.impload = true;
        this.implosionRadius = implosionRadius;
    }
}
