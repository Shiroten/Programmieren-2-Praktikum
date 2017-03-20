import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        char emptyPlaceholder = '+';

        char field[][] = engine.createField();
        field = engine.fillField(field, emptyPlaceholder);
        gui.draw(field);

        for (int i = 0; i < 49; i++) {
            field = engine.insert(field, gui.userInteraction(1), 'x', emptyPlaceholder);
            gui.draw(field);
            field = engine.insert(field, gui.userInteraction(2), 'o', emptyPlaceholder);
            gui.draw(field);
        }
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
