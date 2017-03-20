
public class engine {
    public static char[][] createField() {

        char field[][] = new char[7][7];
        return field;
    }

    public static char[][] fillField(char field[][], char character) {

        //i=Zeilen, j=Reihen
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = character;
            }
        }

        return field;
    }

    public static char[][] insert(char field[][], int position, char character,
                                  char emptyPlaceholder) {

        for (int i = 0; i < 7; i++) {
            if (field[6 - i][position] == emptyPlaceholder) {
                field[6 - i][position] = character;
                return field;
            }
        }
        //Error Meldung
        System.out.println("Voll");
        return field;
    }
}