package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 23.04.2017.
 */
public abstract class MovableEntity extends Entity {
    public MovableEntity(int energy, int id, XY coordinate){
        super(energy, id, coordinate);
    }

    public MovableEntity(int id, XY coordinate){
        super(id, coordinate);
    }

    public  MovableEntity(){};

    public abstract void nextStep(EntityContext context);
}
