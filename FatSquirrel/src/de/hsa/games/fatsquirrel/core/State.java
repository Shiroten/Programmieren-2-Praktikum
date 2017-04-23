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

        //TODO: Update sinnvoll implementieren
        FlattenedBoard flat = board.flatten();
        /*for(Entity e : board.getSet().getEntityList()){
            switch (e.getEntityType()){
                case BADBEAST: ((BadBeast) e).nextStep(flat); break;
                case GOODBEAST: ((GoodBeast) e).nextStep(flat); break;
                case MINISQUIRREL: ((MiniSquirrel) e).nextStep(flat); break;
                case MASTERSQUIRREL: ((MasterSquirrel) e).nextStep(flat);
                case HANDOPERATEDMASTERSQUIRREL: ((HandOperatedMasterSquirrel) e).nextStep(command, flat);
            }
        }*/
        board.getSet().nextStep(flat, command);
    }

    public FlattenedBoard flattenBoard(){
        return board.flatten();
    }
}
