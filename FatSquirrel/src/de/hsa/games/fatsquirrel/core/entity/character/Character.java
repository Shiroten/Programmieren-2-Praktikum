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

    }

    boolean gotStuck(XY xy) {
        return this.getCoordinate().getX() == xy.getX() && this.getCoordinate().getY() == xy.getY();
    }
}
