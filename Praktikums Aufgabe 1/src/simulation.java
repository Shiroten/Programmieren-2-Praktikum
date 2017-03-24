/**
 * Created by Shiroten on 24.03.2017.
 */
public class simulation {

    public static int doIt(GameBoard kiBoard, int anfangsReihe) {

        GameBoard simuBoard = kiBoard.copyBoard();
        simuBoard.setSpieler(false);
        simuBoard.setRow(0);
        simuBoard.setColumn(0);
        int returnValue = 0;

        boolean spieler = simuBoard.getSpieler();
        simuBoard.setAnfang(true);

        while (simuBoard.checkWin2(simuBoard.getColumn(), simuBoard.getRow())==0) {
            if (spieler) {
                simuBoard = Züge(anfangsReihe, simuBoard);
                spieler = simuBoard.getSpieler();
                returnValue = simuBoard.checkWin2(simuBoard.getColumn(), simuBoard.getRow());
            } else {
                simuBoard = Züge(anfangsReihe, simuBoard);
                spieler = simuBoard.getSpieler();
                returnValue = simuBoard.checkWin2(simuBoard.getColumn(), simuBoard.getRow());
            }
        }
        return returnValue;
    }

    private static GameBoard Züge(int anfangsReihe, GameBoard simuBoard) {

        int returnValue = 0;
        int newColumn;
        char chip;

        if (simuBoard.getSpieler()) {
            chip = 'O';
            newColumn = (int) ((Math.random() * simuBoard.getWidth()) + 1);
        } else {
            chip = 'X';
            if (simuBoard.getAnfang()) { //Erster Zug bestimmt durch die Anfangs Reihe
                newColumn = anfangsReihe;
                simuBoard.setAnfang(false);
            } else {
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
