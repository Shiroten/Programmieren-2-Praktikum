package de.hsa.games.fatsquirrel.botapi.bots.GoodBeastChaser;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.XYsupport;
import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.OutOfViewException;
import de.hsa.games.fatsquirrel.botapi.SpawnException;
import de.hsa.games.fatsquirrel.core.entity.EntityType;


public class GoodBeastChaserMaster implements BotController {
    @Override
    public void nextStep(ControllerContext view) {
        try {
            XY nearestEntityOfGOODPLANT = nearestSearchedEntity(view, EntityType.GOODPLANT);
            XY nearestEntityOfGOODBEAST = nearestSearchedEntity(view, EntityType.GOODBEAST);
            XY nearestEntityOf = nearestEntityOfGOODBEAST.distanceFrom(view.locate()) <
                    nearestEntityOfGOODPLANT.distanceFrom(view.locate())
                    ? nearestEntityOfGOODBEAST : nearestEntityOfGOODPLANT;

            XY toMove = XYsupport.oppositeVector(XYsupport.normalizedVector(view.locate().minus(nearestEntityOf)));
            toMove = goodMove(view, toMove);

            if (view.getEnergy() > 2000) {
                if (view.getEntityAt(view.locate().plus(toMove)) == EntityType.NONE) {
                    view.spawnMiniBot(toMove, 1000);
                    return;
                }
            } else {
                view.move(toMove);
                return;
            }

        } catch (SpawnException e) {
            e.printStackTrace();
        } catch (OutOfViewException e) {
            e.printStackTrace();
        }
    }

    private XY goodMove(ControllerContext view, XY direction) {

        XYsupport.Rotation rotation = XYsupport.Rotation.clockwise;
        int nor = 1;
        boolean stuck = true;

        if (freeField(view, view.locate().plus(direction))) {
            return direction;
        }

        while (stuck) {
            if (freeField(view, view.locate().plus(XYsupport.rotate(rotation, direction, nor)))) {
                return XYsupport.rotate(rotation, direction, nor);
            } else {
                if (rotation == XYsupport.Rotation.clockwise) {
                    rotation = XYsupport.Rotation.anticlockwise;
                } else {
                    rotation = XYsupport.Rotation.clockwise;
                    nor++;
                    System.out.println(nor);
                }
                if (nor > 3)
                    return direction;
            }
        }
        return null;
    }

    private boolean freeField(ControllerContext view, XY location) {

        try {
            EntityType et = view.getEntityAt(location);
            switch (et) {
                case WALL:
                case BADBEAST:
                case BADPLANT:
                case MASTERSQUIRREL:
                case MINISQUIRREL:
                    return false;
                case NONE:
                case GOODBEAST:
                case GOODPLANT:

                    return true;

            }

        } catch (OutOfViewException e) {
            e.printStackTrace();
        }
        return false;
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

    private int normalizeNumber(int i) {
        if (i >= 1)
            return 1;
        else if (i <= -1)
            return -1;
        else
            return 0;
    }

}

