package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.XY;

/**
 * Created by tillm on 07.04.2017.
 */
public class Board {

    private EntitySet set;
    private BoardConfig config;
    private int idCounter = 0;
    private HandOperatedMasterSquirrel masterSquirrel;

    public Board() {
        this.set = new EntitySet(new XY(20,20));
        this.config = new BoardConfig(new XY(20, 20), 60, 7, 7, 7, 7, 7);
        initBoard();
    }

    public Board(BoardConfig config) {
        this.set = new EntitySet(config.getSize());
        this.config = config;
        initBoard();
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
                try {
                    list[dummy.getCoordinate().getY()][dummy.getCoordinate().getX()] = dummy;
                }
                catch(Exception e){
                    e.printStackTrace();
                    System.out.println(dummy.toString());
                }
            }
        }

        FlattenedBoard fb = new FlattenedBoard(config.getSize(), this, list);

        return fb;
    }

    public void initBoard() {
        //Äußere Mauern
        initOuterWalls();

        //Random Entitys auf der Map verteilt
        addEntity(EntityType.WALL, config.getNUMBER_OF_WA());
        addEntity(EntityType.BADBEAST, config.getNUMBER_OF_BB());
        addEntity(EntityType.BADPLANT, config.getNUMBER_OF_BP());
        addEntity(EntityType.GOODBEAST, config.getNUMBER_OF_GB());
        addEntity(EntityType.GOODPLANT, config.getNUMBER_OF_GP());

        addEntity(EntityType.HANDOPERATEDMASTERSQUIRREL, 1);

    }

    private void initOuterWalls(){
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
    }

    public int setID() {

        return idCounter++;
    }

    public void addEntity(EntityType type, int ammount) {

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
                case HANDOPERATEDMASTERSQUIRREL:
                    addEntity = new HandOperatedMasterSquirrel(setID(), new XY(randomX, randomY));
                    masterSquirrel = (HandOperatedMasterSquirrel)addEntity;
                    break;
                case MASTERSQUIRREL:
                    addEntity = new MasterSquirrel(setID(), new XY(randomX, randomY));
                    break;
            }
            set.add(addEntity);

        }

    }

    public Entity addEntity(EntityType type, XY position) {

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

    public void killEntity(Entity e){
        this.set.delete(e);
    }

    private void initPac(){
        if(this.config.getSize().getX()  == 20 && this.getConfig().getSize().getY() == 20){
            initOuterWalls();
            String field =
                "000X0000000000X000|" +
                "0X0X0XXXX0XXX0X0X0|" +
                "0X0x0xxxx0xxx0x0x0|" +
                "00000x000000x000x0|" +
                "xxxx0x0xxxx0x0x0x0|" +
                "00000x0x00000000x0|" +
                "0xxx000x0xxxxxx000|" +
                "0x000xxx0xxxxxx0xx|" +
                "0x0x0x00000000x000|" +
                "000X000xx0xxx0X000|" +
                "0X0X0XXXX0XXX0X0X0|" +
                "0X0x0xxxx0xxx0x0x0|" +
                "00000x000000x000x0|" +
                "xxxx0x0xxxx0x0x0x0|" +
                "00000x0x00000000x0|" +
                "0xx0000x0xx0x0x000|" +
                "0xx0xxxx0xx0x0x0xx|" +
                "000000000000x00000|";
            parseWalls(field);
        }
    }

    private void parseWalls(String field){
        int x = 0, y = 1;
        field.toUpperCase();
        for(char c : field.toCharArray()){
            x++;
            if(c == 'X' || c == 'x'){
                addEntity(EntityType.WALL, new XY(x, y));
            }
            else if(c == '|'){
                x = 0;
                y++;
            }
        }
    }

    public void add(Entity toAdd){
        this.set.add(toAdd);
    }

    public HandOperatedMasterSquirrel getMasterSquirrel() {
        return masterSquirrel;
    }
}
