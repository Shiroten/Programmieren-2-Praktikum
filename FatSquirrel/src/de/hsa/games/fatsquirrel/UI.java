package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.BoardView;

/**
 * Created by tillm on 04.04.2017.
 */
public interface UI {
    void render(BoardView view);
    MoveCommand getDirection();

}
