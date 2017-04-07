package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 07.04.2017.
 */
public class FlattenedBoard implements BoardView, EntityContext {
    private final XY size;
    private Board board;

    //TODO: Kein wirkliches todo, sondern nur für Sichtbarkeit ;-)
    //Ganz, ganz wichtig für Konsistenz:
    //Ein Mehrdimensionales Array zählt folgendermaßen:
    //a[0][0] a[0][1] a[0][2]
    //a[1][0] ...
    //...
    //Das heißt der erste Wert ist die y-Koordinate (Höhe), der zweite Wert ist die x-Koordinate (Breite)
    private Entity[][] flattenedBoard;

    public FlattenedBoard(XY size, Board board, Entity[][] flat){
        this.size = size;
        flattenedBoard = new Entity[size.getY()][size.getX()];
        this.board = board;
        this.flattenedBoard = flat;
    }

    //Hier wird die Koordinate etwas missbraucht: man speichert in einer Koordinate die x-Höhe
    //und die y-Höhe. Felder sind wohl nicht immer quadratisch...
    public XY getSize(){
        return size;
    }

    //TODO: tryMove-Methoden implentieren
    @Override
    public void tryMove(MiniSquirrel miniSquirrel, Vector vector) {

    }

    @Override
    public void tryMove(GoodBeast goodBeast, Vector vector) {

    }

    @Override
    public void tryMove(BadBeast badBeast, Vector vector) {

    }

    @Override
    public void tryMove(MasterSquirrel masterSquirrel, Vector vector) {

    }

    //TODO: nearestPlayerEntity ordentlich implementieren
    @Override
    public HandOperatedMasterSquirrel nearestPlayerEntity(XY pos) {
        return null;
    }

    @Override
    public void killEntity(Entity entity) {
        for(int i = 0; i < size.getY(); i++){
            for(int j = 0; j < size.getX(); j++){
                if(flattenedBoard[i][j] == entity){
                    flattenedBoard[i][j] = null;
                    board.getSet().delete(entity);
                    return;
                }
            }
        }
    }

    @Override
    public void killAndReplace(Entity entity) {
        killEntity(entity);
        entity.setCoordinate(randomFreePosition());
    }

    @Override
    public EntityType getEntityType(XY xy) {
        try{
            return flattenedBoard[xy.getY()][xy.getX()].getEntityType();
        }catch (IndexOutOfBoundsException e){
            System.out.println("IOOE in getEntityType");
            return null;
        }
    }

    private XY randomFreePosition(){
        XY xy;
        do {
            xy = new XY((int) Math.round(Math.random() * size.getX()), (int) Math.round(Math.random() * size.getY()));
        }
        while(flattenedBoard[xy.getY()][xy.getX()] != null);

        return xy;
    }
}
