package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import usantatecla.draughts.controllers.StartController;
import usantatecla.draughts.utils.Console;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class StartViewTest {

    private static final String TITTLE = "Draughts";

    @InjectMocks
    private StartView startView;

    @Mock
    private Console console;
    @Mock
    private StartController startController;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test(expected = AssertionError.class)
    public void testGivenStartViewWhenInteractWithNullThenIsError() {
        this.startView.interact(null);
    }

    @Test
    public void testGivenStartViewWhenInteractWithStartControllerThenInteract() {
        this.startView.interact(this.startController);
        verify(this.console).writeln(TITTLE);
        verify(this.startController).getDimension();
        verify(this.startController).start();
    }
}
