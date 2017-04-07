package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 07.04.2017.
 */
public class Board {
    private EntitySet set;
    private BoardConfig config;

    public Board(){
        set = new EntitySet();
        config = new BoardConfig(new XY(100, 100), 400);
    }

    public Board(XY size){
        set = new EntitySet();
        config = new BoardConfig(new XY(100, 100), 400);
    }

    public EntitySet getSet(){
        return set;
    }

    public BoardConfig getConfig(){
        return config;
    }

    public FlattenedBoard flatten(){
        Entity[][] list = new Entity[config.getSize().getY()][config.getSize().getX()];
        for(int i = 0; i < set.getNumberOfEntities(); i++){
            Entity dummy = set.getEntity(i);
            list[dummy.getCoordinate().getY()][dummy.getCoordinate().getX()] = dummy;
        }

        FlattenedBoard fb = new FlattenedBoard(config.getSize(), this, list);

        return fb;
    }

    //TODO: Landschaftsgenerator
}
