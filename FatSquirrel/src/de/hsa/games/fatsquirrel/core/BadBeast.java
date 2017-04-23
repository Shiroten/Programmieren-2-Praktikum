package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 29.03.2017.
 */
public class BadBeast extends MovableEntity {
    public static final int START_ENERGY = -150;
    public static final EntityType type = EntityType.BADBEAST;
    private int lives;
    public BadBeast(int id, XY coordinate){
        super(START_ENERGY, id, coordinate);
        this.lives = 7;
    }

    @Override
    public EntityType getEntityType() {
        return type;
    }

    public void nextStep(EntityContext context){
        PlayerEntity pe = context.nearestPlayerEntity(this.getCoordinate());
        Vector distance = new Vector(pe.getCoordinate(), this.getCoordinate());
        if(distance.getLength() < 6)
            context.tryMove(this, distance.normalizedVector());
        else
            this.setCoordinate(this.getCoordinate().randomMove());

    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.BadBeast: " + super.toString());
    }

    public void bites(){
        lives --;
    }

    public int getLives(){
        return this.lives;
    }
}
