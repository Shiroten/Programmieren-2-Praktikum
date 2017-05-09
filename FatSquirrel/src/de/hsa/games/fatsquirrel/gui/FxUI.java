package de.hsa.games.fatsquirrel.gui;

import de.hsa.games.fatsquirrel.MoveCommand;
import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.console.GameCommandType;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.util.ui.Command;

import javafx.application.Platform;

import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;


public class FxUI extends Scene implements UI {

    private Canvas boardCanvas;
    private Label msgLabel;
    private static Command cmd = new Command(GameCommandType.NOTHING, new Object[0]);

    final static int CELL_SIZE = 30;

    public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
        super(parent);
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;
    }

    public static FxUI createInstance(XY boardSize) {
        Canvas boardCanvas = new Canvas(boardSize.getX() * CELL_SIZE, boardSize.getY() * CELL_SIZE);
        Label statusLabel = new Label();
        VBox top = new VBox();
        top.getChildren().add(boardCanvas);
        top.getChildren().add(statusLabel);

        statusLabel.setText("Fange Squirrel ein");
        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel);

        fxUI.setOnKeyPressed(
                keyEvent -> {
                    switch (keyEvent.getCode()) {
                        case W:
                        case UP:
                            cmd = new Command(GameCommandType.UP, new Object[0]);
                            break;
                        case A:
                        case LEFT:
                            cmd = new Command(GameCommandType.LEFT, new Object[0]);
                            break;
                        case S:
                        case DOWN:
                            cmd = new Command(GameCommandType.DOWN, new Object[0]);
                            break;
                        case D:
                        case RIGHT:
                            cmd = new Command(GameCommandType.RIGHT, new Object[0]);
                            break;
                        case F:
                            //Todo: Spawn Mini Energy in Config setzen oder per menü
                            cmd = new Command(GameCommandType.SPAWN_MINI,
                                    new Object[]{100});
                            break;
                        case P:
                            cmd = new Command(GameCommandType.CHEAT_ENERGY, new Object[0]);
                            break;
                        default:
                            cmd = new Command(GameCommandType.NOTHING, new Object[0]);
                    }
                }
        );
        return fxUI;
    }


    @Override
    public void render(final BoardView view) {
        Platform.runLater(() -> repaintBoardCanvas(view));
    }

    private void repaintBoardCanvas(BoardView view) {
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        for (int x = 0; x < boardCanvas.getWidth(); x++) {
            for (int y = 0; y < boardCanvas.getHeight(); y++) {
                printEntity(gc, view.getEntity(new XY(x, y)), new XY(x, y));
            }
        }
    }

    private void printEntity(GraphicsContext gc, Entity e, XY xy) {

        if (e == null)
            return;

        //Kästchen für die Entity setzen
        EntityType et = e.getEntityType();
        gc.setFill(entityTypeToColor(et));

        switch (et) {
            case WALL:
                gc.fillRect(xy.getX() * CELL_SIZE, xy.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                break;
            case EMPTY:
                return;
            default:
                gc.fillOval(xy.getX() * CELL_SIZE, xy.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        //Text Schreiben
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(entityTypeToString(e), (xy.getX() + 0.5) * CELL_SIZE, (xy.getY() + 0.5) * CELL_SIZE);
    }

    private Color entityTypeToColor(EntityType et) {
        Color returnColor;
        switch (et) {
            case GOODPLANT:
                returnColor = Color.color(0, 1, 0);
                break;
            case GOODBEAST:
                returnColor = Color.color(1, 0.9765, 0);
                break;
            case BADPLANT:
                returnColor = Color.color(0, 0.2353, 0);
                break;
            case BADBEAST:
                returnColor = Color.color(1, 0.0392, 0);
                break;
            case WALL:
                returnColor = Color.color(0.3804, 0.3804, 0.3765);
                break;
            case MINISQUIRREL:
                returnColor = Color.color(1, 0.5412, 0);
                break;
            case MASTERSQUIRREL:
                returnColor = Color.color(0, 0.0588, 1);
                break;
            case HANDOPERATEDMASTERSQUIRREL:
                returnColor = Color.color(0.2314, 0.7843, 1);
                break;
            default:
                returnColor = Color.gray(0, 0);
        }
        return returnColor;
    }

    private String entityTypeToString(Entity e) {

        EntityType et = e.getEntityType();
        String stringToPrint;
        switch (et) {
            case GOODPLANT:
                stringToPrint = "GP";
                break;
            case GOODBEAST:
                stringToPrint = "GB";
                break;
            case BADPLANT:
                stringToPrint = "BP";
                break;
            case BADBEAST:
                stringToPrint = "BB";
                break;
            case WALL:
                stringToPrint = "";
                break;
            case MINISQUIRREL:
                //stringToPrint = "mS";
                stringToPrint = Integer.toString(e.getEnergy());
                break;
            case MASTERSQUIRREL:
                stringToPrint = "MS";
                break;
            case HANDOPERATEDMASTERSQUIRREL:
                stringToPrint = "HS";
                break;
            default:
                stringToPrint = "";
        }
        return stringToPrint;
    }

    public void message(final String msg) {
        Platform.runLater(() -> msgLabel.setText(msg));
    }

    @Override
    public Command getCommand() {
        Command temp = cmd;
        cmd = new Command(GameCommandType.NOTHING, new Object[0]);
        return temp;

    }


}