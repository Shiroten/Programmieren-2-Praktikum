/**
 * Created by Shiroten on 01.04.2017.
 */
public class EntitySet {

    private int nummberOfEntitys = 100;
    Entity[] entitiyList = new Entity[nummberOfEntitys];

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
                returnString += entitiyList[i].toString();
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

}
