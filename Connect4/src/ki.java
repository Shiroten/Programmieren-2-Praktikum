/**
 * Created by Shiroten on 23.03.2017.
 */
public class ki {

    public static int zug(GameBoard board, int kiStrength) {

        int returnValue = 0;
        int durchläufe;
        switch (kiStrength) {

            case 0:
                //Random Number KI
                returnValue = (int) ((Math.random() * board.getWidth()));
                break;

            case 1:
                //Monta Carlo mit 300
                durchläufe = 300;
                returnValue = monteCarlo(board, durchläufe);
                break;

            case 2:
                //Monta Carlo mit 3000
                durchläufe = 3000;
                returnValue = monteCarlo(board, durchläufe);
                break;

            case 3:
                //Monta Carlo mit 30000
                durchläufe = 30000;
                returnValue = monteCarlo(board, durchläufe);
                break;

        }
        return returnValue;
    }

    private static int monteCarlo(GameBoard board, int durchläufe) {
        //Monte Carlo
        int returnValue = 0;
        int gewinnAnzahl;
        int spalten = board.getWidth();
        int[] zugMoeglichkeiten = new int[spalten]; //Ermittlung der Anzahl der Zugmöglichkeiten

        for (int i = 0; i < spalten; i++) {

            //Versuche resetten und neues GameBoard erstellen
            gewinnAnzahl = 0;
            GameBoard kiBoard = board.copyBoard();

            //Berechnung, wie oft gewonnen wurde
            for (int j = 0; j < durchläufe; j++) {
                if (simulation.doIt(kiBoard, i + 1) == -1) {
                    gewinnAnzahl++;
                }
            }
            zugMoeglichkeiten[i] = gewinnAnzahl; //Abspeichern der einzelnen "Gewinnchancen"

            float[] gewinnchance = new float[spalten];
            gewinnchance[i] = (float) gewinnAnzahl / durchläufe * 100;

            //Ausgabe in der Konsole zum debugen
            System.out.printf("%d. Gewinnchance: %2.2f%%%n", (i + 1), gewinnchance[i]);

        }

        //Bestimmen des Besten Zugs
        int besterZug = 0;
        int besterZugWahrscheinlichkeit = 0;

        for (int i = 0; i < spalten; i++) { //Durchgehen des ganzen Array und finden des Maximum
            if (besterZugWahrscheinlichkeit < zugMoeglichkeiten[i]) {
                besterZugWahrscheinlichkeit = zugMoeglichkeiten[i];
                besterZug = i + 1;
            }
        }
        System.out.printf("%nBester Zug mit Gewinnchance %2.2f%%: %d%n", (float) besterZugWahrscheinlichkeit / durchläufe * 100, (besterZug + 1));
        returnValue = besterZug;

        return returnValue;
    }
}



