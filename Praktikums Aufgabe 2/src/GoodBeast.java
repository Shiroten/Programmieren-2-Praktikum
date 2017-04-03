/**
 * Created by tillm on 29.03.2017.
 */
public class GoodBeast extends Entity {
    public static final int START_ENERGY = 200;
    public GoodBeast(int id, XY coordinate) {
        super(START_ENERGY, id, coordinate);
    }

    public void nextStep(){
        setCoordinate(getCoordinate().randomMove());
    }

    public String toString() {
        return ("GoodBeast: " + super.toString());
    }
}
