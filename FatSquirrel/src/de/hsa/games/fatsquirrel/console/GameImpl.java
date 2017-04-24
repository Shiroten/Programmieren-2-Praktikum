package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.State;

/**
 * Created by tillm on 22.04.2017.
 */
public class GameImpl extends Game {

    public GameImpl(){
        this.setUi(new ConsoleUI());

        //Todo: Board in State kapseln
        BoardConfig config = new BoardConfig(new XY(12, 12), 1, 1, 4, 3, 3);
        Board board = new Board(config);
        this.setState(new State(board));
    }

    protected void processInput(){
        this.command = this.getUi().getDirection();
    }

    protected void render(){
        this.getUi().render(this.getState().flattenBoard());
    }

    protected void update(){
        getState().update(command);
    }
}
