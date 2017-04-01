/**
 * Created by tillm on 29.03.2017.
 */
public class BadBeast extends Entity {
    public BadBeast(int id, XY coordinate){
        super(-150, id, coordinate);
    }

    public String toString() {
        return ("BadBeast: " + super.toString());
    }
}
