package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 29.03.2017.
 */
public class GoodBeast extends Entity {
    public static final int START_ENERGY = 200;
    public static final EntityType type = EntityType.GOODBEAST;
    public GoodBeast(int id, XY coordinate) {
        super(START_ENERGY, id, coordinate);
    }

    public EntityType getEntityType() {
        return type;
    }

    public void nextStep(){
        setCoordinate(getCoordinate().randomMove());
    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.GoodBeast: " + super.toString());
    }
}
