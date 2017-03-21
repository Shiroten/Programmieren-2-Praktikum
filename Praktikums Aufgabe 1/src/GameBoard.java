/**
 * Created by tillm on 21.03.2017.
 */
public class GameBoard {
    private int width, heigth;
    private char[][] board;

    char empty = '.', player1 = 'X', player2 = 'O';

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public GameBoard(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
        setBoard(width, heigth);
    }

    public void printBoard() {
        for (int i = 0; i < heigth +4; i++) {
            for (int j = 0; j < width; j++) {
                if(i == 0 || i == heigth +3)
                    System.out.print(j+1);
                else if(i == 1)
                    System.out.print('v');
                else if(i == heigth +2)
                    System.out.print('-');
                else
                    System.out.print(board[j][i-2]);
            }
            System.out.println();
        }
    }

    public void setBoard(int width, int heigth) {
        this.board = new char[width][heigth];

        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                board[j][i] = empty;
            }
        }
    }

    public boolean insertChip(char playerChip, int column){
        int i = getDrop(column);
        if(i == -1)
            return false;
        else{
            board[column][i] = playerChip;
            return true;
        }
    }

    public int getDrop(int column){
        if(column > width)
            return -1;
        for (int i = heigth - 1; i >= 0; i--) {
            if(board[column][i] == empty)
                return i;
        }
        return -1;
    }

    public boolean checkRow(){
        for(int i = 0; i < heigth; i++){
            int counter = 0;

            for(int j = 0; j < width - 1; j++){
                if(board[j][i] == board[j+1][i] && board[j][i] != empty)
                    counter++;
                else
                    counter = 0;
                if(counter == 3)
                    return true;
            }
        }
        return false;
    }

    public boolean checkColumn(){
        for(int i = 0; i < width; i++){
            int counter = 0;

            for(int j = 0; j < heigth - 1; j++){
                if(board[i][j] == board[i][j+1] && board[i][j] != empty)
                    counter++;
                else
                    counter = 0;

                if(counter == 3)
                    return true;
            }
        }
        return false;
    }
}
