/**
 * Created by Shiroten on 03.04.2017.
 */
public class Vector {

    private final int xDifference;
    private final int yDifference;

    public int getX() {
        return xDifference;
    }

    public int getY() {
        return yDifference;
    }

    public Vector(int x, int y) {
        this.xDifference = x;
        this.yDifference = y;
    }

    public Vector(){
        this.xDifference = 0;
        this.yDifference = 0;
    }

    public Vector randomDirection() {
        int random = (int) Math.round(Math.random() * 7);
        Vector newPosition;

        switch (random) {
            case 0:
                newPosition = new Vector(1, +1);
                break;
            case 1:
                newPosition = new Vector(1, 0);
                break;
            case 2:
                newPosition = new Vector(0, 1);
                break;
            case 3:
                newPosition = new Vector(-1, 1);
                break;
            case 4:
                newPosition = new Vector(-1, 0);
                break;
            case 5:
                newPosition = new Vector(-1, -1);
                break;
            case 6:
                newPosition = new Vector(0, -1);
                break;
            case 7:
                newPosition = new Vector(1, -1);
                break;
            default:
                newPosition = new Vector(0, 0);
        }
        return newPosition;
    }
}
