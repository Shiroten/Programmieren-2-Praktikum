package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

import static java.lang.Math.PI;


public class MasterBotControllerImplShiroten implements BotController {
    @Override
    public void nextStep(ControllerContext view) {
        try {
            XY nearestEntityOf = nearestSearchedEntity(view, EntityType.GOODBEAST);
            XY toMove = oppositeVector(this.normalizedVector(view.locate().minus(nearestEntityOf)));

            if ((view.getEntityAt(view.locate().plus(toMove)) == EntityType.WALL || view.getEntityAt(view.locate().plus(toMove)) == EntityType.MINISQUIRREL)) {
                XY lookAtNewToMove = rotate(Rotation.clockwise, toMove);
                if (!(view.getEntityAt(view.locate().plus(lookAtNewToMove)) == EntityType.WALL || view.getEntityAt(view.locate().plus(lookAtNewToMove)) == EntityType.MINISQUIRREL)) {
                    XY newToMove = rotate(Rotation.clockwise, toMove);
                    view.move(newToMove);
                    return;
                } else {
                    XY newToMove = rotate(Rotation.anticlockwise, toMove);
                    view.move(newToMove);
                    return;
                }

            } else {
                if (view.getEnergy() > 2000) {
                    if (view.getEntityAt(view.locate().plus(toMove)) == EntityType.NONE) {
                        view.spawnMiniBot(toMove, 1000);
                        return;
                    }
                } else {
                    view.move(toMove);
                    return;
                }
            }
        } catch (SpawnException e) {
            e.printStackTrace();
        } catch (OutOfViewException e) {
            e.printStackTrace();
        }
    }

    private XY nearestSearchedEntity(ControllerContext view, EntityType et) {

        XY pos = view.locate();
        int minX = view.getViewLowerLeft().getX(), maxX = view.getViewUpperRight().getX();
        int minY = view.getViewUpperRight().getY(), maxY = view.getViewLowerLeft().getY();

        try {
            XY nearestEntity = new XY(100, 100);
            for (int i = minX; i < maxX; i++) {
                for (int j = minY; j < maxY; j++) {
                    if (view.getEntityAt(new XY(i, j)) == et) {
                        double distanceToNew = pos.distanceFrom(new XY(i, j));
                        if (distanceToNew < pos.distanceFrom(nearestEntity)) {
                            nearestEntity = new XY(i, j);
                        }
                    }

                }
            }
            return nearestEntity;
        } catch (OutOfViewException e) {
            e.printStackTrace();

        }
        return null;
    }

    private XY normalizedVector(XY xy) {
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
                newX = Math.round(-1 * (Math.abs(oldX) / Math.abs(oldY)));
            else
                newX = Math.round(Math.abs(oldX) / Math.abs(oldY));

            if (oldY < 0)
                newY = Math.round(-1 * (Math.abs(oldY) / Math.abs(oldX)));
            else
                newY = Math.round(Math.abs(oldY) / Math.abs(oldX));
        }
        newX = normalizeNumber(newX);
        newY = normalizeNumber(newY);
        return new XY(newX, newY);
    }

    private int normalizeNumber(int i) {
        if (i >= 1)
            return 1;
        else if (i <= -1)
            return -1;
        else
            return 0;
    }

    private static XY oppositeVector(XY xy) {
        return new XY(-xy.getX(), -xy.getY());
    }

    private static XY rotate(Rotation r, XY toRotate) {

        switch (r) {
            case clockwise:
                return new XY(
                        (int) Math.round(toRotate.getX() * Math.cos(-PI / 4) - toRotate.getY() * Math.sin(-PI / 4)),
                        (int) Math.round(toRotate.getX() * Math.sin(-PI / 4) + toRotate.getY() * Math.cos(-PI / 4)));
            case anticlockwise:
                return new XY(
                        (int) Math.round(toRotate.getX() * Math.cos(PI / 4) - toRotate.getY() * Math.sin(PI / 4)),
                        (int) Math.round(toRotate.getX() * Math.sin(PI / 4) + toRotate.getY() * Math.cos(PI / 4)));

        }
        return toRotate;
    }

    private enum Rotation {
        clockwise,
        anticlockwise,
    }

}

