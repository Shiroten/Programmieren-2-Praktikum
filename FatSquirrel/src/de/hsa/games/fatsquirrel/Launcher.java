package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.botapi.BotGameImpl;
import de.hsa.games.fatsquirrel.core.entity.character.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.entity.Entity;
import de.hsa.games.fatsquirrel.gui.FxUI;
import de.hsa.games.fatsquirrel.util.ui.consoletest.MyFavoriteCommandsProcessor;
import de.hsa.games.fatsquirrel.console.ConsoleUI;
import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.core.*;
import javafx.application.Application;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.*;

public class Launcher extends Application {
    private static final int FRAMERATE = 60;

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Launcher.class.getName());
        logger.setLevel(Level.FINE);
        try {
            Handler handler = new FileHandler("log.txt");
            SimpleFormatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
            handler.setLevel(Level.ALL);
            logger.addHandler(handler);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int switchMode = 0;

        switch (switchMode) {
            case 0:
                Application.launch(args);
                break;
            case 1:
                //For SingleThreaded Console Game
                consoleTest();
                break;
            case 2:
                commandTest();
                break;
            case 3:
                consoleUiTest();
        }
    }

    private static void consoleUiTest() {
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

        XY newField = manuelSquirrel.getCoordinate().plus(XY.LEFT_UP);

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
        game.startSingleThreadGame();
    }

    private static void startGame(Game game) {
        try {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Logger logger = Logger.getLogger(Launcher.class.getName());
                    logger.log(Level.FINER, "start game.run()");
                    game.run();
                }
            }, 1000, game.getState().getBoard().getConfig().getTICKLENGTH());
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Logger logger = Logger.getLogger(Launcher.class.getName());
                    logger.log(Level.FINER, "start game.processInput()");
                    game.processInput();
                }
            }, 500, game.getState().getBoard().getConfig().getTICKLENGTH());
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();

        }

    }

    @Override
    public void start(Stage primaryStage) {

        GameType gameType = GameType.WITH_BOT;

        State state = new State(new XY(40, 30), FRAMERATE, 150, 5, 5, 20, 20, 7, 5, gameType);

        FxUI fxUI = FxUI.createInstance(state.getBoard().getConfig().getSize());
        //final Game game = new FxGameImpl(fxUI, state);
        final Game game = new BotGameImpl(fxUI, state);

        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Diligent Squirrel");

        fxUI.getWindow().setOnCloseRequest(evt -> System.exit(-1));

        game.render();
        primaryStage.show();

        Logger logger = Logger.getLogger(Launcher.class.getName());
        logger.log(Level.INFO, "Starting Game");
        startGame(game);
    }
}