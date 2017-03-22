import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException {
        /*char emptyPlaceholder = '+';

        char field[][] = engine.createField();
        field = engine.fillField(field, emptyPlaceholder);
        gui.draw(field);

        for (int i = 0; i < 49; i++) {
            field = engine.insert(field, gui.userInteraction(1), 'x', emptyPlaceholder);
            gui.draw(field);
            field = engine.insert(field, gui.userInteraction(2), 'o', emptyPlaceholder);
            gui.draw(field);
        }*/

        Scanner reader = new Scanner(System.in);  // Reading from System.in


        boolean input = false;
        int width = 0, heigth = 0;
        while(!input){
            System.out.println("Bitte die Breite des Spielfelds eingeben:");
            try{
                width = reader.nextInt();
                input = true;
            }
            catch(Exception e){
                reader.nextLine();
                System.out.println("Bitte nur Zahlen eingeben");
            }
        }
        input = false;
        while(!input){
            System.out.println("Bitte die Höhe des Spielfelds eingeben:");
            try{
                heigth = reader.nextInt();
                input = true;
            }
            catch(Exception e){
                reader.nextLine();
                System.out.println("Bitte nur Zahlen eingeben");
            }
        }

        GameBoard board = new GameBoard(width, heigth);

        int row = 0, column = 0;
        boolean spieler = true;
        while(!board.checkWin(column, row)){
            board.printBoard();
            if(spieler){
                try{
                    System.out.println("Spieler 1, gib die gewünschte Reihe an: ");
                    int newColumn = reader.nextInt();
                    int newRow = board.insertChip('O', newColumn);
                    if(newRow == -1)
                        System.out.println("Bitte richtige Reihe angeben.");
                    else {
                        spieler = !spieler;
                        row = newRow;
                        column = newColumn -1;
                        if(board.checkWin(column, row)) {
                            board.printBoard();
                            System.out.println("Sieg für Spieler 1!");
                        }
                    }
                }
                catch(Exception e){
                    reader.nextLine();
                    System.out.println("Bitte nur Zahlen eingeben!");
                }
            }
            else{
                try{
                    System.out.println("Spieler 2, gib die gewünschte Reihe an: ");
                    int newColumn = reader.nextInt();
                    int newRow = board.insertChip('X', newColumn);
                    if(newRow == -1)
                        System.out.println("Bitte richtige Reihe angeben.");
                    else {
                        spieler = !spieler;
                        row = newRow;
                        column = newColumn -1;
                        if(board.checkWin(column,row)) {
                            board.printBoard();
                            System.out.println("Sieg für Spieler 2!");
                        }
                    }
                }
                catch(Exception e){
                    reader.nextLine();
                    System.out.println("Bitte nur Zahlen eingeben!");
                }
            }
        }

    }

    public static void testInsert(char field[][], char emptyPlaceholder) {

        for (int i = 0; i < 7; i++) {
            field = engine.insert(field, i, 'a', emptyPlaceholder);
            gui.draw(field);
            field = engine.insert(field, i, 'c', emptyPlaceholder);
            gui.draw(field);
            field = engine.insert(field, i, 'b', emptyPlaceholder);
            gui.draw(field);
            field = engine.insert(field, i, 'o', emptyPlaceholder);
            gui.draw(field);
            field = engine.insert(field, i, 'x', emptyPlaceholder);
            gui.draw(field);
            field = engine.insert(field, i, 'o', emptyPlaceholder);
            gui.draw(field);
            field = engine.insert(field, i, 'x', emptyPlaceholder);
            gui.draw(field);
            field = engine.insert(field, i, 'o', emptyPlaceholder);
            gui.draw(field);

        }
    }

}
