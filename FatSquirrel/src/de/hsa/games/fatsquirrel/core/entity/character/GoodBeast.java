package de.hsa.games.fatsquirrel.core.entity.character;

import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.entity.EntityContext;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

import java.util.logging.Level;
import java.util.logging.Logger;

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

        Logger logger = Logger.getLogger(Launcher.class.getName());
        logger.log(Level.FINEST, "start nextStep() of GoodBeast");

        if (moveCounter == 0) {
            PlayerEntity pe = context.nearestPlayerEntity(this.getCoordinate());
            Vector distance = new Vector(pe.getCoordinate(), this.getCoordinate());

            if (distance.getLength() < context.getGOODBEAST_VIEW_DISTANCE()) {
                XY previousPosition = this.getCoordinate();
                context.tryMove(this, distance.normalizedVector().oppositeVector());

                if (gotStuck(previousPosition)) {
                    tryUnStuck(context, distance.normalizedVector().oppositeVector(), Vector.rotation.clockwise);
                }
            } else
                context.tryMove(this, Vector.randomDirection());
            moveCounter++;
        } else if (moveCounter == context.getBEAST_MOVE_TIME_IN_TICKS())
            moveCounter = 0;
        else
            moveCounter++;
    }

    private void tryUnStuck(EntityContext context, Vector direction, Vector.rotation rotation) {

        //Todo: Rekursive auflösung benötigt.
        XY previousPosition = this.getCoordinate();
        context.tryMove(this, Vector.rotate(rotation, direction));

        if (gotStuck(previousPosition)) {

            if (rotation == Vector.rotation.anticlockwise) {
                rotation = Vector.rotation.clockwise;
            } else {
                rotation = Vector.rotation.anticlockwise;
            }

            context.tryMove(this, Vector.rotate(rotation, direction));
        } else {
            return;
        }

        if (gotStuck(previousPosition)) {
            previousPosition = this.getCoordinate();
            Vector directionTest = Vector.rotate(rotation, direction);
            context.tryMove(this, Vector.rotate(rotation, directionTest));

            if (gotStuck(previousPosition)) {

                if (rotation == Vector.rotation.anticlockwise) {
                    rotation = Vector.rotation.clockwise;
                } else {
                    rotation = Vector.rotation.anticlockwise;
                }

                directionTest = Vector.rotate(rotation, direction);
                context.tryMove(this, Vector.rotate(rotation, directionTest));
            }

        }


    }

    private boolean gotStuck(XY xy) {
        return this.getCoordinate().getX() == xy.getX() && this.getCoordinate().getY() == xy.getY();
    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.entity.character.GoodBeast: " + super.toString());
    }
}
