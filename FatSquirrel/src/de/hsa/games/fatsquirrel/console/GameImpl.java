package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.Vector;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.util.ui.Command;
import de.hsa.games.fatsquirrel.util.ui.CommandTypeInfo;
import de.hsa.games.fatsquirrel.util.ui.ScanException;

/**
 * Created by tillm on 22.04.2017.
 */
public class GameImpl extends Game {


    public GameImpl() {
        this.setUi(new ConsoleUI());

        //Todo: Board in State kapseln
        BoardConfig config = new BoardConfig(new XY(12, 12), 1, 1, 4, 3, 3);
        Board board = new Board(config);
        this.setState(new State(board));
    }

    protected void processInput() {

        Command command = this.getUi().getCommand();
        Object[] params = command.getParams();
        GameCommandType commandType = (GameCommandType) command.getCommandTypeInfo();

        Entity entitySet[] = this.getState().getBoard().getSet().getEntityList();

        switch (commandType) {
            case EXIT:
                System.exit(0);

            case HELP:
                for (CommandTypeInfo i : GameCommandType.values()) {
                    System.out.println(i.getName() + " " + i.getHelpText());
                }
                return;

            case ALL:
                //Todo: ALL Befehl definieren
                return;

            case LEFT:
                this.command = MoveCommand.EAST;
                return;

            case RIGHT:
                this.command = MoveCommand.WEST;
                return;

            case UP:
                this.command = MoveCommand.NORTH;
                return;

            case DOWN:
                this.command = MoveCommand.SOUTH;
                return;

            case MASTER_ENERGY:
                //Todo: Eventuell Referenz für HandoperatedSquirrel setzen
                for (Entity i : entitySet) {
                    if (i.getEntityType() == EntityType.HANDOPERATEDMASTERSQUIRREL) {
                        System.out.printf("Energy vom MasterSquirrel: %d", i.getEnergy());
                        return;
                    }
                }
                break;

            case SPAWN_MINI:
                for (Entity i : entitySet) {
                    if (i.getEntityType() == EntityType.HANDOPERATEDMASTERSQUIRREL) {
                        try {
                            spawnMini((MasterSquirrel) i, (int) params[0]);
                            return;
                        } catch (NotEnoughEnergyException n) {
                            System.out.println("Not Enough Energy");
                        }
                    }
                }

            default:
                return;
        }

    }

    private void spawnMini(MasterSquirrel masterSquirrel, int energy) throws NotEnoughEnergyException {

        XY locationOfMaster = masterSquirrel.getCoordinate();
        for (MoveCommand offset : MoveCommand.values()) {
            //Wenn dieses Feld leer ist....
            if (masterSquirrel.getEnergy() >= energy) {
                if (this.getState().flattenBoard().getEntityType(locationOfMaster.addVector(Vector.moveCommandToVector(offset))) == EntityType.EMPTY) {

                    //Füge neues StandardMiniSquirrel hinzu zum Board
                    this.getState().getBoard().add(
                            new StandardMiniSquirrel(
                                    this.getState().getBoard().setID(),
                                    (locationOfMaster.addVector(Vector.moveCommandToVector(offset))),
                                    energy,
                                    masterSquirrel));
                    return;
                }
            } else {
                throw new NotEnoughEnergyException();
            }
        }

    }

    protected void render() {
        this.getUi().render(this.getState().flattenBoard());
    }

    protected void update() {
        //Todo; MiniSquirrel spawn in die Update Methode verlegen.
        getState().update(command);
    }
}
