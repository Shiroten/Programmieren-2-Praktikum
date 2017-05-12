package de.hsa.games.fatsquirrel.core.entity;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.entity.Entity;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public class GoodPlant extends Entity {
    public static final int START_ENERGY = 100;
    public static final EntityType type = EntityType.GOODPLANT;
    public GoodPlant(int id, XY coordinate) {
        super(START_ENERGY, id, coordinate);
    }

    public EntityType getEntityType() {
        return type;
    }

    public void nextStep() {
    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.entity.GoodPlant: " + super.toString());
    }
}
