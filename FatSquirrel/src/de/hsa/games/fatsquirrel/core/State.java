package de.hsa.games.fatsquirrel.core;

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

    public void update(){

        //TODO: Update sinnvoll implementieren
        FlattenedBoard flat = board.flatten();
        for(Entity e : board.getSet().getEntityList()){
            switch (e.getEntityType()){

            }
        }
    }

    public FlattenedBoard flattenBoard(){
        return board.flatten();
    }
}
