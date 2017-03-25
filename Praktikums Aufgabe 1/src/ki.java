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
                int spalten = board.getWidth();
                int[] zugMoeglichkeiten = new int[spalten]; //Ermittlung der Anzahl der Zugmöglichkeiten

                for (int i = 0; i < spalten; i++) {

                    //Versuche resetten und neues GameBoard erstellen
                    gewinnAnzahl = 0;
                    GameBoard kiBoard = board.copyBoard();

                    //Berechnung, wie oft gewonnen wurde
                    int durchläufe = 2000;
                    for (int j = 0; j < durchläufe; j++) {
                        if (simulation.doIt(kiBoard, i + 1) == -1) {
                            gewinnAnzahl++;
                        }
                    }
                    zugMoeglichkeiten[i] = gewinnAnzahl; //Abspeichern der einzelnen "Gewinnchancen"

                    //Ausgabe in der Konsole zum debugen
                    float gewinnchance = (float)gewinnAnzahl/durchläufe*100;
                    System.out.printf("Gewinnchance für die %d. Reihe %2.2f%%%n",(i+1), gewinnchance);

                }

                //Bestimmen des Besten Zugs
                int besterZug = 0;
                int besterZugWahrscheinlichkeit = 0;

                for (int i = 0; i < spalten; i++) { //Durchgehen des ganzen Array und finden des Maximum
                    if (besterZugWahrscheinlichkeit < zugMoeglichkeiten[i]) {
                        besterZugWahrscheinlichkeit = zugMoeglichkeiten[i];
                        besterZug = i;
                    }
                }
                System.out.println("Besterzug: " + (besterZug + 1));
                returnValue = besterZug + 1;
        }
        return returnValue;
    }
}



