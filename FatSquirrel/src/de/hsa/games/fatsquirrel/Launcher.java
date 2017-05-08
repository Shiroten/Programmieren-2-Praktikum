package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.gui.FxGameImpl;
import de.hsa.games.fatsquirrel.gui.FxUI;
import de.hsa.games.fatsquirrel.util.ui.consoletest.MyFavoriteCommandsProcessor;
import de.hsa.games.fatsquirrel.console.ConsoleUI;
import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.core.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends Application {
    private static final int FRAMERATE = 10;

    public static void main(String[] args) {

        Application.launch(args);
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

    private static void consoleTest() {
        Game game = new GameImpl();
        startGame(game);
    }

    private static void startGame(Game game) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                game.run();
            }
        }, 100, 1000 / FRAMERATE);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                game.processInput();
            }
        }, 1000, 100);

    }


    @Override
    public void start(Stage primaryStage) {

        BoardConfig config = new BoardConfig(new XY(7, 7), 1, 1, 1, 1, 1);
        Board board = new Board(config);
        State state = new State(board);

        FxUI fxUI = FxUI.createInstance(state.getBoard().getConfig().getSize());
        final Game game = new FxGameImpl(fxUI, state);

        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Diligent Squirrel");

        fxUI.getWindow().setOnCloseRequest(evt -> System.exit(-1));

        primaryStage.show();
        game.render();
        startGame(game);
    }
}