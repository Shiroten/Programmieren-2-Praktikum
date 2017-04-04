package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 04.04.2017.
 */
public interface EntityContext {
    XY getSize();
    void tryMove(MiniSquirrel miniSquirrel, Vector vector);
    void tryMove(GoodBeast goodBeast, Vector vector);
    void tryMove(BadBeast badBeast, Vector vector);
    void tryMove(MasterSquirrel masterSquirrel, Vector vector);
    HandOperatedMasterSquirrel nearestPlayerEntity(XY pos);

    void killEntity(Entity entity);
    void killAndReplace(Entity entity);
    EntityType getEntityType(XY xy);
}
