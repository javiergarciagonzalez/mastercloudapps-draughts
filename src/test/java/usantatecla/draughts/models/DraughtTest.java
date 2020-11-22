package usantatecla.draughts.models;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DraughtTest {

    private Draught draughtWhite;
    private Draught draughtBlack;

    public DraughtTest() {
        this.draughtWhite = new Draught(Color.WHITE);
        this.draughtBlack = new Draught(Color.BLACK);
    }

    @Test
    public void testGivenASetOfAllowedPairOfCoordinatesWhenCheckingDiagonalThenIsCorrect() {
        Coordinate [] diagonalCoordinatesBlack ={new Coordinate(5,3), new Coordinate(6,2)};
        assertEquals(Error.NULL, this.draughtBlack.isCorrectDiagonalMovement(0, 0, diagonalCoordinatesBlack));
        assertEquals(Error.NULL, this.draughtBlack.isCorrectDiagonalMovement(1, 0, diagonalCoordinatesBlack));
        Coordinate [] diagonalCoordinatesWhite ={new Coordinate(6,3), new Coordinate(3,6)};
        assertEquals(Error.NULL, this.draughtWhite.isCorrectDiagonalMovement(0, 0, diagonalCoordinatesWhite));
        assertEquals(Error.NULL, this.draughtWhite.isCorrectDiagonalMovement(1, 0, diagonalCoordinatesWhite));
    }

    @Test
    public void testGivenASetOfNotAllowedPairOfCoordinatesWhenCheckingDiagonalThenItReturnsTooMuchEatingError() {
        Coordinate [] diagonalCoordinatesBlack ={new Coordinate(5,3), new Coordinate(6,2)};
        assertEquals(Error.TOO_MUCH_EATINGS, this.draughtBlack.isCorrectDiagonalMovement(2, 0, diagonalCoordinatesBlack));
        Coordinate [] diagonalCoordinatesWhite ={new Coordinate(6,3), new Coordinate(3,6)};
        assertEquals(Error.TOO_MUCH_EATINGS, this.draughtWhite.isCorrectDiagonalMovement(2, 0, diagonalCoordinatesWhite));
    }

}
