package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public interface ControllerContext {

    //Todo: 6.1 Proxy zwischenschalten
    XY getViewLowerLeft();
    XY getViewUpperRight();
    EntityType getEntityAt(XY xy);
    void move(XY direction);
    void spawnMiniBot(XY direction, int energy);
    int getEnergy();

    //Todo: 6.2 C Himmelsrichtung des Masters für Bots implentieren
}
