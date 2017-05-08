package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.util.ui.CommandScanner;
import de.hsa.games.fatsquirrel.util.ui.consoletest.MyFavoriteCommandType;
import de.hsa.games.fatsquirrel.util.ui.consoletest.MyFavoriteCommandsProcessor;
import de.hsa.games.fatsquirrel.console.ConsoleUI;
import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.core.*;

public class Launcher {
    public static void main(String[] args) {

        consoleTest();

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
        Vector newVector = Vector.moveCommandToVector(MoveCommand.NORTHWEST);
        XY newField = manuelSquirrel.getCoordinate().addVector(newVector);

        Entity manuelSquirrel2 = new HandOperatedMasterSquirrel(101, newField);
        board.getSet().add(manuelSquirrel2);

        flatBoard = board.flatten();
        ui.render(flatBoard);


    }

    private static void commandTest() {

        MyFavoriteCommandsProcessor myFavoriteCommandsProcessor = new MyFavoriteCommandsProcessor();
        myFavoriteCommandsProcessor.process();
    }

    private static void consoleTest(){
        Game game = new GameImpl();
        game.startGame();
    }
}
