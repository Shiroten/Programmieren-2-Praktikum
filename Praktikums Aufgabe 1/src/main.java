import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException {

        //Abspeichern des Übergabeparameter
        String aufrufParameter = "";
        if (args.length > 0) {
            aufrufParameter = args[0];
        }

        Scanner reader = new Scanner(System.in);  // Reading from System.in

        boolean input = false;
        int width = 0, heigth = 0;

        //Ermittlung der Breites des Spielfelds
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

        //Ermittlung der Höhe des Spielfelds
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
        //board.fillBoard1();
        //board.printBoard();

        //Beginn des Spiels
        boolean spieler = board.getSpieler();
        while (board.checkStatus() == 0) {
            board.printBoard();
            if (spieler) {
                //Zug von Spieler 1
                board = spielZüge(aufrufParameter, board);
                spieler = board.getSpieler();

            } else {
                //Zug von Spieler 2
                board = spielZüge(aufrufParameter, board);
                spieler = board.getSpieler();
            }
        }
    }

    private static int userInteraction(int Spieler) {

        //Ermittlung der Column/Reihe/Spalte des Spielsteins
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
        //userReader.close();
        return returnValue - 1;
    }

    private static int kiSwitch(String aufrufParameter, GameBoard board) {

        int newColumn;
        //Überprüfung auf KI Stufe 0,1,2.....
        switch (aufrufParameter) {

            case "0":
                newColumn = ki.zug(board, 0);
                break;

            case "1":
                newColumn = ki.zug(board, 1);
                break;

            case "2":
                newColumn = ki.zug(board, 2);
                break;

            case "3":
                newColumn = ki.zug(board, 3);
                break;

            default:
                newColumn = userInteraction(2); //Aufruf für Spieler 2
                break;
        }
        return newColumn;
    }

    private static GameBoard spielZüge(String aufrufParameter, GameBoard board) {

        int newColumn;
        char chip;
        String winMessage;

        if (board.getSpieler()) {//Bei true = Spieler 1, bei false = Spieler 2
            newColumn = userInteraction(1); //Aufruf für Spieler 1
            chip = 'O';
            winMessage = "Sieg für Spieler 1";
        } else {
            newColumn = kiSwitch(aufrufParameter, board);//Überprüfung auf Spieler oder KI
            chip = 'X';
            winMessage = "Sieg für Spieler 2";
        }

        int newRow; //Überprüfung auf richtige Zahlen für Column
        if (newColumn >= board.getWidth() || newColumn < 0) {
            newRow = -1;
        } else {
            newRow = board.insertChip(chip, newColumn);
        }

        if (newRow == -1) //Überprüfung auf Fehlerfall Zuhoch oder falsche Reihe
            System.out.println("Bitte richtige Reihe angeben.");
        else {
            board.setSpieler(!board.getSpieler()); //boolean Spieler wechseln
            board.setRow(newRow); //Übergabe der Parameter des letzten Spielsteins
            board.setColumn(newColumn);

            //Überprüfung auf Spielende
            if (board.checkStatus() == 1) {
                board.printBoard();
                System.out.println(winMessage);
            }
        }
        return board;
    }
}
