package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.State;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private UI ui;
    private State state;
    protected ActionCommand command;


    public UI getUi() {
        return ui;
    }

    protected void setUi(UI ui) {
        this.ui = ui;
    }

    protected State getState() {
        return state;
    }

    protected void setState(State state) {
        this.state = state;
    }

    public Game(State state, UI ui) {
        this.state = state;
        this.ui = ui;
    }

    public Game() {
    }

    public void run() {
        Logger logger = Logger.getLogger(Launcher.class.getName());
        logger.log(Level.FINER, "start render()");
        render();
        logger.log(Level.FINER, "start update()");
        update();

    }

    public void startSingleThreadGame() {
        while (true) {
            render();
            processInput();
            update();
        }
    }

    protected void processInput() {

    }

    protected void render() {

    }

    protected void update() {

    }

    public static enum GameType {
        SINGLE_PLAYER, WITH_BOT, BOT_ONLY, PACMAN
    }
}
