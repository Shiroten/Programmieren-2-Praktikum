package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

public class BoardConfig {

    private final XY size;

    private final int NUMBER_OF_GB;
    private final int NUMBER_OF_BB;
    private final int NUMBER_OF_GP;
    private final int NUMBER_OF_BP;
    private final int NUMBER_OF_WA;

    private final int POINTS_FOR_MINI_SQUIRREL = 150;

    private final int TICKLENGTH;
    private final float SQUIRREL_STUN_TIME_LENGTH = 0.2f;
    private final int SQUIRREL_STUN_TIME_IN_TICKS;
    private final float BEAST_MOVE_TIME_LENGTH = 0.15f;
    private final int BEAST_MOVE_TIME_IN_TICKS;
    private final float MINI_SQUIRREL_MOVE_TIME_LENGTH = 0.05f;
    private final int MINI_SQUIRREL_MOVE_TIME_IN_TICKS;

    private final int GOODBEAST_VIEW_DISTANCE;
    private final int BADBEAST_VIEW_DISTANCE;

    private final GameType gameType;


    public BoardConfig(XY size, int TICKLENGTH, int NUMBER_OF_GB, int NUMBER_OF_BB, int NUMBER_OF_GP, int NUMBER_OF_BP, int NUMBER_OF_WA, int GOODBEAST_VIEW_DISTANCE, int BADBEAST_VIEW_DISTANCE, GameType gameType) {
        this.size = size;
        this.TICKLENGTH = TICKLENGTH;
        this.NUMBER_OF_GB = NUMBER_OF_GB;
        this.NUMBER_OF_BB = NUMBER_OF_BB;
        this.NUMBER_OF_GP = NUMBER_OF_GP;
        this.NUMBER_OF_BP = NUMBER_OF_BP;
        this.NUMBER_OF_WA = NUMBER_OF_WA;
        this.SQUIRREL_STUN_TIME_IN_TICKS = (int) (TICKLENGTH * SQUIRREL_STUN_TIME_LENGTH);
        this.BEAST_MOVE_TIME_IN_TICKS = (int) (TICKLENGTH * BEAST_MOVE_TIME_LENGTH);
        this.MINI_SQUIRREL_MOVE_TIME_IN_TICKS = (int) (TICKLENGTH * MINI_SQUIRREL_MOVE_TIME_LENGTH);
        this.GOODBEAST_VIEW_DISTANCE = GOODBEAST_VIEW_DISTANCE;
        this.BADBEAST_VIEW_DISTANCE = BADBEAST_VIEW_DISTANCE;
        this.gameType = gameType;
    }

    public BoardConfig(XY size) {
        this(size, 1, 7, 7, 7, 7, 7, 6, 6, GameType.SINGLE_PLAYER);
    }

    public GameType getGameType() {
        return gameType;
    }

    public BoardConfig(XY size, int NUMBER_OF_GB, int NUMBER_OF_BB, int NUMBER_OF_GP, int NUMBER_OF_BP, int NUMBER_OF_WA) {
        this(size, 60, NUMBER_OF_GB, NUMBER_OF_BB, NUMBER_OF_GP, NUMBER_OF_BP, NUMBER_OF_WA, 6, 6, GameType.SINGLE_PLAYER);
    }

    public XY getSize() {
        return size;
    }

    public int getTICKLENGTH() {
        return TICKLENGTH;
    }

    public int getPOINTS_FOR_MINI_SQUIRREL() {
        return POINTS_FOR_MINI_SQUIRREL;
    }

    public int getSQUIRREL_STUN_TIME_IN_TICKS() {
        return SQUIRREL_STUN_TIME_IN_TICKS;
    }

    public int getBEAST_MOVE_TIME_IN_TICKS() {
        return BEAST_MOVE_TIME_IN_TICKS;
    }

    public int getMINI_SQUIRREL_MOVE_TIME_IN_TICKS() {
        return MINI_SQUIRREL_MOVE_TIME_IN_TICKS;
    }

    public int getGOODBEAST_VIEW_DISTANCE() {
        return GOODBEAST_VIEW_DISTANCE;
    }

    public int getBADBEAST_VIEW_DISTANCE() {
        return BADBEAST_VIEW_DISTANCE;
    }

    public int getNUMBER_OF_GB() {
        return NUMBER_OF_GB;
    }

    public int getNUMBER_OF_BB() {
        return NUMBER_OF_BB;
    }

    public int getNUMBER_OF_GP() {
        return NUMBER_OF_GP;
    }

    public int getNUMBER_OF_BP() {
        return NUMBER_OF_BP;
    }

    public int getNUMBER_OF_WA() {
        return NUMBER_OF_WA;
    }

}
