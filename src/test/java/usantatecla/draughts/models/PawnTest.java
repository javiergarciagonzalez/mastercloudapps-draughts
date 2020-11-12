package usantatecla.draughts.models;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PawnTest {

    private Pawn pawnWhite;
    private Pawn pawnBlack;

    public PawnTest() {
        this.pawnWhite = new Pawn(Color.WHITE);
        this.pawnBlack = new Pawn(Color.BLACK);
    }

    @Test
    public void testGivenASetOfAllowedPairOfCoordinatesWhenCheckingDiagonalThenIsCorrect() {
        Coordinate [] diagonalCoordinatesBlack ={new Coordinate(1,7), new Coordinate(2,6)};
        assertEquals(null, this.pawnBlack.isCorrectDiagonalMovement(0, 0, diagonalCoordinatesBlack));
        Coordinate [] diagonalCoordinatesWhite ={new Coordinate(7,2), new Coordinate(6,3)};
        assertEquals(null, this.pawnWhite.isCorrectDiagonalMovement(0, 0, diagonalCoordinatesWhite));
    }

    @Test (expected = AssertionError.class)
    public void testGivenANotDiagonalPairOfCoordinatesWhenCheckingDiagonalForBlackPawnThenItThrowsAssertionError() throws AssertionError {
        Coordinate [] diagonalCoordinatesBlack ={new Coordinate(1,7), new Coordinate(2,7)};
        assertEquals(null, this.pawnBlack.isCorrectDiagonalMovement(0, 0, diagonalCoordinatesBlack));
    }

    @Test (expected = AssertionError.class)
    public void testGivenANotDiagonalPairOfCoordinatesWhenCheckingDiagonalForWhitePawnThenItThrowsAssertionError() throws AssertionError {
        Coordinate [] diagonalCoordinatesWhite ={new Coordinate(7,2), new Coordinate(6,2)};
        assertEquals(null, this.pawnWhite.isCorrectDiagonalMovement(0, 0, diagonalCoordinatesWhite));
    }

    @Test
    public void testGivenANotAdvancedCoordinateWhenCheckingDiagonalThenItReturnsNotAdvancedError() {
        Coordinate [] diagonalCoordinatesBlack ={new Coordinate(2,7), new Coordinate(1,7)};
        assertEquals(Error.NOT_ADVANCED, this.pawnBlack.isCorrectDiagonalMovement(0, 0, diagonalCoordinatesBlack));
        Coordinate [] diagonalCoordinatesWhite ={new Coordinate(7,2), new Coordinate(8,2)};
        assertEquals(Error.NOT_ADVANCED, this.pawnWhite.isCorrectDiagonalMovement(0, 0, diagonalCoordinatesWhite));
    }

    @Test
    public void testGivenALongDistanceInBetweenCoordinatesWhenCheckingDiagonalThenItReturnsTooMuchAdvancedError() {
        Coordinate [] diagonalCoordinatesBlack ={new Coordinate(2,7), new Coordinate(6,3)};
        assertEquals(Error.TOO_MUCH_ADVANCED, this.pawnBlack.isCorrectDiagonalMovement(0, 0, diagonalCoordinatesBlack));
        Coordinate [] diagonalCoordinatesWhite ={new Coordinate(6,3), new Coordinate(3,6)};
        assertEquals(Error.TOO_MUCH_ADVANCED, this.pawnWhite.isCorrectDiagonalMovement(0, 0, diagonalCoordinatesWhite));
    }

    @Test
    public void testGivenTwoCoordinatesWithoutEatingWhenCheckingDiagonalThenItReturnsWithoutEatingError() {
        Coordinate [] diagonalCoordinatesBlack ={new Coordinate(2,6), new Coordinate(4,4)};
        assertEquals(Error.WITHOUT_EATING, this.pawnBlack.isCorrectDiagonalMovement(0, 0, diagonalCoordinatesBlack));
        Coordinate [] diagonalCoordinatesWhite ={new Coordinate(6,3), new Coordinate(4,5)};
        assertEquals(Error.WITHOUT_EATING, this.pawnWhite.isCorrectDiagonalMovement(0, 0, diagonalCoordinatesWhite));
    }

}
