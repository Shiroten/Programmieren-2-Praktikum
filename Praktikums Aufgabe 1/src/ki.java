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
                        if (simulation.doIt(kiBoard, i + 1) == 1) {
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
}



