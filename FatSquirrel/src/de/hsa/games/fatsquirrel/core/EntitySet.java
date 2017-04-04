package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by Shiroten on 01.04.2017.
 */
public class EntitySet {

    private int numberOfEntities = 100;
    private Entity[] entityList = new Entity[numberOfEntities];
    //private ArrayList<de.hsa.games.fatsquirrel.core.Entity> entityList = new ArrayList<de.hsa.games.fatsquirrel.core.Entity>();


    public int getNumberOfEntities() {
        return numberOfEntities;
    }

    public Entity[] getEntityList() {
        return entityList;
    }

    public void add(Entity toAdd) {
        for (int i = 0; i < numberOfEntities; i++) {
            if (entityList[i] == null) {
                entityList[i] = toAdd;
                return;
            }
        }
    }

    public void delete(Entity toDelete) {

        for (int i = 0; i < numberOfEntities; i++) {
            if (entityList[i] == toDelete) {
                entityList[i] = null;
                return;
            }
        }

    }

    public String toString() {

        String returnString = "";
        for (int i = 0; i < numberOfEntities; i++) {
            if (entityList[i] != null) {
                returnString += entityList[i].toString() + '\n';
            }
        }
        return returnString;
    }

    public void nextStep() {
        for (int i = 0; i < numberOfEntities; i++) {
            if (entityList[i] != null) {
                entityList[i].nextStep();
            }
        }


    }

    public XY collision(Entity entityToCheck, XY destination) {

        for (int i = 0; i < numberOfEntities; i++) {
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

    //TODO: check equals für entities
    //Keine Ahnung was damit nochmal gemeint ist. S
}
