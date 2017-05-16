package de.hsa.games.fatsquirrel.tests;

import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.XYsupport;
import org.junit.Test;

import static org.junit.Assert.*;

public class XYsupportTest {
    private XY test1 = new XY(10, 10);
    private XY test2 = new XY(20, 20);
    private XY test3 = new XY(9, -12);
    private XY test4 = new XY(-13, 17);
    private XY testVectorNormalized1 = new XY(5, 3);
    private XY testVectorNormalized2 = new XY(5, 2);


    @Test
    public void oppositeVector() throws Exception {
        boolean firstTest = XYsupport.oppositeVector(test3).equals(new XY(-9, 12));
        boolean secondTest = XYsupport.oppositeVector(test4).equals(new XY(13, -17));
        assertTrue(firstTest && secondTest);
    }

    @Test
    public void rotate() throws Exception {
        XY rotatedOneTimeClockwise = XYsupport.rotate(XYsupport.Rotation.clockwise, XY.UP, 1);
        XY rotatedThreeTimeClockwise = XYsupport.rotate(XYsupport.Rotation.clockwise, XY.UP, 3);
        XY rotatedFiveTimeClockwise = XYsupport.rotate(XYsupport.Rotation.clockwise, XY.UP, 5);
        XY rotatedOneTimeAntiClockwise = XYsupport.rotate(XYsupport.Rotation.anticlockwise, XY.UP, 1);
        XY rotatedThreeTimeAntiClockwise = XYsupport.rotate(XYsupport.Rotation.anticlockwise, XY.UP, 3);
        XY rotatedFiveTimeAntiClockwise = XYsupport.rotate(XYsupport.Rotation.anticlockwise, XY.UP, 5);

        boolean firstTest = rotatedOneTimeClockwise.equals(XY.RIGHT_UP) && rotatedOneTimeAntiClockwise.equals(XY.LEFT_UP);
        boolean secondTest = rotatedThreeTimeClockwise.equals(XY.RIGHT_DOWN) && rotatedThreeTimeAntiClockwise.equals(XY.LEFT_DOWN);
        boolean thirdTest = rotatedFiveTimeClockwise.equals(XY.LEFT_DOWN) && rotatedFiveTimeAntiClockwise.equals((XY.RIGHT_DOWN));
        assertTrue(firstTest && secondTest && thirdTest);
    }

    @Test
    public void isInRange() throws Exception {
    }

    @Test
    public void normalizedVector() throws Exception {
        XY test1normalized = XYsupport.normalizedVector(test1);
        XY test2normalized = XYsupport.normalizedVector(test2);
        XY test3normalized = XYsupport.normalizedVector(testVectorNormalized1);
        XY test4normalized = XYsupport.normalizedVector(testVectorNormalized2);

        System.out.println(test1normalized);
        System.out.println(test2normalized);
        System.out.println(test3normalized);
        System.out.println(test4normalized);

        boolean firstTest = test1normalized.equals(new XY(1, 1));
        boolean secondTest = test2normalized.equals(new XY(1, 1));
        boolean thirdTest = test3normalized.equals(new XY(1, 1));
        boolean fourthTest = test4normalized.equals(new XY(1, 0));
        assertTrue(firstTest && secondTest && thirdTest && fourthTest);
    }

}