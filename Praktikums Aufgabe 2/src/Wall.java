/**
 * Created by tillm on 29.03.2017.
 */
public class Wall extends Entity {

    public Wall(int id, XY coordinate) {
        super(-10, id, coordinate);
    }

    public void nextStep() {
    }

    public String toString() {
        return ("Wall: " + super.toString());
    }
}
