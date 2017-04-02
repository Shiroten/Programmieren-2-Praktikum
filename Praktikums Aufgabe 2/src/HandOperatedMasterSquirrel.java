import java.util.Scanner;

/**
 * Created by tillm on 29.03.2017.
 */
public class HandOperatedMasterSquirrel extends MasterSquirrel {
    public HandOperatedMasterSquirrel(int id, XY coordinate) {
        super(id, coordinate);
    }

    public void nextStep() {
        move();
    }

    public void move() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie eine Richtung für ihr Squirrel in WASD an: ");
        try {
            char c = (char) System.in.read();
            c = Character.toUpperCase(c);
            switch (c) {
                case 'W':
                    setCoordinate(new XY(getCoordinate().getX(), getCoordinate().getY() + 1));
                    break;
                case 'A':
                    setCoordinate(new XY(getCoordinate().getX() - 1, getCoordinate().getY()));
                    break;
                case 'S':
                    setCoordinate(new XY(getCoordinate().getX(), getCoordinate().getY() - 1));
                    break;
                case 'D':
                    setCoordinate(new XY(getCoordinate().getX() + 1, getCoordinate().getY()));
                    break;
            }
        } catch (Exception e) {
            System.out.println("Bitte nur W A S D benutzen!");
        }
    }
}
