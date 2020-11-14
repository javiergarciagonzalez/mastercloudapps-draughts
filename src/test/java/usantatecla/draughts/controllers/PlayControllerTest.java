package usantatecla.draughts.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;
import usantatecla.draughts.models.Color;
import usantatecla.draughts.models.GameBuilder;

public class PlayControllerTest {

    private PlayController playController;

    @Test
    public void testGivenPlayControllerWhenMovingAPieceThenIsCorrect() {
        Game game = new GameBuilder().build();
        playController = new PlayController(game, new State());
        Coordinate whitePiece = new Coordinate(5, 2);
        Coordinate whiteTarget = new Coordinate(4, 1);
        playController.move(whitePiece, whiteTarget);
        assertEquals(playController.getColor(whiteTarget), Color.WHITE);
        Coordinate blackPiece = new Coordinate(2, 1);
        Coordinate blackTarget = new Coordinate(3, 0);
        playController.move(blackPiece, blackTarget);
        assertEquals(playController.getColor(blackTarget), Color.BLACK);
    }

    @Test
    public void testGivenPlayControllerWhenEatingTheLastBlackPieceThenIsBlocked() {
        Game game = new GameBuilder().build(
            "        ",
            "        ",
            "        ",
            "        ",
            " n      ",
            "  b     ",
            "        ",
            "        ");
        playController = new PlayController(game, new State());
        Coordinate whitePiece = new Coordinate(5, 2);
        Coordinate target = new Coordinate(3, 0);
        playController.move(whitePiece, target);
        assertEquals(playController.getColor(target), Color.WHITE);
        assertTrue(game.isBlocked());
    }


    @Test
    public void testGivenPlayControllerWhenMakingAValidMoveThenIsNotBlocked() {
        Game game = new GameBuilder().build(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "b       "
        );

        playController = new PlayController(game, new State());
        Coordinate behindWhitePiece = new Coordinate(7, 0);
        Coordinate target = new Coordinate(6, 1);
        playController.move(behindWhitePiece, target);
        assertEquals(Color.WHITE, playController.getColor(target));
        assertTrue(game.isBlocked());
    }

    @Test
    public void testGivenPlayControllerWhenCancellingTurnThenIsBlackTurn() {
        Game game = new GameBuilder().build();
        playController = new PlayController(game, new State());
        playController.cancel();
        assertEquals(Color.BLACK, playController.getColor());
    }

}
