package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

public interface BoardView {
    EntityType getEntityType(XY xy);
    Entity getEntity(XY xy);
    XY getSize();
}
