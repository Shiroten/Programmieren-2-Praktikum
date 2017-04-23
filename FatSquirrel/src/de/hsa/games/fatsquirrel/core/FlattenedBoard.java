package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 07.04.2017.
 */
public class FlattenedBoard implements BoardView, EntityContext {
    private final XY size;
    private Board board;
    private final int PointsForMiniSquirrel = 150;

    //TODO: Kein wirkliches todo, sondern nur für Sichtbarkeit ;-)
    //Ganz, ganz wichtig für Konsistenz:
    //Ein Mehrdimensionales Array zählt folgendermaßen:
    //a[0][0] a[0][1] a[0][2]
    //a[1][0] ...
    //...
    //a[y][x]
    //Das heißt der erste Wert ist die y-Koordinate (Höhe), der zweite Wert ist die x-Koordinate (Breite)
    private Entity[][] flattenedBoard;

    public FlattenedBoard(XY size, Board board, Entity[][] flat) {
        this.size = size;
        flattenedBoard = new Entity[size.getY()][size.getX()];
        this.board = board;
        this.flattenedBoard = flat;
    }

    //Hier wird die Koordinate etwas missbraucht: man speichert in einer Koordinate die x-Höhe
    //und die y-Höhe. Felder sind wohl nicht immer quadratisch...
    public XY getSize() {
        return size;
    }

    //Zuerst wird geschaut, auf welchem Feld die Entity landen wird
    //Dann wird geschaut, ob und wenn ja welche Entity sich auf dem Feld befindet
    //In Abhängkeit der E wird die Energie abgezogen

    public void move(Entity en, XY newPosition) {

        //Alte Position Löschen im Array
        flattenedBoard[en.getCoordinate().getX()][en.getCoordinate().getY()] = null;

        //Neue Position im EntitySet setzen
        en.setCoordinate(newPosition);

        //Neue Position im Array ablegen
        flattenedBoard[en.getCoordinate().getX()][en.getCoordinate().getY()] = en;

    }

    public void moveOrKillMiniSquirrel(MiniSquirrel miniSquirrel, XY newPosition) {

        if (miniSquirrel.getEnergy() <= 0) {
            killEntity(miniSquirrel);
        } else {
            move(miniSquirrel, newPosition);
        }
    }

    public void moveAndKillMiniSquirrel(Entity en, int startEnergy, XY newField) {
        en.updateEnergy(startEnergy);
        killAndReplace(flattenedBoard[newField.getX()][newField.getY()]);
        moveOrKillMiniSquirrel((MiniSquirrel) en, newField);
    }

    @Override
    public void tryMove(MiniSquirrel miniSquirrel, Vector vector) {
        XY newField = miniSquirrel.getCoordinate().addVector(vector);

        switch (getEntityType(newField)) {
            case WALL:
                miniSquirrel.updateEnergy(Wall.START_ENERGY);
                //Todo: Betäuben
                moveOrKillMiniSquirrel(miniSquirrel, miniSquirrel.getCoordinate());
                break;

            case BADBEAST:
                miniSquirrel.updateEnergy(BadBeast.START_ENERGY);
                moveOrKillMiniSquirrel(miniSquirrel, miniSquirrel.getCoordinate());
                //Todo: Badbeast Counter reduzieren
                break;

            case BADPLANT:
                moveAndKillMiniSquirrel(miniSquirrel, BadPlant.START_ENERGY, newField);
                break;

            case GOODBEAST:
                moveAndKillMiniSquirrel(miniSquirrel, GoodBeast.START_ENERGY, newField);
                break;

            case GOODPLANT:
                moveAndKillMiniSquirrel(miniSquirrel, GoodPlant.START_ENERGY, newField);
                break;

            case MINISQUIRREL:
                if (miniSquirrel.getDaddy() ==
                        ((MiniSquirrel) flattenedBoard[newField.getX()][newField.getY()]).getDaddy()) {
                    //Mach nichts, da freundliche Kollision
                } else {

                    //Löse beide MiniSquirrel auf
                    killEntity(miniSquirrel);
                    killEntity(flattenedBoard[newField.getX()][newField.getY()]);
                }
                break;

            case MASTERSQUIRREL:
                if (((MasterSquirrel) flattenedBoard[newField.getX()][newField.getY()]).mySquirrel(miniSquirrel)) {

                    //Kollision mit eigenem MasterSquirrel: auflösen und Energie übertragen
                    (flattenedBoard[newField.getX()][newField.getY()]).updateEnergy(miniSquirrel.getEnergy());
                    killEntity(miniSquirrel);

                } else {
                    //Kollision mit fremdem MasterSquirrel: nur auflösen
                    killEntity(miniSquirrel);
                }
                break;

            default:
                //Keine Kollisionen: einfacher Move
                move(miniSquirrel, newField);
        }


    }

    @Override
    public void tryMove(GoodBeast goodBeast, Vector vector) {

        XY newField = goodBeast.getCoordinate().addVector(vector);

        switch (getEntityType(newField)) {
            case WALL:
                break;
            case BADBEAST:
                break;
            case BADPLANT:
                break;
            case GOODBEAST:
                break;
            case GOODPLANT:
                break;
            case MINISQUIRREL:
                flattenedBoard[newField.getX()][newField.getY()].updateEnergy(GoodBeast.START_ENERGY);
                killAndReplace(goodBeast);
                break;
            case MASTERSQUIRREL:
                flattenedBoard[newField.getX()][newField.getY()].updateEnergy(GoodBeast.START_ENERGY);
                killAndReplace(goodBeast);
                break;
            default:
                move(goodBeast, newField);
        }

    }

