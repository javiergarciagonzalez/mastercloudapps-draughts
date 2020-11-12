package usantatecla.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CoordinateTest {
    Coordinate originCoordinate;

    public CoordinateTest() {
        originCoordinate = new Coordinate(4,4);
    }

    @Test
    public void testGivenAValidStringWithCoordinatesWhenGettingItsIntanceThenItReturnsTheCoordinateInstace() {
        Coordinate validMinCoordinate = new Coordinate(0,0);
        String minValidCoordinateString = "11";
        assertEquals(validMinCoordinate, Coordinate.getInstance(minValidCoordinateString));

        Coordinate validMaxCoordinate = new Coordinate(7,7);
        String maxValidCoordinateString = "88";
        assertEquals(validMaxCoordinate, Coordinate.getInstance(maxValidCoordinateString));
    }

    @Test
    public void testGivenANotValidStringWhenGettingTheInstanceItReturnsNull() {
        String notValidCoordinateString = "invalid coordinates string format";
        assertEquals(null, Coordinate.getInstance(notValidCoordinateString));
    }

    @Test
    public void testGivenAOverflowStringForCoordinatesWhenGettingTheInstanceItReturnsNull() {
        String minOverflowString = "00";
        assertEquals(null, Coordinate.getInstance(minOverflowString));
        String maxOverflowString = "99";
        assertEquals(null, Coordinate.getInstance(maxOverflowString));
    }

    @Test
    public void testGivenACoordinateWhenGettingItsDirectionThenIsRightDirection() {

        assertNotEquals(null, originCoordinate.getDirection(new Coordinate(3,3)));
        assertEquals(Direction.SW, originCoordinate.getDirection(new Coordinate(3,3)));
        assertEquals(Direction.SE, originCoordinate.getDirection(new Coordinate(3,5)));
        assertEquals(Direction.NW, originCoordinate.getDirection(new Coordinate(5,3)));
        assertEquals(Direction.NE, originCoordinate.getDirection(new Coordinate(5,5)));
        assertNull(originCoordinate.getDirection(new Coordinate(4,5)));
    }

    @Test
    public void testGivenACoordinateWhenCheckingIsDiagonalThenIsCorrect() {
        assertTrue(originCoordinate.isOnDiagonal(new Coordinate(5,5)));
        assertTrue(originCoordinate.isOnDiagonal(new Coordinate(3,3)));
        assertTrue(originCoordinate.isOnDiagonal(new Coordinate(0,0)));
    }

    @Test
    public void testGivenACoordinateWhenCheckingIsDiagonalThenIsIncorrect() {
        assertFalse(originCoordinate.isOnDiagonal(new Coordinate(0,7)));
        assertFalse(originCoordinate.isOnDiagonal(new Coordinate(7,0)));
    }

    @Test
    public void testGivenACoordinateOnItsDiagonalWhenGettingItsDistanceThenItRetunsTheDistance() {
        assertEquals(4, originCoordinate.getDiagonalDistance(new Coordinate(0,0)));
        assertEquals(3, originCoordinate.getDiagonalDistance(new Coordinate(1,1)));
        assertEquals(2, originCoordinate.getDiagonalDistance(new Coordinate(6,6)));
        assertEquals(1, originCoordinate.getDiagonalDistance(new Coordinate(3,3)));
        assertEquals(6, originCoordinate.getDiagonalDistance(new Coordinate(10,10)));
    }

    @Test(expected = AssertionError.class)
    public void testGivenACoordinateOutOfItsDiagonaWhenGettingDistanceThenItReturnsAssertionFail() {
        assertEquals(1, originCoordinate.getDiagonalDistance(new Coordinate(0,7)));
    }

    @Test
    public void testGivenACoordinateWhenGettingTheCoordinateInBetweenThenIsCorrect() {
        assertEquals(new Coordinate(5,5), originCoordinate.getBetweenDiagonalCoordinate(new Coordinate(6,6)));
        assertEquals(new Coordinate(3,3), originCoordinate.getBetweenDiagonalCoordinate(new Coordinate(2,2)));
    }

    @Test(expected = AssertionError.class)
    public void testGivenABadCoordinateWhenGettingTheCoordinateInBetweenThenIsIncorrect() {
        assertEquals(new Coordinate(5,5), originCoordinate.getBetweenDiagonalCoordinate(new Coordinate(7,0)));
    }

    @Test
    public void testGivenACoordinateWhenGettingTheOnesInBetweenThenIsCorrect() {
        List<Coordinate> coordinates = new ArrayList<Coordinate>(2);
        coordinates.add(new Coordinate(3,3));
        coordinates.add(new Coordinate(2,2));
        assertEquals(coordinates, originCoordinate.getBetweenDiagonalCoordinates(new Coordinate(1,1)));
    }

    @Test(expected = AssertionError.class)
    public void testGivenABadCoordinateWhenGettingTheOnesInBetweenThenIsIcorrect() {
        List<Coordinate> coordinates = new ArrayList<Coordinate>(0);
        assertEquals(coordinates, originCoordinate.getBetweenDiagonalCoordinates(new Coordinate(7,0)));
    }

    @Test
    public void testGivenALevelWhenGettingTheCoordinatesInTheDiagonalThenIsCorrect() {
        List<Coordinate> coordinatesLevel1 = new ArrayList<Coordinate>(4);
        coordinatesLevel1.add(new Coordinate(5,5));
        coordinatesLevel1.add(new Coordinate(3,5));
        coordinatesLevel1.add(new Coordinate(3,3));
        coordinatesLevel1.add(new Coordinate(5,3));

        List<Coordinate> coordinatesLevel2 = new ArrayList<Coordinate>(4);
        coordinatesLevel2.add(new Coordinate(6,6));
        coordinatesLevel2.add(new Coordinate(2,6));
        coordinatesLevel2.add(new Coordinate(2,2));
        coordinatesLevel2.add(new Coordinate(6,2));

        List<Coordinate> coordinatesLevel3 = new ArrayList<Coordinate>(1);
        coordinatesLevel3.add(new Coordinate(3,3));

        assertEquals(coordinatesLevel1, originCoordinate.getDiagonalCoordinates(1));
        assertEquals(coordinatesLevel2, originCoordinate.getDiagonalCoordinates(2));
        assertEquals(coordinatesLevel3, new Coordinate(0,0).getDiagonalCoordinates(3));
    }

    @Test
    public void testGivenABlackPawnWhenCheckingColorThenIsTrue() {
        Coordinate black = new Coordinate(1,2);
        assertTrue(black.isBlack());
    }
    @Test
    public void testGivenAWhitePawnWhenCheckingColorThenIsTrue() {
        Coordinate white = new Coordinate(1,7);
        assertFalse(white.isBlack());
    }

    @Test
    public void testGivenACoordinateWhenIsLastThenIsCorrect() {
        assertTrue(new Coordinate(7,0).isLast());
        assertTrue(new Coordinate(7,0).isLast());
        assertTrue(new Coordinate(7,2).isLast());
        assertTrue(new Coordinate(7,7).isLast());
    }
    @Test
    public void testGivenACoordinateWhenIsNotLastThenIsIncorrect() {
        assertFalse(new Coordinate(0,0).isLast());
        assertFalse(new Coordinate(1,1).isLast());
        assertFalse(new Coordinate(4,7).isLast());
        assertFalse(new Coordinate(6,7).isLast());
    }

    @Test
    public void testGivenACoordinateWhenIsFirstThenIsCorrect() {
        assertTrue(new Coordinate(0,0).isFirst());
        assertTrue(new Coordinate(0,2).isFirst());
        assertTrue(new Coordinate(0,4).isFirst());
        assertTrue(new Coordinate(0,6).isFirst());
    }
    @Test
    public void testGivenACoordinateWhenIsNotFirstThenIsIncorrect() {
        assertFalse(new Coordinate(7,7).isFirst());
        assertFalse(new Coordinate(1,1).isFirst());
        assertFalse(new Coordinate(4,7).isFirst());
        assertFalse(new Coordinate(7,2).isFirst());
    }
}
