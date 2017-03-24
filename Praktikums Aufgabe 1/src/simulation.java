/**
 * Created by Shiroten on 24.03.2017.
 */
public class simulation {

    public static int doIt(GameBoard kiBoard, int anfangsReihe) {

        kiBoard.setRow(0);
        kiBoard.setColumn(0);
        int returnValue = 0;

        boolean spieler = kiBoard.getSpieler();
        kiBoard.setAnfang(true);

        while (!kiBoard.checkWin(kiBoard.getColumn(), kiBoard.getRow())) {
            kiBoard.printBoard();
            if (spieler) {
                spieler = Züge(anfangsReihe, kiBoard).getSpieler();
                returnValue = kiBoard.checkWin2(kiBoard.getColumn(), kiBoard.getRow());
            } else {
                spieler = Züge(anfangsReihe, kiBoard).getSpieler();
                returnValue = kiBoard.checkWin2(kiBoard.getColumn(), kiBoard.getRow());
            }
        }
        return returnValue;
    }

    private static GameBoard Züge(int anfangsReihe, GameBoard kiBoard) {

        int returnValue = 0;
        int newColumn;
        char chip;

        if (kiBoard.getSpieler()) {
            chip = 'O';
            newColumn = (int) ((Math.random() * kiBoard.getWidth()) + 1);
        } else {
            chip = 'X';
            if (kiBoard.getAnfang()) { //Erster Zug bestimmt durch die Anfangs Reihe
                newColumn = anfangsReihe;
                kiBoard.setAnfang(false);
            } else {
                newColumn = (int) ((Math.random() * kiBoard.getWidth()) + 1);
            }
        }
        int newRow = kiBoard.insertChip(chip, newColumn);
        if (newRow != -1) {
            kiBoard.setSpieler(!kiBoard.getSpieler());
            kiBoard.setRow(newRow);
            kiBoard.setColumn(newColumn - 1);
        }
        return kiBoard;
    }
}
