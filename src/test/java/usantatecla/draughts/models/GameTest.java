package usantatecla.draughts.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

    private Game game;

    @Before
    public void fillBoard() {
        this.game = new GameBuilder().build(
            " n n n n",
            "n n n n ",
            " n n n n",
            "        ",
            "        ",
            "b b b b ",
            " b b b b",
            "b b b b "
        );
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
        Coordinate target = new Coordinate(4,1);
        assertNull(this.game.getPiece(target));
        Coordinate [] coordinates = {new Coordinate(5,0), target};
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
