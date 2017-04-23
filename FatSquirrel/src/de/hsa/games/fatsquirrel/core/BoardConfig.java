package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 07.04.2017.
 */
public class BoardConfig {
    private final XY size;

    private final int numberOfGoodBeast;
    private final int numberOfBadBeast;
    private final int numberOfGoodPlant;
    private final int numberOfBadPlant;
    private final int numberOfWalls;


    public BoardConfig(XY size, int numberOfGoodBeast, int numberOfBadBeast, int numberOfGoodPlant, int numberOfBadPlant, int numberOfWalls) {
        this.size = size;
        this.numberOfGoodBeast = numberOfGoodBeast;
        this.numberOfBadBeast = numberOfBadBeast;
        this.numberOfGoodPlant = numberOfGoodPlant;
        this.numberOfBadPlant = numberOfBadPlant;
        this.numberOfWalls = numberOfWalls;
    }

    public BoardConfig(XY size) {
        this(size, 7, 7, 7, 7, 7);
    }


    public XY getSize() {
        return size;
    }

    public int getNumberOfGoodBeast() {
        return numberOfGoodBeast;
    }

    public int getNumberOfBadBeast() {
        return numberOfBadBeast;
    }

    public int getNumberOfGoodPlant() {
        return numberOfGoodPlant;
    }

    public int getNumberOfBadPlant() {
        return numberOfBadPlant;
    }

    public int getNumberOfWalls() {
        return numberOfWalls;
    }

}
