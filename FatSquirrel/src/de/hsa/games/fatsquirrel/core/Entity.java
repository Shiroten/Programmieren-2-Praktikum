package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 28.03.2017.
 */
public abstract class Entity {
    private final int id;
    protected int energy;
    private XY coordinate;

    public Entity(int id, XY coordinate) {
        this.id = id;
        this.coordinate = coordinate;
    }

    public Entity() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public int getEnergy() {
        return energy;
    }

    public void updateEnergy(int energyDifference) {
        this.energy += energyDifference;
    }

    public XY getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(XY coordinate) {
        this.coordinate = coordinate;
    }

    public abstract EntityType getEntityType();

    public abstract void nextStep();

    public String toString() {
        return ("[ID: " + id + ", Energy: " + energy
                + ", Coordinate: (X: " + coordinate.getX() + ", Y: " + coordinate.getY() + ")]");
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Entity) {
            Entity e = (Entity) o;
            boolean returnValue = false;
            if (this.id == e.getId()) {
                returnValue = true;
            } else {
                returnValue = false;
            }
            return returnValue;
        }
        return false;

    }
}
