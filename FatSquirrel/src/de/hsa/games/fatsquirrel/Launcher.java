package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.botapi.BotGameImpl;
import de.hsa.games.fatsquirrel.core.entity.character.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.entity.Entity;
import de.hsa.games.fatsquirrel.gui.FxGameImpl;
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
    private static final GameType gameType = GameType.WITH_BOT;
    private static final Level logLevel = Level.FINE;
    private static final XY gameSize = new XY(40, 30);
    private static final int NUMBER_OF_GB = 100;
    private static final int NUMBER_OF_BB = 3;
    private static final int NUMBER_OF_GP = 10;
    private static final int NUMBER_OF_BP = 5;
    private static final int NUMBER_OF_WA = 25;
    private static final int VIEW_DISTANCE_OF_GOODBEAST = 6;
    private static final int VIEW_DISTANCE_OF_BADBEAST = 6;

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Launcher.class.getName());
        logger.setLevel(logLevel);
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
        }
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

        State state = new State(gameSize, FRAMERATE,
                NUMBER_OF_GB, NUMBER_OF_BB, NUMBER_OF_GP, NUMBER_OF_BP, NUMBER_OF_WA,
                VIEW_DISTANCE_OF_GOODBEAST, VIEW_DISTANCE_OF_BADBEAST, gameType);

        FxUI fxUI = FxUI.createInstance(state.getBoard().getConfig().getSize());
        final Game game = new FxGameImpl(fxUI, state);
        //final Game game = new BotGameImpl(fxUI, state);

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