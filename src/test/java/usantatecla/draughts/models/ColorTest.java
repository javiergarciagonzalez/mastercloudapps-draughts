package usantatecla.draughts.models;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ColorTest {


    @Mock
    private Coordinate coordinate;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestGivenACoordinateOnLowerLimitWhenCheckingItsColorThenIsBlack() {
        when(coordinate.isBlack()).thenReturn(true);
        when(coordinate.getRow()).thenReturn(2);

        Color color = Color.getInitialColor(coordinate);
        assertTrue(Color.BLACK == color);
    }

    @Test
    public void TestGivenACoordinateOnUpperLimitWhenCheckingItsColorThenIsWhite() {
        when(coordinate.isBlack()).thenReturn(true);
        when(coordinate.getRow()).thenReturn(5);

        Color color = Color.getInitialColor(coordinate);
        assertTrue(Color.WHITE == color);
    }

    @Test
    public void TestGivenACoordinateOutOfLimitsWhenCheckingItsColorThenIsNull() {
        when(coordinate.getRow()).thenReturn(1);
        Color lowerLimitColor = Color.getInitialColor(coordinate);
        assertTrue(lowerLimitColor.isNull());

        when(coordinate.getRow()).thenReturn(6);
        Color upperLimitColor = Color.getInitialColor(coordinate);
        assertTrue(upperLimitColor.isNull());
    }

    @Test
    public void TestGivenAnEmptyCoordinateWhenCheckingItsColorThenIsNull() {
        when(coordinate.isBlack()).thenReturn(true);
        when(coordinate.getRow()).thenReturn(4);

        Color color = Color.getInitialColor(coordinate);
        assertTrue(color.isNull());
    }

    @Test
    public void TestGivenAnWhiteCoordinateWhenCheckingItsColorThenIsNull() {
        when(coordinate.isBlack()).thenReturn(false);

        Color color = Color.getInitialColor(coordinate);
        assertTrue(color.isNull());
    }
}
