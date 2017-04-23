package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.core.BoardView;

import java.util.Scanner;

/**
 * Created by tillm on 22.04.2017.
 */
public class ConsoleUI implements UI {
    //TODO: Implementierungen


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

    }
}
