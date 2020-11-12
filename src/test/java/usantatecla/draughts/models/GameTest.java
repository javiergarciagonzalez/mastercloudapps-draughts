package usantatecla.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

    private Game game;
    private Board board;
    private Turn turn;

    public GameTest() {

        this.board = new Board();
        this.game = new Game(this.board);
        this.turn = new Turn();
    }

    @Before
    public void fillBoard() {
        this.board.put(new Coordinate(0,0), new Draught(Color.WHITE));
        this.board.put(new Coordinate(1,1), new Draught(Color.BLACK));
        this.board.put(new Coordinate(2,2), new Draught(Color.WHITE));
        this.board.put(new Coordinate(3,3), new Draught(Color.BLACK));
        this.board.put(new Coordinate(4,4), new Draught(Color.WHITE));
        this.board.put(new Coordinate(5,5), new Draught(Color.BLACK));
        this.board.put(new Coordinate(6,6), new Draught(Color.WHITE));
        this.board.put(new Coordinate(7,7), new Draught(Color.BLACK));
    }

    @Test
    public void TestGivenAFullBoardWhenResettingItThenTheBoardGetsClear() {
        this.game.reset();
        assertNull(this.game.getPiece(new Coordinate(0,0)));
        assertNull(this.game.getPiece(new Coordinate(2,2)));
        assertNull(this.game.getPiece(new Coordinate(3,3)));
        assertNull(this.game.getPiece(new Coordinate(7,7)));
    }

    @Test
    public void TestGivenAnEmptyBoardWhenResettingItThenTheBoardStaysTheSame() {
        Board emptyBoard = new Board();
        Game newGame = new Game(emptyBoard);
        assertNull(newGame.getPiece(new Coordinate(0,0)));
        assertNull(newGame.getPiece(new Coordinate(2,2)));
        assertNull(newGame.getPiece(new Coordinate(3,3)));
        assertNull(newGame.getPiece(new Coordinate(7,7)));
    }

    @Test
    public void TestGivenABoardWhenMovingItsPiecesThenIsCorrect() {
        Coordinate target = new Coordinate(3, 5);
        assertNull(this.game.getPiece(target));
        Coordinate [] coordinates = {new Coordinate(4,4), target};
        this.game.move(coordinates);
        assertNotNull(this.game.getPiece(target));
    }

    @Test (expected = AssertionError.class)
    public void TestGivenABoardAndABadTargetCoordinateWhenMovingItsPiecesThenIsFails() {
        Coordinate target = new Coordinate(7, 0);
        assertNull(this.game.getPiece(target));
        Coordinate [] coordinates = {new Coordinate(4,4), target};
        this.game.move(coordinates);
        assertNotNull(this.game.getPiece(target));
    }

}
