/**
 * Created by tillm on 29.03.2017.
 */
public class MasterSquirrel extends Entity {

    public MasterSquirrel(int id, XY coordinate){
        super(1000, id, coordinate);
    }

    public MasterSquirrel(int id, XY coordinate, int startEnergy){
        super(startEnergy, id, coordinate);
    }

    public MasterSquirrel(){

    }
}
