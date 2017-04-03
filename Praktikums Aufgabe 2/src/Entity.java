/**
 * Created by tillm on 28.03.2017.
 */
public abstract class Entity {
    private final int id;
    private int energy;
    private XY coordinate;

    public Entity(int startEnergy, int id, XY coordinate){
        this.id = id;
        this.energy = startEnergy;
        this.coordinate = coordinate;
    }

    public Entity(){
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

    public abstract void nextStep();

    public String toString(){
        return ("[ID: " + id + ", Energy: " + energy
                + ", Coordinate: (X: " + coordinate.getX() + ", Y: " + coordinate.getY()+ ")]");
    }
}
