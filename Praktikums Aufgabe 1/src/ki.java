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
                int[] zugMoeglichkeiten = new int[Reihen]; //Ermittlung der Anzahl der Zugmöglichkeiten

                for (int i = 0; i < Reihen; i++) {

                    //Versuche resetten und neues GameBoard erstellen
                    gewinnAnzahl = 0;
                    GameBoard kiBoard = board.copyBoard();

                    //Berechnung, wie oft gewonnen wurde
                    for (int j = 0; j < 1000000; j++) {
                        if (simulation.doIt(kiBoard, i + 1) == 1) {
                            gewinnAnzahl++;
                        }
                    }
                    zugMoeglichkeiten[i] = gewinnAnzahl; //Abspeichern der einzelnen "Gewinnchancen"

                    //Ausgabe in der Konsole zum debugen
                    System.out.println(i + ". gewinnAnzahl: " + gewinnAnzahl);

                }

                //Bestimmen des Besten Zugs
                int besterZug = 0;
                int besterZugWahrscheinlichkeit = 0;

                for (int i = 0; i < Reihen; i++) { //Durchgehen des ganzen Array und finden des Maximum
                    if (besterZugWahrscheinlichkeit < zugMoeglichkeiten[i]) {
                        besterZugWahrscheinlichkeit = zugMoeglichkeiten[i];
                        besterZug = i;
                    }
                    System.out.println(zugMoeglichkeiten[i]); //Debug Ausgabe
                }
                System.out.println("besterzug: " + (besterZug + 1));
                returnValue = besterZug + 1;
        }
        return returnValue;
    }
}



