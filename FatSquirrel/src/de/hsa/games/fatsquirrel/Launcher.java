package de.hsa.games.fatsquirrel; /**
 * Created by Shiroten on 01.04.2017.
 */

import de.hsa.games.fatsquirrel.console.ConsoleUI;
import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.core.*;

public class Launcher {
    public static void main(String[] args) {

        Game game = new GameImpl();
        game.run();

    }

    private static void oldTest() {

        EntitySet entitySet = new EntitySet(new XY(100, 100));

        entitySet.add(new Wall(0, new XY(50, 50)));
        entitySet.add(new GoodPlant(1, new XY(10, 20)));
        entitySet.add(new BadPlant(2, new XY(10, 10)));

        entitySet.add(new GoodBeast(3, new XY(20, 20)));
        entitySet.add(new BadBeast(4, new XY(0, 0)));

        MasterSquirrel ms = new MasterSquirrel(100, new XY(100, 100));
        entitySet.add(ms);
        Entity manuelSquirrel = new HandOperatedMasterSquirrel(101, new XY(100, 100));
        entitySet.add(manuelSquirrel);
        entitySet.add(new GoodPlant(1000, new XY(100, 101)));

        MiniSquirrel testSquirrel1 = new MiniSquirrel(101, new XY(100, 101), 100, ms);
        MiniSquirrel testSquirrel2 = new MiniSquirrel(102, new XY(100, 102), 100, ms);
        MiniSquirrel testSquirrel3 = new MiniSquirrel(103, new XY(100, 103), 100, ms);

        entitySet.add(testSquirrel1);
        entitySet.add(testSquirrel2);
        entitySet.add(testSquirrel3);

        System.out.println(entitySet.toString());

        entitySet.delete(testSquirrel2);
        System.out.println(entitySet.toString());

        entitySet.add(testSquirrel2);
        System.out.println(entitySet.toString());

        //entitySet.delete(manuelSquirrel);

        for (int i = 0; i < 9; i++) {
            //entitySet.nextStep();
            System.out.println();
            System.out.printf("%d. nextStep: %n%n", (i + 1));
            System.out.println(entitySet.toString());
        }
    }

    private static void uiTest() {
        BoardConfig config = new BoardConfig(new XY(21, 21), 50, 7, 7, 7, 7);
        //config = new BoardConfig(new XY(10,10));
        Board board = new Board(config);


        board.initBoard();

        FlattenedBoard flatBoard = board.flatten();

        ConsoleUI ui = new ConsoleUI();
        ui.render(flatBoard);

        Entity manuelSquirrel = new HandOperatedMasterSquirrel(101, new XY(3, 3));
        board.getSet().add(manuelSquirrel);

        //MoveCommand Vector Test
        Vector  newVector = Vector.MoveCommandToVector(MoveCommand.NORTHWEST);
        XY newField = manuelSquirrel.getCoordinate().addVector(newVector);

        Entity manuelSquirrel2 = new HandOperatedMasterSquirrel(101, newField);
        board.getSet().add(manuelSquirrel2);

        flatBoard = board.flatten();
        ui.render(flatBoard);



    }
}
