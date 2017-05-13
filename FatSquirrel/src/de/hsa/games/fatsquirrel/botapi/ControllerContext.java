package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public interface ControllerContext {

    //Todo: 6.1 Proxy zwischenschalten
    XY getViewLowerLeft();
    XY getViewUpperRight();
    XY locate();
    boolean isMine(XY xy);
    void implode(int impactRadius);
    EntityType getEntityAt(XY xy);
    void move(XY direction);
    void spawnMiniBot(XY direction, int energy);
    XY directionOfMaster();
    long getRemainingSteps();
    default void shout(String text){};
    int getEnergy();

}
