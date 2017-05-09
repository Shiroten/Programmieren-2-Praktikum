package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.EntityType;

/**
 * Created by tillm on 09.05.2017.
 */
public interface ControllerContext {
    XY getViewLowerLeft();
    XY getViewUpperRight();
    EntityType getEntityAt(XY xy);
    void move(Vector direction);
    void spawnMiniBot(Vector direction, int energy);
    int getEnergy();
}
