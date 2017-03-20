import java.io.IOException;

public class gui {

    public static void draw(char field[][]) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(field[i][j] + " ");
                if (j == 6) {
                    System.out.println();
                }
            }
        }
        System.out.println();
    }

    public static int userInteraction(int Spieler) throws IOException {

        char input;
        boolean wrongAnswer = true;
        do {
            System.out.print("Spieler " + Spieler + ": Bitte geben eine Reihe an von 1-7: ");
            input = (char) System.in.read();

            //input flush überarbeiten
            char clear1 = (char) System.in.read();
            //char clear2 = (char) System.in.read();

            if (input >= '1' && input <= '7') {
                wrongAnswer = false;
            } else {
                System.out.println("Falsche Eingabe. Bitte probieren Sie es erneut.");
            }

        } while (wrongAnswer);

        input = (char) (input - '1');
        return (int) input;
    }
}
