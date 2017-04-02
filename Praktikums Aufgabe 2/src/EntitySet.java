/**
 * Created by Shiroten on 01.04.2017.
 */
public class EntitySet {

    private int nummberOfEntitys = 100;
    private Entity[] entitiyList = new Entity[nummberOfEntitys];

    public int getNumberofEntitys() {
        return nummberOfEntitys;
    }

    public Entity[] getEntitiList() {
        return entitiyList;
    }

    public void add(Entity toAdd) {

        for (int i = 0; i < nummberOfEntitys; i++) {
            if (entitiyList[i] == null) {
                entitiyList[i] = toAdd;
                return;
            }
        }
    }

    public void delete(Entity toDelete) {

        for (int i = 0; i < nummberOfEntitys; i++) {
            if (entitiyList[i] == toDelete) {
                entitiyList[i] = null;
                return;
            }
        }

    }

    public String toString() {

        String returnString = "";
        for (int i = 0; i < nummberOfEntitys; i++) {
            if (entitiyList[i] != null) {
                returnString += entitiyList[i].toString() + '\n';
            }
        }
        return returnString;
    }

    public void nextStep() {
        for (int i = 0; i < nummberOfEntitys; i++) {
            if (entitiyList[i] != null) {
                entitiyList[i].nextStep();
            }
        }
    }

    public XY collision(Entity entityToCheck, XY destination) {

        for (int i = 0; i < nummberOfEntitys; i++) {
            Entity toCheck = entitiyList[i];
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
