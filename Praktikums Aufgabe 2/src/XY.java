import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tillm on 28.03.2017.
 */
public class XY {
    private final int x;
    private final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public XY addVector(Vector vector) {

        int xOffset = vector.getX();
        int yOffset = vector.getY();

        return new XY(x + xOffset, y + yOffset);
    }

    public XY randomMove() {

        Vector offset = new Vector();
        return addVector(offset.randomDirection());
    }

}
