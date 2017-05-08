package de.hsa.games.fatsquirrel.gui;

import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.util.ui.Command;
import javafx.application.Platform;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FxUI extends Scene implements UI {

    private Canvas boardCanvas;
    private Label msgLabel;
    private Command cmd;

    final static int CELL_SIZE = 50;

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

        //Todo: Entfernen wenn befehl nicht mehr zur Referenz benötigt wird
        //statusLabel.setText("Hallo Welt");
        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel);

        fxUI.setOnKeyPressed(
                keyEvent -> {
                    System.out.println("Es wurde folgende Taste gedrückt: " + keyEvent.getCode() + " bitte behandeln!");

                    // Command mit richtigen Richtungsangaben überschreiben
                    // TODO handle event
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
        XY viewSize = view.getSize();

        gc.setStroke(Color.BLUE);
        gc.setLineWidth(50);
        gc.strokeLine(40, 10, 100, 400);

        gc.fillText("Where are the beasts?", 100, 100);
        gc.setFill(Color.RED);
        gc.fillOval(150, 150, 50, 50);
        gc.fillOval(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());

    }


    //Todo: @Override von FxUI/message() fixen
    //@Override
    public void message(final String msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                msgLabel.setText(msg);
            }
        });
    }

    @Override
    public Command getCommand() {
        return cmd;
    }
}