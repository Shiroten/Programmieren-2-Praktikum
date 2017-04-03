/**
 * Created by tillm on 29.03.2017.
 */
public class BadBeast extends Entity {
    public static final int START_ENERGY = -150;
    public BadBeast(int id, XY coordinate){
        super(START_ENERGY, id, coordinate);
    }

    public void nextStep(){
        setCoordinate(getCoordinate().randomMove());
    }

    public String toString() {
        return ("BadBeast: " + super.toString());
    }
}
