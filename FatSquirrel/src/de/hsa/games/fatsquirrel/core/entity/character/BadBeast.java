package de.hsa.games.fatsquirrel.core.entity.character;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.XYsupport;
import de.hsa.games.fatsquirrel.core.entity.EntityContext;
import de.hsa.games.fatsquirrel.core.entity.EntityType;

public class BadBeast extends Character {
    public static final int START_ENERGY = -150;
    public static final EntityType type = EntityType.BADBEAST;
    private int moveCounter = 0;
    private int lives;

    public BadBeast(int id, XY coordinate) {
        super(START_ENERGY, id, coordinate);
        this.lives = 7;
    }

    @Override
    public EntityType getEntityType() {
        return type;
    }

    public void nextStep(EntityContext context) {
        if (moveCounter == 0) {
            PlayerEntity pe = context.nearestPlayerEntity(this.getCoordinate());
            XY distance = new XY(pe.getCoordinate(), this.getCoordinate());

            if (distance.length() < context.getBADBEAST_VIEW_DISTANCE())

                context.tryMove(this, XYsupport.normalizedVector(distance));
            else
                context.tryMove(this, XYsupport.randomDirection());
            moveCounter++;
        } else if (moveCounter == context.getBEAST_MOVE_TIME_IN_TICKS())
            moveCounter = 0;
        else
            moveCounter++;
    }

    public String toString() {
        return ("de.hsa.games.fatsquirrel.core.entity.character.BadBeast: " + super.toString());
    }

    public void bites() {
        lives--;
    }

    public int getLives() {
        return this.lives;
    }

}