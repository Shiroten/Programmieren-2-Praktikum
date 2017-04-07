package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 29.03.2017.
 */
public class MiniSquirrel extends MasterSquirrel {
    public static final EntityType type = EntityType.MINISQUIRREL;
    private MasterSquirrel daddy;

    public EntityType getEntityType() {
        return type;
    }

    public MiniSquirrel(int id, XY coordinate, int startEnergy, MasterSquirrel daddy) {
        super(id, coordinate, startEnergy);
        this.daddy = daddy;
    }

    public MasterSquirrel getDaddy(){
        return daddy;
    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.MiniSquirrel: " + super.toString() + "Luke, wer ist dein Vater? ParentID:" + daddy.getId());
    }

    public void nextStep(){
    }
}
