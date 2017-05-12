package de.hsa.games.fatsquirrel.core.entity;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.entity.Entity;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public class Wall extends Entity {
    public static final int START_ENERGY = -10;
    public static final EntityType type = EntityType.WALL;

    public EntityType getEntityType() {
        return type;
    }

    public Wall(int id, XY coordinate) {
        super(START_ENERGY, id, coordinate);
    }

    public void nextStep() {
    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.entity.Wall: " + super.toString());
    }
}
