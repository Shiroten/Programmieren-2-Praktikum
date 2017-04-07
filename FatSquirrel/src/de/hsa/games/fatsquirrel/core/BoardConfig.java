package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 07.04.2017.
 */
public class BoardConfig {
    private final XY size;
    private final int wallCount;

    public BoardConfig(XY size, int wallCount){
        this.size = size;
        this.wallCount = wallCount;
    }

    public XY getSize(){
        return size;
    }

    public int getWallCount(){
        return wallCount;
    }

}
