package usantatecla.draughts.views;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.MockitoAnnotations.initMocks;

import usantatecla.draughts.controllers.PlayController;
import usantatecla.draughts.models.Color;
import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.utils.Console;

public class PlayViewTest {
    private static final String PROMPT_BLACKS = "Mueven las negras: ";
    private static final String PROMPT_WHITES = "Mueven las blancas: ";
    private static final String CANCEL_FORMAT = "-1";
    private static final String RIGHT_FORMAT = "18.27";
    private static final String ERROR_MESSAGE = "Error!!! Formato incorrecto";
    private static final String LOST_MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";
    private static final Coordinate[] RIGHT_COORDINATES = new Coordinate[]{
        new Coordinate(0, 7),
        new Coordinate(1, 6)
    };

    @Mock
    PlayController playController;
    @Mock
    Console console;
    @InjectMocks
    PlayView playView;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test(expected = AssertionError.class)
    public void testGivenPlayViewWhenInteractWithNullThenIsError() {
        this.playView.interact(null);
    }

    @Test
    public void testGivenPlayViewWhenInteractWithCancelFormatAndBlackColorThenCancel() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString(PROMPT_BLACKS)).thenReturn(CANCEL_FORMAT);
        playView.interact(playController);
        verify(playController).cancel();
    }

    @Test
    public void testGivenPlayViewWhenInteractWithCancelFormatAndWhiteColorThenCancel() {
        when(playController.getColor()).thenReturn(Color.WHITE);
        when(console.readString(PROMPT_WHITES)).thenReturn(CANCEL_FORMAT);
        playView.interact(playController);
        verify(playController).cancel();
    }

    @Test
    public void testGivenPlayViewWhenGivingGoodCoordinatesThenIsCorrect() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString(PROMPT_BLACKS)).thenReturn(RIGHT_FORMAT);
        playView.interact(playController);
        verify(playController).move(RIGHT_COORDINATES);
    }

    @Test
    public void testGivenPlayViewWhenGivingEmptyCoordinatesThenIsError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString(PROMPT_BLACKS)).thenReturn("").thenReturn(RIGHT_FORMAT);
        playView.interact(playController);
        verify(console).writeln(ERROR_MESSAGE);
        verify(playController).move(RIGHT_COORDINATES);
    }

    @Test
    public void testGivenPlayViewWhenInteractWithNotPointThenIsError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString(PROMPT_BLACKS)).thenReturn("44,45").thenReturn(RIGHT_FORMAT);
        playView.interact(playController);
        verify(console).writeln(ERROR_MESSAGE);
        verify(playController).move(RIGHT_COORDINATES);
    }


    @Test
    public void testGivenPlayViewWhenInteractWithBadRangeThenIsError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString(PROMPT_BLACKS)).thenReturn("99.89").thenReturn(RIGHT_FORMAT);
        playView.interact(playController);
        verify(playController).move(RIGHT_COORDINATES);
    }

    @Test
    public void testGivenPlayViewWhenGvingNegativeCoordinatesThenIsError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString(PROMPT_BLACKS)).thenReturn("43,-34").thenReturn(RIGHT_FORMAT);
        playView.interact(playController);
        verify(playController).move(RIGHT_COORDINATES);
    }


    @Test
    public void testGivenPlayViewWhenGivingABadFormatThenIsError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString(PROMPT_BLACKS)).thenReturn("!?._@").thenReturn(RIGHT_FORMAT);
        playView.interact(playController);
        verify(console).writeln(ERROR_MESSAGE);
        verify(playController).move(RIGHT_COORDINATES);
    }

    @Test
    public void testGivenPlayViewWhenGivingCoordiantesWithoutSeparatorThenIsCorrect() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString(PROMPT_BLACKS)).thenReturn("0123").thenReturn(RIGHT_FORMAT);
        playView.interact(playController);
        verify(playController).move(RIGHT_COORDINATES);
    }

    @Test
    public void testGivenPlayViewWhenGivingThreeCoordiantesThenIsCorrect() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString(PROMPT_BLACKS)).thenReturn("23.00.11").thenReturn(RIGHT_FORMAT);
        playView.interact(playController);
        verify(playController).move(RIGHT_COORDINATES);
    }

    @Test
    public void testGivenPlayViewWhenGivingGoodCoordinatesWhenGameIsBlockedThenIsCorrectAndGameIsLost() {
        when(playController.getColor()).thenReturn(Color.WHITE);
        when(this.playController.isBlocked()).thenReturn(true);
        when(this.console.readString(PROMPT_WHITES)).thenReturn(RIGHT_FORMAT);
        this.playView.interact(this.playController);
        verify(this.playController).move(RIGHT_COORDINATES);
        verify(this.console).writeln(LOST_MESSAGE);
    }
}
