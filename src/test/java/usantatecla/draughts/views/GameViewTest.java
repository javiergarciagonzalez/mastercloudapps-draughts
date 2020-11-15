package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import usantatecla.draughts.controllers.InteractorController;
import usantatecla.draughts.controllers.StartController;
import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameViewTest {

    private Game game = new Game();
    private State state = new State();

    private GameView gameView;
    private InteractorController interactorController;

    @Before
    public void before() {
        initMocks(this);
        this.gameView = new GameView();
        this.interactorController = spy(new StartController(this.game, this.state));
    }

    @Test(expected = AssertionError.class)
    public void testGivenGameViewWhenPassingNullTpoWriteMethodThenIsError() {
        this.gameView.write(null);
    }

    @Test
    public void testGivenGameViewWhenWritingTheGameThenControllerIsCalledAsManyTimesAsDimensionItHas() {
        final ArgumentCaptor<Coordinate> captor = ArgumentCaptor.forClass(Coordinate.class);
        this.gameView.write(this.interactorController);
        verify(this.interactorController, times(Coordinate.getDimension() * Coordinate.getDimension())).getPiece(captor.capture());
        // Last Coordinate Captured
        assertEquals(captor.getValue(), new Coordinate(7,7));
    }
}
