package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 07.04.2017.
 */
public class State {
    private int highscore;
    private Board board;

    public State(){
        BoardConfig config = new BoardConfig(new XY(12, 12), 1, 1, 4, 3, 3);
        this.board = new Board(config);
    }

    public State(Board board){
        this.board = board;
    }

    public Board getBoard(){
        return board;
    }

    public int getHighscore(){
        return highscore;
    }

    public void update(MoveCommand command){

        FlattenedBoard flat = board.flatten();
        board.getSet().nextStep(flat, command);
    }

    public FlattenedBoard flattenBoard(){
        return board.flatten();
    }

    public Entity[] getEntitySet(){
        return board.getSet().getEntityList();
    }
}
