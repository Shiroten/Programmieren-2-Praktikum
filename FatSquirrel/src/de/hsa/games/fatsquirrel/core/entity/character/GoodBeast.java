package de.hsa.games.fatsquirrel.core.entity.character;

import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.XYsupport;
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
            XY distance = new XY(pe.getCoordinate(), this.getCoordinate());

            if (distance.length() < context.getGOODBEAST_VIEW_DISTANCE()) {
                XY previousPosition = this.getCoordinate();
                context.tryMove(this, XYsupport.oppositeVector(XYsupport.normalizedVector(distance)));

                if (gotStuck(previousPosition)) {
                    tryUnStuck(context, XYsupport.oppositeVector(XYsupport.normalizedVector(distance)), XYsupport.Rotation.clockwise);
                }
            } else
                context.tryMove(this, XYsupport.randomDirection());
            moveCounter++;
        } else if (moveCounter == context.getBEAST_MOVE_TIME_IN_TICKS())
            moveCounter = 0;
        else
            moveCounter++;
    }

    private void tryUnStuck(EntityContext context, XY direction, XYsupport.Rotation rotation) {

        //Todo: Rekursive auflösung benötigt.
        XY previousPosition = this.getCoordinate();
        context.tryMove(this, XYsupport.rotate(rotation, direction));

        if (gotStuck(previousPosition)) {

            if (rotation == XYsupport.Rotation.anticlockwise) {
                rotation = XYsupport.Rotation.clockwise;
            } else {
                rotation = XYsupport.Rotation.anticlockwise;
            }

            context.tryMove(this, XYsupport.rotate(rotation, direction));
        } else {
            return;
        }

        if (gotStuck(previousPosition)) {
            previousPosition = this.getCoordinate();
            XY directionTest = XYsupport.rotate(rotation, direction);
            context.tryMove(this, XYsupport.rotate(rotation, directionTest));

            if (gotStuck(previousPosition)) {

                if (rotation == XYsupport.Rotation.anticlockwise) {
                    rotation = XYsupport.Rotation.clockwise;
                } else {
                    rotation = XYsupport.Rotation.anticlockwise;
                }

                directionTest = XYsupport.rotate(rotation, direction);
                context.tryMove(this, XYsupport.rotate(rotation, directionTest));
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
