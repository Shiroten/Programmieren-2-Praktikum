package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;

public class GoodBeast extends Character {
    public static final int START_ENERGY = 200;
    public static final EntityType type = EntityType.GOODBEAST;
    private int moveCounter = 0;


    public GoodBeast(int id, XY coordinate) {
        super(START_ENERGY, id, coordinate);
    }

    public EntityType getEntityType() {
        return type;
    }

    public void nextStep(EntityContext context){
        if(moveCounter == 0) {
            PlayerEntity pe = context.nearestPlayerEntity(this.getCoordinate());
            //System.out.println(pe.getCoordinate().toString() + " The GB: " + this.getCoordinate().toString());
            Vector distance = new Vector(pe.getCoordinate(), this.getCoordinate());
            //System.out.println("GoodBeast to Player: " + distance.toString() + " Dis.: " + distance.getLength());
            if (distance.getLength() < 6)
                context.tryMove(this, distance.normalizedVector().oppositeVector());
            else
                context.tryMove(this, distance.randomDirection());
            moveCounter++;
        }
        else if(moveCounter == context.getBEAST_MOVE_TIME_IN_TICKS())
            moveCounter = 0;
        else
            moveCounter++;
    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.GoodBeast: " + super.toString());
    }
}
