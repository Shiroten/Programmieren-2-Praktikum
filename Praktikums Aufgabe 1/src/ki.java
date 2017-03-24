/**
 * Created by Shiroten on 23.03.2017.
 */
public class ki {

    public static int zug(GameBoard board, int kiStrength) {

        int returnValue = 1;

        switch (kiStrength) {

            case 0:
                //Random Number KI
                returnValue = (int) ((Math.random() * board.getWidth()) + 1);
                break;

            case 1:
                //Monte Carlo
                int gewinnAnzahl;
                int Reihen = board.getWidth();
                int[] zugMoeglichkeiten = new int[Reihen];

                for (int i = 0; i < Reihen; i++) {

                    //Versuche resetten
                    gewinnAnzahl = 0;
                    GameBoard kiBoard = board.copyBoard();

                    //Berechnung von Wie oft wurde gewonnen
                    for (int j = 0; j < 10; j++) {
                        if (spielLogik(kiBoard, i + 1) == 1) {
                            gewinnAnzahl++;
                        }
                    }
                    zugMoeglichkeiten[i] = gewinnAnzahl;
                    System.out.println(i + ". gewinnAnzahl: " + gewinnAnzahl);

                }

                //Bestimmen des Besten Zugs
                int besterZug = 0;
                int besterZugWahrscheinlichkeit = 0;

                for (int i = 0; i < Reihen; i++) {
                    if (besterZugWahrscheinlichkeit < zugMoeglichkeiten[i]) {
                        besterZugWahrscheinlichkeit = zugMoeglichkeiten[i];
                        besterZug = i;
                    }
                    System.out.println(zugMoeglichkeiten[i]);
                }
                System.out.println("besterzug: " + (besterZug + 1));
                returnValue = besterZug + 1;
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
                int newColumn = (int) ((Math.random() * kiBoard.getWidth()) + 1);
                //System.out.println("newColumn: " + newColumn);
                int newRow = kiBoard.insertChip('O', newColumn);
                if (newRow != -1) {
                    newColumn = (int) ((Math.random() * kiBoard.getWidth()) + 1);
                } else {
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
                } else {
                    newColumn = (int) ((Math.random() * kiBoard.getWidth()) + 1);
                }
                int newRow = kiBoard.insertChip('X', newColumn);
                if (newRow == -1)
                    newColumn = (int) ((Math.random() * kiBoard.getWidth()) + 1);
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