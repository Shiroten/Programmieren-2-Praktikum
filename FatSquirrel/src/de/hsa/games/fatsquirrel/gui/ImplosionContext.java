package de.hsa.games.fatsquirrel.gui;

import de.hsa.games.fatsquirrel.XY;

public class ImplosionContext {


    private int energyLoss;
    private int radius;
    private XY position;
    private int tickCounter = 0;

    public ImplosionContext(int energyLoss, int radius, XY position) {
        this.energyLoss = energyLoss;
        this.radius = radius;
        this.position = position;
    }

    int getEnergyLoss() {
        return energyLoss;
    }


    int getRadius() {
        return radius;
    }

    XY getPosition() {
        return position;
    }

    public int getTickCounter() {
        return tickCounter;
    }

    public void addTick() {
        tickCounter++;
    }


}
