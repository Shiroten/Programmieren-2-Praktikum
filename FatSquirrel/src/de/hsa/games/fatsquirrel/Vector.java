package de.hsa.games.fatsquirrel;

import java.util.concurrent.ThreadLocalRandom;

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

    public Vector randomDirection() {
        return new Vector(
                ThreadLocalRandom.current().nextInt(0, 3) - 1,
                ThreadLocalRandom.current().nextInt(1, 4) - 2);
    }

    //Gibt die Länge in Schritten aus
    public int getLength() {
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
        if (newX < 1)
            newX = 0;
        else
            newX = 1;
        if (newY < 1)
            newY = 0;
        else
            newY = 1;

        return new Vector(newX, newY);
    }

    public Vector oppositeVector() {
        return new Vector(-xDifference, -yDifference);
    }

    public String toString() {
        return "x: " + xDifference + " y: " + yDifference;
    }
}
