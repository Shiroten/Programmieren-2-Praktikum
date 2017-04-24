package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 23.04.2017.
 */
public abstract class Character extends Entity {
    public Character(int energy, int id, XY coordinate){
        super(energy, id, coordinate);
    }

    public Character(int id, XY coordinate){
        super(id, coordinate);
    }

    public Character(){};

    public abstract void nextStep(EntityContext context);
}
