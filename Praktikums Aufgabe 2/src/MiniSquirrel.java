/**
 * Created by tillm on 29.03.2017.
 */
public class MiniSquirrel extends MasterSquirrel {
    private MasterSquirrel daddy;

    public MiniSquirrel(int id, XY coordinate, int startEnergy, MasterSquirrel daddy) {
        super(id, coordinate, startEnergy);
        this.daddy = daddy;
    }

    public MasterSquirrel getDaddy(){
        return daddy;
    }

    public String toString() {
        return ("MiniSquirrel: " + super.toString() + "Luke, wer ist dein Vater? ParentID:" + daddy.getId());
    }

    public void nextStep(){
    }
}
