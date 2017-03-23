/**
 * Created by tillm on 21.03.2017.
 */
public class GameBoard {
    private int width, heigth;
    private char[][] board;

    private char empty = '.', player1 = 'X', player2 = 'O';

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public char[][] getBoard() {
        return board;
    }

    public GameBoard copyBoard() {
        GameBoard copyBoard = new GameBoard(this.width, this.heigth);
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                copyBoard.board[j][i] = this.board[j][i];
            }
        }
        return copyBoard;
    }

    //Konstruktor
    public GameBoard(int width, int heigth) {
        this.width = width;
        this.heigth = heigth;
        setBoard(width, heigth);
    }

    //Gibt das aktuelle Spielbrett auf der Konsole aus
    //Über und unter dem Brett werden die Spaltenzahlen gedruckt, sowie Trennlinien zur besseren Übersicht
    public void printBoard() {

        System.out.println();
        for (int i = 0; i < heigth + 4; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == heigth + 3)
                    System.out.printf("%03d|", j + 1);
                else if (i == 1)
                    System.out.print("vvv|");
                else if (i == heigth + 2)
                    System.out.print("---|");
                else
                    System.out.printf(" %c |", board[j][i - 2]);
            }
            System.out.println();
        }
        System.out.println();
    }

    //Initialisiert das Brett mit leeren Feldern
    private void setBoard(int width, int heigth) {
        this.board = new char[width][heigth];

        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                board[j][i] = empty;
            }
        }
    }

    //Ein Spielstein wird in das Brett eingeworfen und an der richtigen Stelle eingefügt
    //Achtung: Column ist eins- und nicht nullbasiert
    public int insertChip(char playerChip, int column) {
        int i = getDrop(column - 1);
        if (i != -1)
            board[column - 1][i] = playerChip;
        return i;
    }

    //Findet die Zeile raus, in der ein Spielstein landet, wenn er in eine Spalte geworfen wird
    private int getDrop(int column) {
        if (column > width)
            return -1;
        for (int i = heigth - 1; i >= 0; i--) {
            if (board[column][i] == empty)
                return i;
        }
        return -1;
    }

    private boolean checkRow(int row) {
        int counter = 0;

        for (int j = 0; j < width - 1; j++) {
            if (board[j][row] == board[j + 1][row] && board[j][row] != empty)
                counter++;
            else
                counter = 0;
            if (counter == 3)
                return true;
        }
        return false;
    }

    private boolean checkColumn(int column) {
        int counter = 0;

        for (int j = 0; j < heigth - 1; j++) {
            if (board[column][j] == board[column][j + 1] && board[column][j] != empty)
                counter++;
            else
                counter = 0;

            if (counter == 3)
                return true;
        }
        return false;
    }

    private boolean checkDiagonal(int column, int row) {

        //Hier werden die oberen und unteren Limits berechnet. Grundsätzlich muss man nur bis zu Abstand drei zu einem
        //spezifizierten Stein suchen, da man dann vier in einer Reihe hat (mit dem Stein, ab dem man sucht)
        //Stößt man aber an die Grenze des Bretts, kann nur ab oder bis zu dieser gesucht werden.
        int columnMin = (column - 3 < 0 ? 0 : column - 3); //Läuft durch die Höhe
        int rowMin = (row - 3 < 0 ? 0 : row - 3); //Läuft durch die Breite
        int columnMax = (column + 2 >= heigth ? heigth - 1 : column + 2);
        int rowMax = (row + 2 >= width ? width - 1 : row + 2);

        //Erste Schleife geht von oben links nach unten rechts
        int counter = 0;
        for (int i = columnMin; i < columnMax; i++) {
            for (int j = rowMin; j < rowMax; j++) {
                if (board[j][i] == board[j + 1][i + 1] && board[j][i] != empty)
                    counter++;
                else
                    counter = 0;
                if (counter == 3)
                    return true;
            }
        }

        //Zweite Schleife geht von unten links noch oben rechts
        counter = 0;
        for (int i = columnMax; i > columnMin; i--) {
            for (int j = rowMin; j < rowMax; j++) {
                if (board[j][i] == board[j + 1][i - 1] && board[j][i] != empty)
                    counter++;
                else
                    counter = 0;
                if (counter == 3)
                    return true;
            }
        }

        return false;
    }

    public boolean checkWin(int column, int row) {
        return (checkColumn(column) || checkRow(row) || checkDiagonal(column, row));
    }
}
