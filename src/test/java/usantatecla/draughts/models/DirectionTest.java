package usantatecla.draughts.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DirectionTest {

    @Test
    public void testGivenACoordinateWhenSameDirectionThenIsCorrect(){
        assertTrue(Direction.NW.isOnDirection(new Coordinate(4,-4)));
        assertTrue(Direction.NE.isOnDirection(new Coordinate(2,2)));
        assertTrue(Direction.SW.isOnDirection(new Coordinate(-2,-2)));
        assertTrue(Direction.SE.isOnDirection(new Coordinate(-4,4)));
    }

    @Test
    public void testGivenACoordinateWhenDifferentDirectionThenIsIncorrect(){
        assertFalse(Direction.NW.isOnDirection(new Coordinate(4,4)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(4,3)));
        assertFalse(Direction.SW.isOnDirection(new Coordinate(1,2)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(0,0)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(0,-1)));
    }

    @Test
    public void testGivenACoordinateWhenRowIsDifferentThanColumnThenIsFalse() {
        assertFalse(Direction.NE.isOnDirection(new Coordinate(1,3)));
        assertFalse(Direction.NW.isOnDirection(new Coordinate(3,1)));
    }

    @Test
    public void testGivenACoordinateWhenRowIsZeroThenIsFalse() {
        assertFalse(Direction.NE.isOnDirection(new Coordinate(0,0)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(0,3)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(0,-3)));
    }

}
