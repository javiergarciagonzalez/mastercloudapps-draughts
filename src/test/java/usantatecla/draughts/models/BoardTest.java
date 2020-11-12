package usantatecla.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

    private Board board;
    private Pawn pawn;
    private Coordinate coordinate;

    public BoardTest() {
        this.board = new Board();
        this.pawn = new Pawn(Color.BLACK);
    }

    @Before
    public void init() {
        this.coordinate = new Coordinate(4,4);
        this.board.put(coordinate, this.pawn);
    }

    @Test
    public void TestGivenABoardWhenCheckingItsPiecesThenIsCorrect() {
        assertEquals(this.pawn, this.board.getPiece(this.coordinate));
        Coordinate newCoordinate = new Coordinate(7,7);

        this.board.move(this.coordinate, newCoordinate);
        assertNull(this.board.getPiece(this.coordinate));
        assertEquals(this.pawn, this.board.getPiece(newCoordinate));
        assertEquals(this.board.getColor(newCoordinate), Color.BLACK);

        this.board.remove(newCoordinate);
        assertNull(this.board.getPiece(newCoordinate));
        assertTrue(this.board.isEmpty(newCoordinate));
    }

    @Test
    public void TestGivenACoordinateWhenGettingANonExistingPieceThenItReturnsNull() {
        assertNull(this.board.getPiece(new Coordinate(0,0)));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void TestGivenAOutOfBoundsCoordinateWhenGettingAPieceThenItShouldFail() {
        this.board.getPiece(new Coordinate(100,100));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void TestGivenAWrongCoordinateWhenMovingAPieceThereThenItShouldFail() {
        this.board.move(this.coordinate, new Coordinate(100,100));
    }

    @Test (expected = AssertionError.class)
    public void TestGivenACoordinateWhenMovingAPieceThatIsNotThereThenItShouldFail() {
        this.board.move(new Coordinate(0,0), new Coordinate(1,1));
    }

    @Test
    public void TestGivenAFullBoardWhenRemovingACoordinateThenIsClear() {
        assertNotNull(this.board.getPiece(this.coordinate));
        this.board.remove(this.coordinate);
        assertNull(this.board.getPiece(this.coordinate));
    }

    @Test (expected = AssertionError.class)
    public void TestGivenAFullBoardWhenRemovingACoordinateEmptyThenIsClear() {
        Coordinate emptyCoordinate = new Coordinate(0, 0);
        assertNull(this.board.getPiece(emptyCoordinate));
        this.board.remove(emptyCoordinate);
    }

    @Test
    public void TestGivenTwoCoordinatesWhenGettingTheDiagonalBetweenThemThenIsCorrect() {
        Coordinate coordinateInDiagonal = new Coordinate(6,6);
        Coordinate coordinateInBetween = new Coordinate(5,5);
        Pawn pieceInBetween = new Pawn(Color.BLACK);
        this.board.put(coordinateInBetween, pieceInBetween);
        assertEquals(Arrays.asList(pieceInBetween), this.board.getBetweenDiagonalPieces(this.coordinate, coordinateInDiagonal));
        Coordinate farCoordinateInDiagonal = new Coordinate(7,7);
        Pawn farPieceInBetween = new Pawn(Color.WHITE);
        this.board.put(coordinateInDiagonal, farPieceInBetween);
        assertEquals(Arrays.asList(pieceInBetween, farPieceInBetween), this.board.getBetweenDiagonalPieces(this.coordinate, farCoordinateInDiagonal));
    }

    @Test
    public void TestGivenTwoCoordinatesWithoutPieceInBetweenWhenGettingThePieceInBetweenThenItReturnsAnEmptyList() {
        Coordinate coordinateInDiagonal = new Coordinate(6,6);
        assertEquals(Arrays.asList(), this.board.getBetweenDiagonalPieces(this.coordinate, coordinateInDiagonal));
    }

    @Test
    public void TestGivenBadCoordinatesWhenGettingDiagonalInBetweenThenItReturnsAnEmptyList() {
        Coordinate coordinateOutOfBoundaries = new Coordinate(100, 1000);
        assertEquals(Arrays.asList(), this.board.getBetweenDiagonalPieces(this.coordinate, coordinateOutOfBoundaries));
    }

    @Test
    public void TestGivenTwoCoordinatesWhenGettingTheAmountOfPiecesInBetweenThenIsCorrect() {
        Coordinate coordinateInDiagonal = new Coordinate(6,6);
        Coordinate coordinateInBetween = new Coordinate(5,5);
        Pawn pieceInBetween = new Pawn(Color.BLACK);
        this.board.put(coordinateInBetween, pieceInBetween);
        assertEquals(1, this.board.getAmountBetweenDiagonalPieces(this.coordinate, coordinateInDiagonal));
        Coordinate farCoordinateInDiagonal = new Coordinate(7,7);
        Pawn farPieceInBetween = new Pawn(Color.WHITE);
        this.board.put(coordinateInDiagonal, farPieceInBetween);
        assertEquals(2, this.board.getAmountBetweenDiagonalPieces(this.coordinate, farCoordinateInDiagonal));
    }

    @Test
    public void TestGivenTwoCoordinatesWithoutPieceInBetweenWhenGettingTheAmountOfPieceInBetweenThenItReturnsZero() {
        Coordinate coordinateInDiagonal = new Coordinate(6,6);
        assertEquals(0, this.board.getAmountBetweenDiagonalPieces(this.coordinate, coordinateInDiagonal));
    }

    @Test
    public void TestGivenTwoBadCoordinatesWhenGettingTheAmountOfPiecesInBetweenThenItReturnsZero() {
        Coordinate coordinateOutOfBoundaries = new Coordinate(100, 1000);
        assertEquals(0, this.board.getAmountBetweenDiagonalPieces(this.coordinate, coordinateOutOfBoundaries));
    }

    @Test
    public void TestGivenAnEmptyCoordinateWhenGettingTheColorItShouldReturnNull() {
        assertNull(this.board.getColor(new Coordinate(0,0)));
    }

}
