import java.util.Scanner;

/**
 * Created by tillm on 29.03.2017.
 */
public class HandOperatedMasterSquirrel extends MasterSquirrel {
    public HandOperatedMasterSquirrel(int id, XY coordinate){
        super(id, coordinate);
    }

    public void nextStep(){
        move();
    }

    public void move(){
        Scanner scanner = new Scanner(System.in);
        try{
            char c = (char) System.in.read();
            c = Character.toUpperCase(c);
            switch(c){
                case 'W': setCoordinate(new XY(getCoordinate().getX(), getCoordinate().getY() + 1));
                case 'A': setCoordinate(new XY(getCoordinate().getX() - 1, getCoordinate().getY()));
                case 'S': setCoordinate(new XY(getCoordinate().getX(), getCoordinate().getY() - 1));
                case 'D': setCoordinate(new XY(getCoordinate().getX() + 1, getCoordinate().getY()));
            }
        } catch(Exception e){
            System.out.println("Bitte nur W A S D benutzen!");
        }
    }
}
