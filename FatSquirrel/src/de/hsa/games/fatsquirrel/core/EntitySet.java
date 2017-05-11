package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.Pac.PacSquirrel;

/**
 * Created by Shiroten on 01.04.2017.
 */
public class EntitySet {

    private final int numberOfMaxEntities;
    private Entity[] entityList;
    //private ArrayList<de.hsa.games.fatsquirrel.core.Entity> entityList = new ArrayList<de.hsa.games.fatsquirrel.core.Entity>();

    public EntitySet (XY size){
        numberOfMaxEntities = size.getX()*size.getY();
        this.entityList = new Entity[numberOfMaxEntities];

    }

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

    public void nextStep(EntityContext flat) {
        for (int i = 0; i < numberOfMaxEntities; i++) {
            if (entityList[i] != null) {
                if(entityList[i] instanceof HandOperatedMasterSquirrel)
                    ((HandOperatedMasterSquirrel) entityList[i]).nextStep(flat);
                else if(entityList[i] instanceof Character)
                    ((Character) entityList[i]).nextStep(flat);
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
