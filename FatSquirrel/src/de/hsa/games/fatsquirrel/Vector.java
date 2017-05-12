package de.hsa.games.fatsquirrel;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Math.*;

/*public class Vector {

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

    public Vector(XY xy1, XY xy2) {
        this.xDifference = xy1.getX() - xy2.getX();
        this.yDifference = xy1.getY() - xy2.getY();
    }

    public Vector() {
        this.xDifference = 0;
        this.yDifference = 0;
    }

    public static Vector moveCommandToVector(MoveCommand move) {

        switch (move) {
            //WASD
            case NORTH:
                return new Vector(+0, -1);
            case WEST:
                return new Vector(-1, +0);
            case SOUTH:
                return new Vector(+0, +1);
            case EAST:
                return new Vector(+1, +0);

            //Diagonal
            case NORTHEAST:
                return new Vector(+1, -1);
            case NORTHWEST:
                return new Vector(-1, -1);
            case SOUTHEAST:
                return new Vector(+1, +1);
            case SOUTHWEST:
                return new Vector(-1, +1);

            //SpecialCase
            case NOWHERE:
                return new Vector(+0, +0);
            default:
                return new Vector(+0, +0);
        }

    }

    public static Vector randomDirection() {

        int x = ThreadLocalRandom.current().nextInt(0, 3) - 1;
        int y = ThreadLocalRandom.current().nextInt(1, 4) - 2;

        return new Vector(x, y);

    }

    public int getLength() {

        //Gibt die Länge in Schritten aus
        if (Math.abs(this.xDifference) > Math.abs(this.yDifference))
            return Math.abs(this.xDifference);
        return Math.abs(this.yDifference);
    }

    public Vector normalizedVector() {
        int newX, newY;
        if (xDifference == 0) {
            if (yDifference == 0)
                return new Vector(0, 0);
            else if (yDifference < 0) {
                return new Vector(0, -1);
            } else {
                return new Vector(0, 1);
            }
        } else if (yDifference == 0) {
            if (xDifference < 0) {
                return new Vector(-1, 0);
            } else {
                return new Vector(1, 0);
            }
        } else {
            if (xDifference < 0)
                newX = Math.round(-1 * (Math.abs(xDifference) / Math.abs(yDifference)));
            else
                newX = Math.round(Math.abs(xDifference) / Math.abs(yDifference));

            if (yDifference < 0)
                newY = Math.round(-1 * (Math.abs(yDifference) / Math.abs(xDifference)));
            else
                newY = Math.round(Math.abs(yDifference) / Math.abs(xDifference));
        }
        newX = normalizeNumber(newX);
        newY = normalizeNumber(newY);
        return new Vector(newX, newY);
    }

    private int normalizeNumber(int i) {
        if (i >= 1)
            return 1;
        else if (i <= -1)
            return -1;
        else
            return 0;
    }

    public Vector oppositeVector() {
        return new Vector(-xDifference, -yDifference);
    }

    public enum Rotation {
        clockwise,
        anticlockwise,
    }

    public static Vector rotate(Rotation r, Vector toRotate) {

        switch (r) {
            case clockwise:
                return new Vector(
                        (int) Math.round(toRotate.getX() * Math.cos(-PI/4) - toRotate.getY() * Math.sin(-PI/4)),
                        (int) Math.round(toRotate.getX() * Math.sin(-PI/4) + toRotate.getY() * Math.cos(-PI/4)));
            case anticlockwise:
                return new Vector(
                        (int) Math.round(toRotate.getX() * Math.cos(PI/4) - toRotate.getY() * Math.sin(PI/4)),
                        (int) Math.round(toRotate.getX() * Math.sin(PI/4) + toRotate.getY() * Math.cos(PI/4)));

        }
        return toRotate;
    }

    public String toString() {
        return "x: " + xDifference + " y: " + yDifference;
    }
}*/
