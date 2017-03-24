import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException {

        String aufrufParameter = "";
        if (args.length > 0) {
            aufrufParameter = args[0];
        }
        Scanner reader = new Scanner(System.in);  // Reading from System.in


        boolean input = false;
        int width = 0, heigth = 0;
        while (!input) {
            System.out.println("Bitte die Breite des Spielfelds eingeben:");
            try {
                width = reader.nextInt();
                input = true;
            } catch (Exception e) {
                reader.nextLine();
                System.out.println("Bitte nur Zahlen eingeben");
            }
        }
        input = false;
        while (!input) {
            System.out.println("Bitte die Höhe des Spielfelds eingeben:");
            try {
                heigth = reader.nextInt();
                input = true;
            } catch (Exception e) {
                reader.nextLine();
                System.out.println("Bitte nur Zahlen eingeben");
            }
        }

        GameBoard board = new GameBoard(width, heigth);

        int row = 0, column = 0;
        boolean spieler = true;
        while (!board.checkWin(column, row)) {
            board.printBoard();
            if (spieler) {
                try {
                    System.out.println("Spieler 1, gib die gewünschte Reihe an: ");
                    int newColumn = reader.nextInt();
                    int newRow = board.insertChip('O', newColumn);
                    if (newRow == -1)
                        System.out.println("Bitte richtige Reihe angeben.");
                    else {
                        spieler = !spieler;
                        row = newRow;
                        column = newColumn - 1;
                        if (board.checkWin(column, row)) {
                            board.printBoard();
                            System.out.println("Sieg für Spieler 1!");
                        }
                    }
                } catch (Exception e) {
                    reader.nextLine();
                    System.out.println("Bitte nur Zahlen eingeben!");
                }
            } else {
                try {
                    int newColumn;

                    //newColumn = kiSwitch (aufrufParameter, width, board);

                    //Überprüfung auf KI Stufe 0,1,2.....
                    switch (aufrufParameter) {

                        case "0":
                            newColumn = ki.zug(board, 0);
                            break;

                        case "1":
                            newColumn = ki.zug(board, 1);
                            break;

                        default:
                            System.out.println("Spieler 2, gib die gewünschte Reihe an: ");
                            newColumn = reader.nextInt();
                            break;
                    }

                    int newRow = board.insertChip('X', newColumn);
                    if (newRow == -1)
                        System.out.println("Bitte richtige Reihe angeben.");
                    else {
                        spieler = !spieler;
                        row = newRow;
                        column = newColumn - 1;
                        if (board.checkWin(column, row)) {
                            board.printBoard();
                            System.out.println("Sieg für Spieler 2!");
                        }
                    }
                } catch (Exception e) {
                    reader.nextLine();
                    System.out.println("Bitte nur Zahlen eingeben!");
                }
            }
        }
    }

    private static int userInteraction(int Spieler, int width) {

        Scanner userReader = new Scanner(System.in);
        int returnValue = 0;

        boolean input = false;
        while (!input) {
            System.out.printf("Spieler %d, gib die gewünschte Reihe an: ", Spieler);
            try {
                returnValue = userReader.nextInt();
                input = true;
            } catch (Exception e) {
                userReader.nextLine();
                System.out.println("Bitte nur Zahlen eingeben");
            }
        }
        userReader.close();
        return returnValue;
    }

    private static int kiSwitch(String aufrufParameter, int width, GameBoard board) {

        int newColumn;
        //Überprüfung auf KI Stufe 0,1,2.....
        switch (aufrufParameter) {

            case "0":
                newColumn = ki.zug(board, 0);
                break;

            case "1":
                newColumn = ki.zug(board, 1);
                break;

            default:
                newColumn = userInteraction(1, width);
                break;
        }
        return newColumn;
    }

}
