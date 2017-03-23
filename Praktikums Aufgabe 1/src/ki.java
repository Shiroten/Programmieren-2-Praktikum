/**
 * Created by Shiroten on 23.03.2017.
 */
public class ki {

    public static int zug(GameBoard board, int kiStrength) {

        int returnValue = 0;

        switch (kiStrength) {

            case 0:
                //Random Number KI
                returnValue = (int) (Math.random() * board.getWidth());
                break;

            case 1:
                //Monte Carlo
                int gewinnAnzahl;
                GameBoard kiBoard = board.copyBoard();

                int Reihen = kiBoard.getWidth();
                int[] zugMoeglichkeiten = new int[Reihen];

                for (int i = 0; i < Reihen; i++) {

                    gewinnAnzahl = 0;
                    //Berechnung von Wie oft wurde gewonnen
                    for (int j = 0; j < 1000; j++) {
                        if (spielLogik(kiBoard, i) == 1)
                            gewinnAnzahl++;
                    }
                    zugMoeglichkeiten[i] = gewinnAnzahl;
                }

                //Bestimmen des Besten Zugs
                int besterZug = 0;
                int besterZugWahrscheinlichkeit = 0;

                for (int i = 0; i < Reihen; i++) {
                    if (besterZugWahrscheinlichkeit < zugMoeglichkeiten[i]) {
                        besterZugWahrscheinlichkeit = zugMoeglichkeiten[i];
                        besterZug = i;
                    }
                }
                returnValue = besterZug;
        }
        return returnValue;
    }


    private static int spielLogik(GameBoard kiBoard, int anfangsReihe) {
        int returnValue = 0;
        int row = 0, column = 0;
        boolean spieler = true;
        int counter = 0;

        while (!kiBoard.checkWin(column, row)) {
            if (spieler) {
                int newColumn = (int) (Math.random() * kiBoard.getWidth());
                int newRow = kiBoard.insertChip('O', newColumn);
                if (newRow == -1) break;
                else {
                    spieler = !spieler;
                    row = newRow;
                    column = newColumn - 1;
                    if (kiBoard.checkWin(column, row)) {
                        returnValue = -1;
                    }
                }
            } else {
                int newColumn;
                if (counter == 0) {
                    newColumn = anfangsReihe;
                }else{
                    newColumn = (int) (Math.random() * kiBoard.getWidth());
                }
                int newRow = kiBoard.insertChip('X', newColumn);
                if (newRow == -1) break;
                else {
                    spieler = !spieler;
                    row = newRow;
                    column = newColumn - 1;
                    if (kiBoard.checkWin(column, row)) {
                        returnValue = 1;
                    }
                }
            }
            counter++;
        }
        return returnValue;
    }
}