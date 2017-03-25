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

        boolean spieler = simuBoard.getSpieler();
        //Simulation des Spielablaufs
        while (simuBoard.checkWin2() == 0) {
            if (spieler) {
                simuBoard = Züge(anfangsReihe, simuBoard);
                spieler = simuBoard.getSpieler(); //Wechseln des Spielers über boolean spieler
                returnValue = simuBoard.checkWin2();
            } else {
                simuBoard = Züge(anfangsReihe, simuBoard);
                spieler = simuBoard.getSpieler();
                returnValue = simuBoard.checkWin2();
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
            newColumn = (int) ((Math.random() * simuBoard.getWidth()) + 1);
        } else {
            chip = 'X';
            if (simuBoard.getAnfang()) { //Erster Zug bestimmt durch die Anfangs Reihe
                newColumn = anfangsReihe;
                simuBoard.setAnfang(false);
            } else { //Rest der Ki Züge bestimmt durch Zufall
                newColumn = (int) ((Math.random() * simuBoard.getWidth()) + 1);
            }
        }
        int newRow = simuBoard.insertChip(chip, newColumn);
        if (newRow != -1) {
            simuBoard.setSpieler(!simuBoard.getSpieler());
            simuBoard.setRow(newRow);
            simuBoard.setColumn(newColumn - 1);
        }
        return simuBoard;
    }
}
