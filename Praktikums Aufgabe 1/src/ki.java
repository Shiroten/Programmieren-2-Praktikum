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
                        if (simulation2(kiBoard, i + 1) == 1) {
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

    private static int simulation(GameBoard kiBoard, int anfangsReihe) {

        int row = 0, column = 0, returnValue = 0;
        boolean spieler = false, anfang = true;
        while (!kiBoard.checkWin(column, row)) {

            if (spieler) {
                //Simulation Spieler Zug
                int newColumn = (int) ((Math.random() * kiBoard.getWidth()) + 1);
                int newRow = kiBoard.insertChip('O', newColumn);
                spieler = !spieler;
                row = newRow;
                column = newColumn - 1;
                if (kiBoard.checkWin(column, row)) {
                    returnValue = -1;
                }

            } else {
                //Simulation KI Zug
                int newColumn;
                if (anfang == true) { //Erster Zug bestimmt durch die Anfangs Reihe
                    newColumn = anfangsReihe;
                    anfang = false;
                } else {
                    newColumn = (int) ((Math.random() * kiBoard.getWidth()) + 1);
                }
                int newRow = kiBoard.insertChip('X', newColumn);
                spieler = !spieler;
                row = newRow;
                column = newColumn - 1;
                if (kiBoard.checkWin(column, row)) {
                    returnValue = 1;
                }
            }
        }
        //Übergibt 1 beim gewinnen, -1 beim verlieren und 0 bei unentschieden
        return returnValue;
    }

    private static int simulation2(GameBoard kiBoard, int anfangsReihe) {

        int row = 0, column = 0, returnValue = 0;
        boolean spieler = false, anfang = true;
        while (kiBoard.checkWin2(column, row) == 0) {

            if (spieler) {
                //Simulation Spieler Zug
                int newColumn = (int) ((Math.random() * kiBoard.getWidth()) + 1);
                int newRow = kiBoard.insertChip('O', newColumn);
                if (row != -1) {
                    spieler = !spieler;
                    row = newRow;
                    column = newColumn - 1;
                    if (kiBoard.checkWin2(column, row) == -1) {
                        returnValue = -1;
                    }
                }
            } else {
                //Simulation KI Zug
                int newColumn;
                if (anfang == true) { //Erster Zug bestimmt durch die Anfangs Reihe
                    newColumn = anfangsReihe;
                    anfang = false;
                } else {
                    newColumn = (int) ((Math.random() * kiBoard.getWidth()) + 1);
                }
                int newRow = kiBoard.insertChip('X', newColumn);
                if (row != -1) {
                    spieler = !spieler;
                    row = newRow;
                    column = newColumn - 1;
                    if (kiBoard.checkWin2(column, row) == 1) {
                        returnValue = 1;
                    }
                }
            }
        }
        //Übergibt 1 beim gewinnen, -1 beim verlieren und 0 bei unentschieden
        return returnValue;
    }
}