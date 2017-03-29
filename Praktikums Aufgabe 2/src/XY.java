import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tillm on 28.03.2017.
 */
public class XY {
    int x;
    int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public XY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public XY randomMove(){
        int random = (int) Math.round(Math.random() * 7);
        XY newPosition;

        switch(random) {
            case 0: return newPosition = new XY(x+1, y+1);
            case 1: return newPosition = new XY(x+1, y);
            case 2: return newPosition = new XY(x, y+1);
            case 3: return newPosition = new XY(x-1, y+1);
            case 4: return newPosition = new XY(x-1, y);
            case 5: return newPosition = new XY(x-1, y-1);
            case 6: return newPosition = new XY(x, y-1);
            case 7: return newPosition = new XY(x+1, y-1);
            default: return newPosition = new XY(0, 0);
        }
    }

}
