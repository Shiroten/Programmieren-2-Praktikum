/**
 * Created by tillm on 29.03.2017.
 */
public class Wall extends Entity {
    public static final int START_ENERGY = -10;

    public Wall(int id, XY coordinate) {
        super(START_ENERGY, id, coordinate);
    }

    public void nextStep() {
    }

    public String toString() {
        return ("Wall: " + super.toString());
    }
}
