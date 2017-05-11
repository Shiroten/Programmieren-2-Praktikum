package de.hsa.games.fatsquirrel;

/**
 * Created by tillm on 28.03.2017.
 */
public class XY {
    private final int x;
    private final int y;

    public static final XY ZERO_ZERO = new XY(0, 0);
    public static final XY RIGHT = new XY(1, 0);
    public static final XY LEFT = new XY(-1, 0);
    public static final XY UP = new XY(0, -1);
    public static final XY DOWN = new XY(0, 1);
    public static final XY RIGHT_UP = new XY(1, -1);
    public static final XY RIGHT_DOWN = new XY(1, 1);
    public static final XY LEFT_UP = new XY(-1, -1);
    public static final XY LEFT_DOWN = new XY(-1, 1);

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

    public String toString(){
        return new String("x: " + x + " y: "+ y);
    }

}
