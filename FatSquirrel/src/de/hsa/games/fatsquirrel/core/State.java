package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.XY;

import java.util.logging.Level;
import java.util.logging.Logger;

public class State {
    private int highscore;
    private Board board;

    public State() {
        BoardConfig config = new BoardConfig(new XY(12, 12), 1, 1, 4, 3, 3);
        this.board = new Board(config);
    }

    public State(Board board) {
        this.board = board;
    }

    public State(XY size, int TICKLENGTH, int NUMBER_OF_GB, int NUMBER_OF_BB, int NUMBER_OF_GP, int NUMBER_OF_BP, int NUMBER_OF_WA, int GOODBEAST_VIEW_DISTANCE, int BADBEAST_VIEW_DISTANCE, GameType gameType){
        this.board = new Board(new BoardConfig(size, TICKLENGTH, NUMBER_OF_GB, NUMBER_OF_BB, NUMBER_OF_GP, NUMBER_OF_BP, NUMBER_OF_WA, GOODBEAST_VIEW_DISTANCE, BADBEAST_VIEW_DISTANCE, gameType));
    }

    public Board getBoard() {
        return board;
    }

    public int getHighscore() {
        return highscore;
    }

    public void update() {

        Logger logger = Logger.getLogger(Launcher.class.getName());
        logger.log(Level.FINER, "start update() from State");

        FlattenedBoard flat = board.flatten();
        board.getSet().nextStep(flat);
    }

    public FlattenedBoard flattenBoard() {
        return board.flatten();
    }

    public Entity[] getEntitySet() {
        return board.getSet().getEntityList();
    }
}
