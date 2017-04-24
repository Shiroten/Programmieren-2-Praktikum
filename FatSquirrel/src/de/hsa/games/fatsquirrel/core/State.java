package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.MoveCommand;

/**
 * Created by tillm on 07.04.2017.
 */
public class State {
    private int highscore;
    private Board board;

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
}
