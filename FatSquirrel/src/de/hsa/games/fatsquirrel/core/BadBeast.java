package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 29.03.2017.
 */
public class BadBeast extends Character {
    public static final int START_ENERGY = -150;
    public static final EntityType type = EntityType.BADBEAST;
    private int moveCounter = 0;
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

        //Todo: Badbest bleibt manchmal einfach stehen über zu lange zeit
        //Todo: BadBeast bleibt bei diagonalen hängen
        if(moveCounter == 0) {
            PlayerEntity pe = context.nearestPlayerEntity(this.getCoordinate());
            //System.out.println(pe.getCoordinate().toString() + " The BB: " + this.getCoordinate().toString());
            Vector distance = new Vector(pe.getCoordinate(), this.getCoordinate());
            //System.out.println("BadBeast to Player: " + distance.toString() + " Dis.: " + distance.getLength());
            if (distance.getLength() < 6)
                //context.tryMove(this, distance.normalizedVector().oppositeVector());
                  context.tryMove(this, distance.normalizedVector());
            else
                context.tryMove(this, distance.randomDirection());
            moveCounter++;
        }
        else if(moveCounter == 3)
            moveCounter = 0;
        else
            moveCounter++;
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
