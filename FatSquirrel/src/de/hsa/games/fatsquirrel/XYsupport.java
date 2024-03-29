package de.hsa.games.fatsquirrel;

import static java.lang.Math.PI;

public class XYsupport {
    public enum Rotation {
        clockwise,
        anticlockwise,
    }

    public static XY[] directions() {
        return new XY[]{XY.ZERO_ZERO, XY.RIGHT, XY.LEFT, XY.DOWN, XY.UP, XY.LEFT_UP, XY.LEFT_DOWN, XY.RIGHT_DOWN, XY.RIGHT_UP};
    }

    public static XY randomDirection() {
        return new XY(randomWithRange(-1, 1), randomWithRange(-1, 1));
    }

    public static XY oppositeVector(XY xy) {
        return new XY(-xy.getX(), -xy.getY());
    }

    public static XY rotate(Rotation r, XY toRotate, int numberOfRotation) {

        double rotationClockwise = (PI / 4 * numberOfRotation);
        double rotationAntiClockwise = (-PI / 4 * numberOfRotation);

        switch (r) {
            case clockwise:
                return new XY(
                        (int) Math.round(toRotate.getX() * Math.cos(rotationClockwise)
                                - toRotate.getY() * Math.sin(rotationClockwise)),
                        (int) Math.round(toRotate.getX() * Math.sin(rotationClockwise)
                                + toRotate.getY() * Math.cos(rotationClockwise)));
            case anticlockwise:
                return new XY(
                        (int) Math.round(toRotate.getX() * Math.cos(rotationAntiClockwise)
                                - toRotate.getY() * Math.sin(rotationAntiClockwise)),
                        (int) Math.round(toRotate.getX() * Math.sin(rotationAntiClockwise)
                                + toRotate.getY() * Math.cos(rotationAntiClockwise)));

        }
        return toRotate;
    }

    public static boolean isInRange(XY middle, XY lowerLeftEnd, XY upperRightEnd) {
        return middle.getX() <= upperRightEnd.getX() && middle.getX() >= lowerLeftEnd.getX()
                && middle.getY() <= lowerLeftEnd.getY() && middle.getY() >= upperRightEnd.getY();
    }

    private static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public static XY normalizedVector(XY xy) {

        int newX, newY, oldX = xy.getX(), oldY = xy.getY();
        if (oldX == 0) {
            if (oldY == 0)
                return XY.ZERO_ZERO;
            else if (oldY < 0) {
                return XY.UP;
            } else {
                return XY.DOWN;
            }
        } else if (oldY == 0) {
            if (oldX < 0) {
                return XY.LEFT;
            } else {
                return XY.RIGHT;
            }
        } else {
            if (oldX < 0)
                newX = Math.round(-1 * (Math.abs((float) oldX / (float) oldY)));
            else{
                newX = Math.round(Math.abs((float) oldX / (float) oldY));
            }

            if (oldY < 0)
                newY = Math.round(-1 * (Math.abs((float) oldY / (float) oldX)));
            else {
                newY = Math.round(Math.abs((float) oldY / (float) oldX));
            }
        }
        newX = normalizeNumber(newX);
        newY = normalizeNumber(newY);
        return new XY(newX, newY);
    }

    private static int normalizeNumber(int i) {
        if (i >= 1)
            return 1;
        else if (i <= -1)
            return -1;
        else
            return 0;
    }
}
