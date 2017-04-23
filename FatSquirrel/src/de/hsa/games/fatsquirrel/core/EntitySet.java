package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by Shiroten on 01.04.2017.
 */
public class EntitySet {

    private int numberOfMaxEntities = 500;
    private Entity[] entityList = new Entity[numberOfMaxEntities];
    //private ArrayList<de.hsa.games.fatsquirrel.core.Entity> entityList = new ArrayList<de.hsa.games.fatsquirrel.core.Entity>();


    public int getNumberOfMaxEntities() {
        return numberOfMaxEntities;
    }

    public Entity[] getEntityList() {
        return entityList;
    }

    public Entity getEntity(int index) {return entityList[index]; }

    public void add(Entity toAdd) {
        for (int i = 0; i < numberOfMaxEntities; i++) {
            if (entityList[i] == null) {
                entityList[i] = toAdd;
                return;
            }
        }
    }

    public void delete(Entity toDelete) {

        for (int i = 0; i < numberOfMaxEntities; i++) {
            if (entityList[i] == toDelete) {
                entityList[i] = null;
                return;
            }
        }
    }

    public String toString() {

        String returnString = "";
        for (int i = 0; i < numberOfMaxEntities; i++) {
            if (entityList[i] != null) {
                returnString += entityList[i].toString() + '\n';
            }
        }
        return returnString;
    }

    public void nextStep() {
        for (int i = 0; i < numberOfMaxEntities; i++) {
            if (entityList[i] != null) {
                entityList[i].nextStep();
            }
        }
    }

    public XY collision(Entity entityToCheck, XY destination) {

        for (int i = 0; i < numberOfMaxEntities; i++) {
            Entity toCheck = entityList[i];
            if (toCheck != null) {
                if (toCheck.getCoordinate().getX() == destination.getX()
                        && toCheck.getCoordinate().getY() == destination.getY()) {
                    if (toCheck instanceof GoodPlant) {
                        entityToCheck.updateEnergy(toCheck.getEnergy());
                        this.delete(toCheck);
                        return destination;
                    }
                }
            }
        }
        return destination;
    }
}
