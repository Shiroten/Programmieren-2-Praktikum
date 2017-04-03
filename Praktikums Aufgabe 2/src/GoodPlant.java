/**
 * Created by tillm on 29.03.2017.
 */
public class GoodPlant extends Entity {
    public static final int START_ENERGY = 100;
    public GoodPlant(int id, XY coordinate) {
        super(START_ENERGY, id, coordinate);
    }

    public void nextStep() {
    }

    public String toString() {
        return ("GoodPlant: " + super.toString());
    }
}
