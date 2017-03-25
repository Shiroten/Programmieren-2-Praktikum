/**
 * Created by tillm on 21.03.2017.
 */
public class GameBoard {

    //Column = Spalte, X
    //Row    = Zeile,  y

    private int width, heigth; //Größe des Boards
    private char[][] board;
    //Raster XY beginnt links Oben mit 00 und geht nach rechts unten mit MaxMax
    //Zeile 0 Reihe 0, Zeile 0 Reihe 1, Zeile 0 Reihe 2
    //Zeile 1 Reihe 0, Zeile 1 Reihe 1, Zeile 1 Reihe 2
    //Zeile 2 Reihe 0, Zeile 2 Reihe 1, Zeile 2 Reihe 2

    private boolean spieler = true; //Aktueller Spieler, true = spieler 1, false = spieler 2
    private int row = 0, column = 0; //Position des letzten Steins
    private boolean anfang = true; //Boolen für Simulation, zur unterscheidung zwischen Anfang und rest des Spiels
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

    public boolean getSpieler() {
        return spieler;
    }

    public void setSpieler(boolean spieler) {
        this.spieler = spieler;
    }

    public boolean getAnfang() {
        return anfang;
    }

    public void setAnfang(boolean anfang) {
        this.anfang = anfang;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }


    public GameBoard copyBoard() {
        GameBoard copyBoard = new GameBoard(this.width, this.heigth);
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                copyBoard.board[j][i] = this.board[j][i];
            }
        }
        copyBoard.setSpieler(this.spieler);
        copyBoard.setRow(this.row);
        copyBoard.setColumn(this.column);
        copyBoard.setAnfang(this.anfang);

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
        int i = getDrop(column);
        if (i != -1)
            board[column][i] = playerChip;
        return i;
    }

    //Findet die Zeile raus, in der ein Spielstein landet, wenn er in eine Spalte geworfen wird
    private int getDrop(int column) {
        if (column > width)
            return -1; //Übergabe von -1 beim Fehlerfall Zeilenhöhe größer oder Reihe nicht vorhanden
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
        int columnMax = (column + 3 > heigth - 1 ? heigth -1  : column + 3);
        int rowMax = (row + 3 > width -1 ? width - 1 : row + 3);

        int leftXOffset = row - rowMin;
        int leftYOffset = column - columnMin;

        int leftMin = (leftYOffset < leftXOffset ? leftYOffset : leftXOffset);
        int rightMin = (columnMax - column < rowMax - row ? columnMax - column : rowMax -row);

        int leftRow = row - leftMin;
        int leftColumn =column - leftMin;

        System.out.println("Column: " + column + " Row: " + row);

        //Erste Schleife geht von oben links nach unten rechts
        int counter = 0;
        for(int i = 0; i < leftMin + rightMin; i++){
            //System.out.println("Field One:" + board[leftColumn + i][leftRow +i] + " Field Two:" + board[leftColumn + i + 1][leftRow + i + 1]);
            //System.out.println("X: " + (leftColumn+i) + " Y " + (leftRow+i));
            if(board[leftColumn + i][leftRow +i] == board[leftColumn + i + 1][leftRow + i + 1] && board[leftColumn + i][leftRow +i] != empty)
                counter++;
            else
                counter = 0;
            if (counter == 3)
                return true;
        }

        leftMin = (columnMax - column < row - rowMin ? columnMax - column : row - rowMin);
        rightMin = (rowMax - row < column - columnMin ? rowMax - row : column - columnMin);

        leftRow = row - leftMin;
        leftColumn = column + leftMin;
        for(int i = 0; i < leftMin + rightMin; i++){
            //System.out.println("Field One:" + board[leftColumn - i][leftRow +i] + " Field Two:" + board[leftColumn - i - 1][leftRow + i + 1]);
            //System.out.println("X: " + (leftColumn+i) + " Y " + (leftRow+i));
            if(board[leftColumn - i][leftRow +i] == board[leftColumn - i - 1][leftRow + i + 1] && board[leftColumn - i][leftRow +i] != empty)
                counter++;
            else
                counter = 0;
            if (counter == 3)
                return true;
        }

        return false;
    }

    private boolean checkFull(){
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                if(board[j][i] == empty)
                    return false;
            }
        }
        return true;
    }

    //Gibt 1 zurück, wenn der aktuelle Spieler gewonnen hat, 2 wenn das Feld voll ist und 0, falls
    public int checkStatus() {
        if( (checkColumn(column) || checkRow(row) || checkDiagonal(column, row)))
            return 1;
        else if (checkFull())
            return 2;
        else
            return 0;
    }

    //Für ein 7x7-Feld, erzeugt Diagonal-Muster
    public void fillBoard1(){
        insertChip(player1, 0);
        insertChip(player2, 1);
        insertChip(player1, 2);
        insertChip(player2, 3);
        insertChip(player1, 4);
        insertChip(player2, 5);
        insertChip(player1, 6);
        insertChip(player2, 0);
        insertChip(player1, 1);
        insertChip(player2, 2);
        insertChip(player1, 3);
        insertChip(player2, 4);
        insertChip(player1, 5);
        insertChip(player2, 6);
        insertChip(player1, 0);
        insertChip(player2, 1);
        insertChip(player1, 2);
        insertChip(player2, 3);
        insertChip(player1, 4);
        insertChip(player2, 5);
        insertChip(player1, 6);
    }
}
