package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 04.04.2017.
 */
public interface BoardView {
    EntityType getEntityType(int x, int y);
    XY getSize();
}
