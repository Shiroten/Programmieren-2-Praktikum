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

    public void nextStep(EntityContext context) {
        if (moveCounter == 0) {
            PlayerEntity pe = context.nearestPlayerEntity(this.getCoordinate());
            //System.out.println(pe.getCoordinate().toString() + " The GB: " + this.getCoordinate().toString());
            Vector distance = new Vector(pe.getCoordinate(), this.getCoordinate());
            //System.out.println("GoodBeast to Player: " + distance.toString() + " Dis.: " + distance.getLength());
            if (distance.getLength() < 6) {
                XY previousPosition = this.getCoordinate();
                context.tryMove(this, distance.normalizedVector().oppositeVector());

                if(gotStuck(previousPosition)){
                    tryUnStuck(context,distance.normalizedVector().oppositeVector(),Vector.rotation.clockwise);
                }
            } else
                context.tryMove(this, distance.randomDirection());
            moveCounter++;
        } else if (moveCounter == context.getBEAST_MOVE_TIME_IN_TICKS())
            moveCounter = 0;
        else
            moveCounter++;
    }
    private void tryUnStuck(EntityContext context, Vector direction, Vector.rotation rotation){

        //Todo: Rekursive auflösung benötigt.
        XY previousPosition = this.getCoordinate();
        context.tryMove(this, Vector.rotate(rotation,direction));

        if(gotStuck(previousPosition)){

            if(rotation == Vector.rotation.anticlockwise){
                rotation = Vector.rotation.clockwise;
            }else{
                rotation = Vector.rotation.anticlockwise;
            }

            context.tryMove(this, Vector.rotate(rotation,direction));
        }else{
            return;
        }

        if(gotStuck(previousPosition)){
            previousPosition = this.getCoordinate();
            Vector directionTest = Vector.rotate(rotation,direction);
            context.tryMove(this, Vector.rotate(rotation,directionTest));

            if(gotStuck(previousPosition)){

                if(rotation == Vector.rotation.anticlockwise){
                    rotation = Vector.rotation.clockwise;
                }else{
                    rotation = Vector.rotation.anticlockwise;
                }

                directionTest = Vector.rotate(rotation,direction);
                context.tryMove(this, Vector.rotate(rotation,directionTest));
            }else{
                return;
            }

        }


    }

    private boolean gotStuck (XY xy){
        return this.getCoordinate().getX() == xy.getX() && this.getCoordinate().getY() == xy.getY();
    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.GoodBeast: " + super.toString());
    }
}
