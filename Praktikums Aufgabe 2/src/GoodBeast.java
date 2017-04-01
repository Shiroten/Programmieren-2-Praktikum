/**
 * Created by tillm on 29.03.2017.
 */
public class GoodBeast extends Entity {
    public GoodBeast(int id, XY coordinate) {
        super(200, id, coordinate);
    }

    public String toString() {
        return ("GoodBeast: " + super.toString());
    }
}
