package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.State;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tillm on 07.04.2017.
 */
public class Game {
    private UI ui;
    private State state;
    protected MoveCommand command;
    private int framerate = 1;


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

    ;

    public void startGame() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runGame();
            }
        }, 100, 1000 / framerate);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                processInput();
            }
        },1000,100);

    }

    public void runGame() {

        render();
        update();

    }

    public void startSingleThreadGame(){

        while(true){
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
