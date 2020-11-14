package usantatecla.draughts.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PieceTest {

    @Test
    public void testGivenTwoPiecesWhenIsAdvancedThenIsCorrect(){
        // Pawn
        assertTrue(new Pawn(Color.WHITE).isAdvanced(new Coordinate(5,6), new Coordinate(4,7)));
        assertTrue(new Pawn(Color.BLACK).isAdvanced(new Coordinate(2,7), new Coordinate(3,6)));
        // Draught
        assertTrue(new Draught(Color.WHITE).isAdvanced(new Coordinate(5,6), new Coordinate(4,7)));
        assertTrue(new Draught(Color.BLACK).isAdvanced(new Coordinate(2,7), new Coordinate(3,6)));
    }

    @Test
    public void testGivenTowPiecesWhenNotIsAdvancedThenIsIncorrect(){
        // Pawn
        assertFalse(new Pawn(Color.WHITE).isAdvanced(new Coordinate(5,6), new Coordinate(6,7)));
        assertFalse(new Pawn(Color.BLACK).isAdvanced(new Coordinate(2,7), new Coordinate(1,6)));
        // Draught
        assertFalse(new Draught(Color.WHITE).isAdvanced(new Coordinate(5,6), new Coordinate(6,7)));
        assertFalse(new Draught(Color.BLACK).isAdvanced(new Coordinate(2,7), new Coordinate(1,6)));
    }

    @Test
    public void testGivenTwoPiecesWhenIsLimitThenIsTrue(){
        // Pawn
        assertTrue(new Pawn(Color.WHITE).isLimit(new Coordinate(0,7)));
        assertTrue(new Pawn(Color.BLACK).isLimit(new Coordinate(7,0)));
        // Draught
        assertTrue(new Draught(Color.WHITE).isLimit(new Coordinate(0,7)));
        assertTrue(new Draught(Color.BLACK).isLimit(new Coordinate(7,0)));
    }

    @Test
    public void testGivenTowPiecesWhenNotIsLimitThenIsFalse(){
        // Pawn
        assertFalse(new Pawn(Color.WHITE).isLimit(new Coordinate(5,5)));
        assertFalse(new Pawn(Color.BLACK).isLimit(new Coordinate(5,5)));
        // Draught
        assertFalse(new Draught(Color.WHITE).isLimit(new Coordinate(5,5)));
        assertFalse(new Draught(Color.BLACK).isLimit(new Coordinate(5,5)));
    }

}
