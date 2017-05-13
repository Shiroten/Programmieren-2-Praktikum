package de.hsa.games.fatsquirrel.core.entity.character;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.entity.Entity;
import de.hsa.games.fatsquirrel.core.entity.EntityContext;

public abstract class Character extends Entity {
    Character(int energy, int id, XY coordinate){
        super(energy, id, coordinate);
    }

    Character(int id, XY coordinate){
        super(id, coordinate);
    }

    Character(){}

    public abstract void nextStep(EntityContext context);

    public XY possibleMove(EntityContext context, XY wantedDirection){
        //TODO: possibleMove implementieren
        return XY.ZERO_ZERO;
    }

    //Todo: Implosion Methode implementieren

    void implode(EntityContext context, int impactRadius){

        context.implode(this, impactRadius);

    }
}
