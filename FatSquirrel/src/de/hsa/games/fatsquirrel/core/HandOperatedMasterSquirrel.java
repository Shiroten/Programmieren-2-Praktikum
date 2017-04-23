package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;

import java.util.Scanner;

/**
 * Created by tillm on 29.03.2017.
 */
public class HandOperatedMasterSquirrel extends MasterSquirrel {
    public static final EntityType type = EntityType.HANDOPERATEDMASTERSQUIRREL;
    public HandOperatedMasterSquirrel(int id, XY coordinate) {
        super(id, coordinate);
    }

    public EntityType getEntityType() {
        return type;
    }

    public void nextStep() {

        int thisX = this.getCoordinate().getX();
        int thisY = this.getCoordinate().getY();

        XY destination = new XY(thisX, thisY);
        setCoordinate(destination.addVector(move()));
    }

    public void nextStep(MoveCommand command, EntityContext context){
        switch(command){
            case EAST: context.tryMove(this, new Vector(1,0)); break;
            case WEST: context.tryMove(this, new Vector(-1,0)); break;
            case NORTH: context.tryMove(this, new Vector(0,-1)); break;
            case SOUTH: context.tryMove(this, new Vector(0,1)); break;
            case NORTHEAST: context.tryMove(this, new Vector(1,-1)); break;
            case NORTHWEST: context.tryMove(this, new Vector(-1,-1)); break;
            case SOUTHEAST: context.tryMove(this, new Vector(1,1)); break;
            case SOUTHWEST: context.tryMove(this, new Vector(-1,1)); break;
            case NOWHERE: break;
            default: break;
        }
    }

    public Vector move() {

        Vector destination = new Vector(0, 0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie eine Richtung für ihr Squirrel in WASD an: ");

        try {
            //char c = (char) System.in.read();
            char c = scanner.next().charAt(0);

            c = Character.toUpperCase(c);
            switch (c) {
                case 'W':
                    destination = (new Vector(0, -1));
                    break;
                case 'A':
                    destination = (new Vector(-1, 0));
                    break;
                case 'S':
                    destination = (new Vector(0, +1));
                    break;
                case 'D':
                    destination = (new Vector(1, 0));
                    break;
                default:
                    destination = (new Vector(0, 0));
            }

        } catch (Exception e) {
            System.out.println("Bitte nur W A S D benutzen!");
        }
        return destination;
    }
}
