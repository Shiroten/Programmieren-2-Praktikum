package de.hsa.games.fatsquirrel.core.entity.character;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.XYsupport;
import de.hsa.games.fatsquirrel.core.entity.Entity;
import de.hsa.games.fatsquirrel.core.entity.EntityContext;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public abstract class Character extends Entity {
    private XY lastVector = XY.ZERO_ZERO;

    public XY getLastVector() {
        return lastVector;
    }

    public void setLastVector(XY lastVector) {
        this.lastVector = lastVector;
    }

    Character(int energy, int id, XY coordinate) {
        super(energy, id, coordinate);
    }

    Character(int id, XY coordinate) {
        super(id, coordinate);
    }

    Character() {
    }


    public abstract void nextStep(EntityContext context);

    public XY possibleMove(EntityContext context, XY wantedDirection) {
        //TODO: possibleMove implementieren
        return XY.ZERO_ZERO;
    }

    void implode(EntityContext context, int impactRadius) {

        context.implode(this, impactRadius);

    }

    void tryUnStuck(EntityContext context, XY direction, XYsupport.Rotation rotation) {

        //Todo: Rekursive auflösung benötigt.
        XY previousPosition = this.getCoordinate();
        EntityType et = this.getEntityType();

            switch (et) {
                case MINISQUIRREL:
                    context.tryMove((MiniSquirrel)this, XYsupport.rotate(rotation, direction));
                    break;
                case MASTERSQUIRREL:
                    context.tryMove((MasterSquirrel)this, XYsupport.rotate(rotation, direction));
                    break;
                case GOODBEAST:
                    context.tryMove((GoodBeast)this, XYsupport.rotate(rotation, direction));
                    break;
                case BADBEAST:
                    context.tryMove((BadBeast)this, XYsupport.rotate(rotation, direction));
                    break;
            }

        if (gotStuck(previousPosition)) {

            if (rotation == XYsupport.Rotation.anticlockwise) {
                rotation = XYsupport.Rotation.clockwise;
            } else {
                rotation = XYsupport.Rotation.anticlockwise;
            }

            switch (et) {
                case MINISQUIRREL:
                    context.tryMove((MiniSquirrel)this, XYsupport.rotate(rotation, direction));
                    break;
                case MASTERSQUIRREL:
                    context.tryMove((MasterSquirrel)this, XYsupport.rotate(rotation, direction));
                    break;
                case GOODBEAST:
                    context.tryMove((GoodBeast)this, XYsupport.rotate(rotation, direction));
                    break;
                case BADBEAST:
                    context.tryMove((BadBeast)this, XYsupport.rotate(rotation, direction));
                    break;
            }
        } else {
            return;
        }

        if (gotStuck(previousPosition)) {
            previousPosition = this.getCoordinate();
            XY directionTest = XYsupport.rotate(rotation, direction);
            switch (et) {
                case MINISQUIRREL:
                    context.tryMove((MiniSquirrel)this, XYsupport.rotate(rotation, directionTest));
                    break;
                case MASTERSQUIRREL:
                    context.tryMove((MasterSquirrel)this, XYsupport.rotate(rotation, directionTest));
                    break;
                case GOODBEAST:
                    context.tryMove((GoodBeast)this, XYsupport.rotate(rotation, directionTest));
                    break;
                case BADBEAST:
                    context.tryMove((BadBeast)this, XYsupport.rotate(rotation, directionTest));
                    break;
            }

            if (gotStuck(previousPosition)) {

                if (rotation == XYsupport.Rotation.anticlockwise) {
                    rotation = XYsupport.Rotation.clockwise;
                } else {
                    rotation = XYsupport.Rotation.anticlockwise;
                }

                directionTest = XYsupport.rotate(rotation, direction);
                switch (et) {
                    case MINISQUIRREL:
                        context.tryMove((MiniSquirrel)this, XYsupport.rotate(rotation, directionTest));
                        break;
                    case MASTERSQUIRREL:
                        context.tryMove((MasterSquirrel)this, XYsupport.rotate(rotation, directionTest));
                        break;
                    case GOODBEAST:
                        context.tryMove((GoodBeast)this, XYsupport.rotate(rotation, directionTest));
                        break;
                    case BADBEAST:
                        context.tryMove((BadBeast)this, XYsupport.rotate(rotation, directionTest));
                        break;
                }
            }

        }


    }

    boolean gotStuck(XY xy) {
        return this.getCoordinate().getX() == xy.getX() && this.getCoordinate().getY() == xy.getY();
    }
}
