package de.hsa.games.fatsquirrel.gui;

import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.console.GameCommandType;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.core.entity.EntityType;
import de.hsa.games.fatsquirrel.core.entity.character.BadBeast;
import de.hsa.games.fatsquirrel.core.entity.character.PlayerEntity;
import de.hsa.games.fatsquirrel.core.entity.Entity;
import de.hsa.games.fatsquirrel.util.ui.Command;

import de.hsa.games.fatsquirrel.util.ui.CommandScanner;
import de.hsa.games.fatsquirrel.util.ui.CommandTypeInfo;
import de.hsa.games.fatsquirrel.util.ui.ScanException;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class FxUI extends Scene implements UI {

    private Canvas boardCanvas;
    private Label msgLabel;
    private static Command cmd = new Command(GameCommandType.NOTHING, new Object[0]);

    private final static int CELL_SIZE = 30;

    private FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
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
                                    new Object[]{10});
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

        for (ImplosionContext ic : view.getImplosions()) {
            XY position = ic.getPosition();
            int radius = ic.getRadius();

            double opacity = ic.getEnergyLoss() / 200 * (1 - ic.getTickCounter() / 6 / 10);

            //System.out.println("EnergyLoss: " + ic.getEnergyLoss() / 200);
            //System.out.println("TickCounter: " + (1 - ic.getTickCounter() / 6 / 10));

            //System.out.println(opacity);
            Color implisionColor = Color.color(1, 0, 0, opacity);

            gc.setFill(implisionColor);
            gc.fillOval(position.getX() * CELL_SIZE - CELL_SIZE * radius / 2,
                    position.getY() * CELL_SIZE - CELL_SIZE * radius / 2,
                    CELL_SIZE * radius,
                    CELL_SIZE * radius);
        }

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
        gc.setFill(entityTypeToColor(e));

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
        gc.setFill(entityTypeToTextColor(e));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(entityToString(e), (xy.getX() + 0.5) * CELL_SIZE, (xy.getY() + 0.5) * CELL_SIZE);
    }

    private Color entityTypeToColor(Entity e) {
        EntityType et = e.getEntityType();
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

    private Color entityTypeToTextColor(Entity e) {

        Color returnColor = Color.BLACK;
        EntityType et = e.getEntityType();

        switch (et) {
            case GOODPLANT:

                break;
            case GOODBEAST:

                break;
            case BADPLANT:
                returnColor = Color.WHITE;
                break;
            case BADBEAST:

                break;
            case WALL:

                break;
            case MINISQUIRREL:
                if (((PlayerEntity) e).getStunTime() != 0) {
                    returnColor = Color.RED;
                }

                break;
            case MASTERSQUIRREL:
                if (((PlayerEntity) e).getStunTime() != 0) {
                    returnColor = Color.RED;
                } else {
                    returnColor = Color.WHITE;
                }
                break;
            case HANDOPERATEDMASTERSQUIRREL:
                if (((PlayerEntity) e).getStunTime() != 0) {
                    returnColor = Color.RED;
                }

                break;
            default:
                returnColor = Color.BLACK;
        }
        return returnColor;
    }

    private String entityToString(Entity e) {

        EntityType et = e.getEntityType();
        String stringToPrint;
        switch (et) {
            case GOODPLANT:
                stringToPrint = "GP";
                break;
            case GOODBEAST:
                stringToPrint = "GB";
                stringToPrint = Integer.toString(e.getEnergy());
                break;
            case BADPLANT:
                stringToPrint = "BP";
                break;
            case BADBEAST:
                //stringToPrint = "BB";
                stringToPrint = Integer.toString(((BadBeast) e).getLives());
                break;
            case WALL:
                stringToPrint = "";
                break;
            case MINISQUIRREL:
                //stringToPrint = "mS";
                if (((PlayerEntity) e).getStunTime() != 0) {
                    stringToPrint = Integer.toString(((PlayerEntity) e).getStunTime());
                    stringToPrint = String.format("A%n" + stringToPrint);
                } else {

                    stringToPrint = Integer.toString(e.getEnergy());
                    stringToPrint = String.format("A%n" + stringToPrint);
                }

                break;
            case MASTERSQUIRREL:
                if (((PlayerEntity) e).getStunTime() != 0) {
                    stringToPrint = Integer.toString(((PlayerEntity) e).getStunTime());
                    stringToPrint = String.format("A%n" + stringToPrint);
                } else {
                    stringToPrint = Integer.toString(e.getEnergy());
                    stringToPrint = String.format("A%n" + stringToPrint);
                }

                break;
            case HANDOPERATEDMASTERSQUIRREL:
                if (((PlayerEntity) e).getStunTime() != 0) {
                    stringToPrint = Integer.toString(((PlayerEntity) e).getStunTime());
                } else {
                    stringToPrint = "HS";
                }

                break;
            default:
                stringToPrint = "";
        }
        return stringToPrint;
    }

    void message(final String msg) {
        Platform.runLater(() -> msgLabel.setText(msg));
    }

    @Override
    public Command getCommand() {
        Command temp = cmd;
        cmd = new Command(GameCommandType.NOTHING, new Object[0]);
        return temp;

    }


}