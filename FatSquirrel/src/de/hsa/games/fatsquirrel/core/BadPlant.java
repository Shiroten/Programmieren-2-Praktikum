package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 29.03.2017.
 */
public class BadPlant extends Entity {
    public static final int START_ENERGY = -100;
    public static final EntityType type = EntityType.BADPLANT;
    public BadPlant(int id, XY coordinate) {
        super(START_ENERGY, id, coordinate);
    }

    public EntityType getEntityType() {
        return type;
    }

    public void nextStep(){
    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.BadPlant: " + super.toString());
    }
}
