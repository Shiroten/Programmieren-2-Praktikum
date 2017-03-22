import java.io.IOException;

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

        GameBoard board = new GameBoard(5, 5);
        int row;
        board.printBoard();

        board.insertChip('O', 1);
        board.insertChip('O', 1);
        board.insertChip('O', 1);
        row = board.insertChip('X', 1);

        board.insertChip('O', 2);
        board.insertChip('O', 3);
        row = board.insertChip('O', 4);



        board.printBoard();
        if(board.checkWin(0, row))
            System.out.println("Gewonnen!" + row);

        //testInsert (field, emptyPlaceholder);

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
