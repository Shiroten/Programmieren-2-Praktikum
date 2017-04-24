package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 22.04.2017.
 */
public abstract class PlayerEntity extends Character {
    protected int stunTime = 0;

    public PlayerEntity(){
        super();
    }

    public PlayerEntity(int id, XY coordinate){
        super(id, coordinate);
    }

    public void setStunTime(int stunTime){
        this.stunTime = stunTime;
    }

    public int getStunTime(){
        return this.stunTime;
    }
}
