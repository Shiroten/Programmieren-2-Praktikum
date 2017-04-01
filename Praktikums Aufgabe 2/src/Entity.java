/**
 * Created by tillm on 28.03.2017.
 */
public abstract class Entity {
    private final int id;
    private final int startEnergy;
    private int energy;
    private XY coordinate;

    public Entity(int startEnergy, int id, XY coordinate){
        this.id = id;
        this.startEnergy = startEnergy;
        this.energy = startEnergy;
        this.coordinate = coordinate;
    }

    public Entity(){
        this.id = 0;
        this.startEnergy = 0;
    }

    public int getId() {
        return id;
    }

    public int getStartEnergy() {
        return startEnergy;
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

    public void nextStep(){
        this.coordinate = coordinate.randomMove();
    }

    public String toString(){
        return ("[ID: " + id + ", StartEnergy: "
                + startEnergy + ", Energy: " + energy
                + ", Coordinate: (X: " + coordinate.getX() + ", Y: " + coordinate.getY()+ ")]");
    }
}
