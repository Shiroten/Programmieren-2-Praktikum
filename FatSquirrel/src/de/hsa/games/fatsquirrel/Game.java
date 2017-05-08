package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.State;

public class Game {
    private UI ui;
    private State state;
    protected MoveCommand command;


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
        render();
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
}
