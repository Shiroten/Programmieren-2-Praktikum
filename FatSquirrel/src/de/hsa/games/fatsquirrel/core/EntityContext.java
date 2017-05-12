package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.Pac.PacSquirrel;

public interface EntityContext {
    XY getSize();
    void tryMove(MiniSquirrel miniSquirrel, Vector vector);
    void tryMove(GoodBeast goodBeast, Vector vector);
    void tryMove(BadBeast badBeast, Vector vector);
    void tryMove(MasterSquirrel masterSquirrel, Vector vector);
    void tryMove(PacSquirrel pacSquirrel, Vector vector);
    PlayerEntity nearestPlayerEntity(XY pos);

    void killEntity(Entity entity);
    void killAndReplace(Entity entity);
    EntityType getEntityType(XY xy);
    void tryMove(StandardMiniSquirrel standardMiniSquirrel);
    int getBEAST_MOVE_TIME_IN_TICKS();
    int getMINI_SQUIRREL_MOVE_TIME_IN_TICKS();
    int getGOODBEAST_VIEW_DISTANCE();
    int getBADBEAST_VIEW_DISTANCE();
}
