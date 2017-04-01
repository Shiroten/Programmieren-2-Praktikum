import java.util.ArrayList;

/**
 * Created by tillm on 29.03.2017.
 */
public class MasterSquirrel extends Entity {

    ArrayList<MiniSquirrel> minions = new ArrayList<MiniSquirrel>();

    public MasterSquirrel(int id, XY coordinate) {
        super(1000, id, coordinate);
    }

    public MasterSquirrel(int id, XY coordinate, int startEnergy) {
        super(startEnergy, id, coordinate);
    }

    public MasterSquirrel() {

    }

    private boolean isMinion(Entity e) {
        for (Entity en : minions) {
            if (en.getId() == e.getId())
                return true;
        }
        return false;
    }

    public void nextStep() {
    }

    public String toString() {
        return ("MasterSquirrel: " + super.toString());
    }


}