    @Override
    public void tryMove(BadBeast badBeast, Vector vector) {

        XY newField = badBeast.getCoordinate().addVector(vector);

        switch (getEntityType(newField)) {
            case WALL:
                break;
            case BADBEAST:
                break;
            case BADPLANT:
                break;
            case GOODBEAST:
                break;
            case GOODPLANT:
                break;
            case MINISQUIRREL:
                flattenedBoard[newField.getX()][newField.getY()].updateEnergy(BadBeast.START_ENERGY);
                moveOrKillMiniSquirrel((MiniSquirrel) flattenedBoard[newField.getX()][newField.getY()],
                        flattenedBoard[newField.getX()][newField.getY()].getCoordinate());

                //Todo: BadBeast Counter
                break;
            case MASTERSQUIRREL:
                flattenedBoard[newField.getX()][newField.getY()].updateEnergy(BadBeast.START_ENERGY);

                //Todo: BadBeast Counter
                break;
            default:
                move(badBeast, newField);
        }
    }

    public void moveAndKill(Entity en, int startEnergy, XY newField) {
        en.updateEnergy(startEnergy);
        killAndReplace(flattenedBoard[newField.getX()][newField.getY()]);
        move(en, newField);
    }


    @Override
    public void tryMove(MasterSquirrel masterSquirrel, Vector vector) {

        XY newField = masterSquirrel.getCoordinate().addVector(vector);

        switch (getEntityType(newField)) {
            case WALL:
                masterSquirrel.updateEnergy(Wall.START_ENERGY);
                //Todo: Betäuben
                break;
            case BADBEAST:
                masterSquirrel.updateEnergy(BadBeast.START_ENERGY);
                //Todo: Badbeast Counter reduzieren
                break;
            case BADPLANT:
                moveAndKill(masterSquirrel, BadPlant.START_ENERGY, newField);
                break;
            case GOODBEAST:
                moveAndKill(masterSquirrel, GoodBeast.START_ENERGY, newField);
                break;
            case GOODPLANT:
                moveAndKill(masterSquirrel, GoodPlant.START_ENERGY, newField);
                break;
            case MINISQUIRREL:

                MiniSquirrel squirrel = (MiniSquirrel) flattenedBoard[newField.getX()][newField.getY()];
                int energy;

                //Todo: Ternärer Operator falsch geschrieben?
                //Was fehlt in dieser Zeile?
                //masterSquirrel.mySquirrel(squirrel)? energy = squirrel.getEnergy() : energy = PointsForMiniSquirrel;

                if (masterSquirrel.mySquirrel(squirrel)) {
                    //Eigener MiniSquirrel wird absorbiert
                    energy = squirrel.getEnergy();
                } else {
                    //Fremder MiniSquirrel wird gerammt
                    energy = PointsForMiniSquirrel;
                }

                masterSquirrel.updateEnergy(energy);
                killEntity(squirrel);
                move(masterSquirrel, newField);
                break;

            case MASTERSQUIRREL:
                //Stößen nur mit den Köpfen Zusammen
                break;
            default:
                move(masterSquirrel, newField);
        }

    }

    //Aus dem Board alle Player-E's ziehen
    //Abstand von pos zu PEs berechnen
    //nächste PE zurückgeben

    @Override
    public PlayerEntity nearestPlayerEntity(XY pos) {
        PlayerEntity nPE = null;
        for (Entity e : board.getSet().getEntityList()) {
            if (e instanceof PlayerEntity) {
                if (nPE == null)
                    nPE = (PlayerEntity) e;
                else {
                    Vector v = new Vector(nPE.getCoordinate(), pos);
                    double distance = v.getLength();
                    Vector v2 = new Vector(e.getCoordinate(), pos);
                    if (distance > v2.getLength())
                        nPE = (PlayerEntity) e;
                }

            }
        }

        return nPE;
    }


    @Override
    public void killEntity(Entity entity) {
        for (int i = 0; i < size.getY(); i++) {
            for (int j = 0; j < size.getX(); j++) {
                if (flattenedBoard[i][j] == entity) {
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
        Entity newE = board.randomAddEntity(entity.getEntityType(), randomFreePosition());
        flattenedBoard[newE.getCoordinate().getY()][newE.getCoordinate().getX()] = newE;
    }

    @Override
    public EntityType getEntityType(XY xy) {
        try {
            return flattenedBoard[xy.getY()][xy.getX()].getEntityType();
        } catch (Exception e) {
            //TODO: Printstacktrace nach debuggen entfernen
            //e.printStackTrace();
            return null;
        }
    }

    private XY randomFreePosition() {
        XY xy;
        do {
            xy = new XY((int) Math.round(Math.random() * size.getX()), (int) Math.round(Math.random() * size.getY()));
        }
        while (flattenedBoard[xy.getY()][xy.getX()] != null);

        return xy;
    }
}
