package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 07.04.2017.
 */
public class Board {
    private EntitySet set;
    private BoardConfig config;
    private int idCounter = 0;

    public Board() {
        this.set = new EntitySet();
        this.config = new BoardConfig(new XY(100, 100));
    }

    public Board(XY size) {
        this.set = new EntitySet();
        this.config = new BoardConfig(new XY(size.getX(), size.getY()));
    }

    public Board(BoardConfig config) {
        this.set = new EntitySet();
        this.config = new BoardConfig(config.getSize(),
                config.getNumberOfGoodBeast(),
                config.getNumberOfBadBeast(),
                config.getNumberOfGoodPlant(),
                config.getNumberOfBadPlant(),
                config.getNumberOfWalls());
    }


    public EntitySet getSet() {
        return set;
    }

    public BoardConfig getConfig() {
        return config;
    }

    public FlattenedBoard flatten() {
        Entity[][] list = new Entity[config.getSize().getY()][config.getSize().getX()];
        for (int i = 0; i < set.getNumberOfMaxEntities(); i++) {
            if (set.getEntity(i) != null) {
                Entity dummy = set.getEntity(i);
                list[dummy.getCoordinate().getY()][dummy.getCoordinate().getX()] = dummy;
            }
        }

        FlattenedBoard fb = new FlattenedBoard(config.getSize(), this, list);

        return fb;
    }

    public void initBoard() {
        EntitySet ret = new EntitySet();

        //Äußere Mauern
        int WallIDs = setID();
        int x = config.getSize().getX(), y = config.getSize().getY();
        for (int i = 0; i < x; i++) {
            set.add(new Wall(WallIDs, new XY(i, 0)));
            set.add(new Wall(WallIDs, new XY(i, y - 1)));
        }
        for (int i = 1; i < y - 1; i++) {
            set.add(new Wall(WallIDs, new XY(0, i)));
            set.add(new Wall(WallIDs, new XY(x - 1, i)));
        }

        //Random Entitys auf der Map verteilt
        randomAddEntity(EntityType.WALL, config.getNumberOfWalls());
        randomAddEntity(EntityType.BADBEAST, config.getNumberOfBadBeast());
        randomAddEntity(EntityType.BADPLANT, config.getNumberOfBadPlant());
        randomAddEntity(EntityType.GOODBEAST, config.getNumberOfGoodBeast());
        randomAddEntity(EntityType.GOODPLANT, config.getNumberOfGoodPlant());

    }

    public int setID() {

        return idCounter++;
    }

    public void randomAddEntity(EntityType type, int ammount) {

        Entity addEntity = null;
        for (int i = 0; i < ammount; i++) {

            XY position = randomPosition(config.getSize());
            int randomX = position.getX(), randomY = position.getY();

            switch (type) {
                case WALL:
                    addEntity = new Wall(setID(), new XY(randomX, randomY));
                    break;
                case BADBEAST:
                    addEntity = new BadBeast(setID(), new XY(randomX, randomY));
                    break;
                case BADPLANT:
                    addEntity = new BadPlant(setID(), new XY(randomX, randomY));
                    break;
                case GOODBEAST:
                    addEntity = new GoodBeast(setID(), new XY(randomX, randomY));
                    break;
                case GOODPLANT:
                    addEntity = new GoodPlant(setID(), new XY(randomX, randomY));
                    break;
            }
            set.add(addEntity);

        }

    }

    public Entity randomAddEntity(EntityType type, XY position) {

        Entity addEntity = null;

        switch (type) {
            case WALL:
                addEntity = new Wall(setID(), position);
                break;
            case BADBEAST:
                addEntity = new BadBeast(setID(), position);
                break;
            case BADPLANT:
                addEntity = new BadPlant(setID(), position);
                break;
            case GOODBEAST:
                addEntity = new GoodBeast(setID(), position);
                break;
            case GOODPLANT:
                addEntity = new GoodPlant(setID(), position);
                break;
        }
        set.add(addEntity);

        return addEntity;
    }


    public XY randomPosition(XY size) {

        boolean collision;
        int newX, newY;

        do {
            collision = false;
            newX = (int) ((Math.random() * size.getX()));
            newY = (int) ((Math.random() * size.getY()));

            //Durchsuchen des Entityset nach möglichen Konflikten
            for (int i = 0; i < set.getNumberOfMaxEntities(); i++) {

                if (set.getEntity(i) == null) {
                    return new XY(newX, newY);
                } else if (newX == set.getEntity(i).getCoordinate().getX() && newY == set.getEntity(i).getCoordinate().getY()) {
                    collision = true;
                    break;
                }
            }

        } while (collision);
        return new XY(newX, newY);
    }

}
