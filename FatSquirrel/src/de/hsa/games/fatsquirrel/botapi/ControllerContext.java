package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public interface ControllerContext {
    XY getViewLowerLeft();
    XY getViewUpperRight();
    EntityType getEntityAt(XY xy);
    void move(Vector direction);
    void spawnMiniBot(Vector direction, int energy);
    int getEnergy();
}
