/**
 * Created by tillm on 29.03.2017.
 */
public class MiniSquirrel extends MasterSquirrel {
    public MiniSquirrel(int id, XY coordinate, int startEnergy) {
        super(id, coordinate, startEnergy);
    }

    public String toString() {
        return ("MiniSquirrel: " + super.toString());
    }
}
