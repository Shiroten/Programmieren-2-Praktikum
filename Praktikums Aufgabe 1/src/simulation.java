/**
 * Created by Shiroten on 24.03.2017.
 */
public class simulation {

    public static int doIt(GameBoard kiBoard, int anfangsReihe) {

        //Erzeugen eines separaten GameBoards für die Simulation
        GameBoard simuBoard = kiBoard.copyBoard();
        simuBoard.setSpieler(false);
        simuBoard.setAnfang(true);

        int returnValue = 0;
        int winStatus;
        boolean spieler = simuBoard.getSpieler();

        //Simulation des Spielablaufs
        while (!(simuBoard.checkStatus() == 1 || simuBoard.checkStatus() == 2)) {
            if (spieler) {
                simuBoard = Züge(anfangsReihe, simuBoard);
                spieler = simuBoard.getSpieler(); //Wechseln des Spielers über boolean spieler
                winStatus = simuBoard.checkStatus();
                if(winStatus == 1 && simuBoard.getSpieler()){
                    returnValue = 1;
                }

            } else {
                simuBoard = Züge(anfangsReihe, simuBoard);
                spieler = simuBoard.getSpieler();
                winStatus = simuBoard.checkStatus();
                if(winStatus == 1 && simuBoard.getSpieler()){
                    returnValue = -1;
                }
            }
        }
        return returnValue;
    }

    private static GameBoard Züge(int anfangsReihe, GameBoard simuBoard) {

        int returnValue = 0;
        int newColumn; //Werte von 1-Max und nicht 0-Max-1
        char chip;

        if (simuBoard.getSpieler()) { //Spieler 1 ersetzt durch zufälige KI
            chip = 'O';
            newColumn = (int) ((Math.random() * simuBoard.getWidth()));
        } else {
            chip = 'X';
            if (simuBoard.getAnfang()) { //Erster Zug bestimmt durch die Anfangs Reihe
                newColumn = anfangsReihe;
                simuBoard.setAnfang(false);
            } else { //Rest der Ki Züge bestimmt durch Zufall
                newColumn = (int) ((Math.random() * simuBoard.getWidth()));
            }
        }
        int newRow; //Überprüfung auf richtige Zahlen für Column
        if (newColumn >= simuBoard.getWidth() || newColumn < 0) {
            newRow = -1;
        } else {
            newRow = simuBoard.insertChip(chip, newColumn);
        }
        if (newRow != -1) {
            simuBoard.setSpieler(!simuBoard.getSpieler());
            simuBoard.setRow(newRow);
            simuBoard.setColumn(newColumn);
        }
        return simuBoard;
    }
}
