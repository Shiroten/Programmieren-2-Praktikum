import java.util.Scanner;

/**
 * Created by tillm on 29.03.2017.
 */
public class HandOperatedMasterSquirrel extends MasterSquirrel {
    public HandOperatedMasterSquirrel(int id, XY coordinate) {
        super(id, coordinate);
    }

    public void nextStep() {

        XY destination = move();
        setCoordinate(destination);

    }

    public XY move() {

        XY destination = new XY(0, 0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie eine Richtung für ihr Squirrel in WASD an: ");

        try {
            //char c = (char) System.in.read();
            char c = scanner.next().charAt(0);

            c = Character.toUpperCase(c);
            //TODO: Ändern in Vektoren / ändern in XY
            switch (c) {
                case 'W':
                    destination = (new XY(getCoordinate().getX(), getCoordinate().getY() - 1));
                    break;
                case 'A':
                    destination = (new XY(getCoordinate().getX() - 1, getCoordinate().getY()));
                    break;
                case 'S':
                    destination = (new XY(getCoordinate().getX(), getCoordinate().getY() + 1));
                    break;
                case 'D':
                    destination = (new XY(getCoordinate().getX() + 1, getCoordinate().getY()));
                    break;
                default: destination = (new XY(getCoordinate().getX(), getCoordinate().getY()));
            }

        } catch (Exception e) {
            System.out.println("Bitte nur W A S D benutzen!");
        }
        return destination;
    }
}
