package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.entity.Entity;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public interface BoardView {
    EntityType getEntityType(XY xy);
    Entity getEntity(XY xy);
    XY getSize();
}
