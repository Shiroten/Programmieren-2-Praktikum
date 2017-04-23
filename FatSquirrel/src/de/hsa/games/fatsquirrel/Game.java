package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.State;

/**
 * Created by tillm on 07.04.2017.
 */
public class Game {
    private UI ui;
    private State state;

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Game(State state){
        this.state = state;
    }

    public Game(){};

    public void run(){
        while(true){
            processInput();
            render();
            update();
        }
    }

    protected void processInput(){

    }

    protected void render(){

    }

    protected void update(){

    }
}
