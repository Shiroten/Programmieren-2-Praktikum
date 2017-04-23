package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityType;

import java.util.Scanner;

/**
 * Created by tillm on 22.04.2017.
 */
public class ConsoleUI implements UI {

    @Override
    public MoveCommand getDirection() {

        MoveCommand destination = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie eine Richtung für ihr Squirrel in WASD an: ");

        try {
            //char c = (char) System.in.read();
            char c = scanner.next().charAt(0);

            c = Character.toUpperCase(c);
            switch (c) {
                case 'W':
                    destination = MoveCommand.NORTH;
                    break;
                case 'A':
                    destination = MoveCommand.WEST;
                    break;
                case 'S':
                    destination = MoveCommand.SOUTH;
                    break;
                case 'D':
                    destination = MoveCommand.EAST;
                    break;
                default:
                    destination = MoveCommand.NOWHERE;
            }

        } catch (Exception e) {
            System.out.println("Bitte nur W A S D benutzen!");
        }
        return destination;
    }

    @Override
    public void render(BoardView view) {

        System.out.printf("----:Aktuelles Board:----%n%n");
        //i =: Zeilen
        for (int i = 0; i < view.getSize().getY(); i++) {

            printBorder(view.getSize().getX());
            //j =: Spalten
            System.out.printf("|");
            for (int j = 0; j < view.getSize().getX(); j++) {
                //Schreiben der Einzelnen Felder
                System.out.printf("%s", printField(view, j, i));
            }
            //abschließen der Zeile
            System.out.println();
        }
        //Abschluss Linie
        printBorder(view.getSize().getX());

        //Einrücken
        System.out.printf("%n%n");


    }

    private void printBorder(int ammount) {
        System.out.printf("|");
        for (int j = 0; j < ammount; j++) {
            System.out.printf("----|");
        }
        System.out.printf("%n");
    }

    private String printField(BoardView view, int x, int y) {

        EntityType onField;
        if (view.getEntityType(new XY(x, y)) !=null){
            onField = view.getEntityType(new XY(x, y));
        }else{
            onField = EntityType.EMPTY;
        }

        String stringToPrint;

        switch (onField) {
            case GOODPLANT:
                stringToPrint = " GP |";
                break;
            case GOODBEAST:
                stringToPrint = " GB |";
                break;
            case BADPLANT:
                stringToPrint = " BP |";
                break;
            case BADBEAST:
                stringToPrint = " BB |";
                break;
            case WALL:
                stringToPrint = " WA |";
                break;
            case MINISQUIRREL:
                stringToPrint = " mS |";
                break;
            case MASTERSQUIRREL:
                stringToPrint = " MS |";
                break;
            case HANDOPERATEDMASTERSQUIRREL:
                stringToPrint = " HS |";
                break;
            default:
                stringToPrint = " .. |";

        }
        return stringToPrint;
    }
}
