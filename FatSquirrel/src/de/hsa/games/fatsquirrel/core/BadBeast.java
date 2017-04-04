package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 29.03.2017.
 */
public class BadBeast extends Entity {
    public static final int START_ENERGY = -150;
    public static final EntityType type = EntityType.BADBEAST;
    public BadBeast(int id, XY coordinate){
        super(START_ENERGY, id, coordinate);
    }

    public void nextStep(){
        setCoordinate(getCoordinate().randomMove());
    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.BadBeast: " + super.toString());
    }
}
