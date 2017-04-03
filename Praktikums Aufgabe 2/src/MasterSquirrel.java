import javax.print.attribute.HashDocAttributeSet;
import java.util.ArrayList;

/**
 * Created by tillm on 29.03.2017.
 */
public class MasterSquirrel extends Entity {
    public static final int START_ENERGY = 1000;

    public MasterSquirrel(int id, XY coordinate) {
        super(START_ENERGY, id, coordinate);
    }

    public MasterSquirrel(int id, XY coordinate, int startEnergy) {
        super(startEnergy, id, coordinate);
    }

    public MasterSquirrel() {
    }

    public void nextStep() {
    }

    public boolean mySquirrel(MiniSquirrel squirrelToCheck) {
        if (this == squirrelToCheck.getDaddy()) {
            return true;
        }
        return false;
    }

    public String toString() {
        return ("MasterSquirrel: " + super.toString());
    }

}
