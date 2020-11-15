package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import usantatecla.draughts.controllers.InteractorController;
import usantatecla.draughts.controllers.PlayController;
import usantatecla.draughts.controllers.ResumeController;
import usantatecla.draughts.controllers.StartController;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ViewTest {

    private Game game = new Game();
    private State state = new State();

    @InjectMocks
    private View view;

    @Mock
    private StartView startView;
    @Mock
    private PlayView playView;
    @Mock
    private ResumeView resumeView;
    @Mock
    private InteractorController interactorController;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test(expected = AssertionError.class)
    public void testGivenViewWhenInteractWithNullThenIsError() {
        this.view.interact(null);
    }

    @Test
    public void testGivenViewWhenInteractingThenCallsAcceptFromController() {
        this.view.interact(this.interactorController);
        verify(this.interactorController).accept(this.view);
    }

    @Test(expected = AssertionError.class)
    public void testGivenViewWhenVisitingStartControllerWithNullThenIsError() {
        this.view.visit((StartController) null);
    }

    @Test
    public void testGivenViewWhenVisitingStartControllerThenCallsInteract() {
        StartController startController = new StartController(this.game, this.state);
        this.view.visit(startController);
        verify(this.startView).interact(startController);
    }

    @Test(expected = AssertionError.class)
    public void testGivenViewWhenVisitingPlayControllerWithNullThenIsError() {
        this.view.visit((PlayController) null);
    }

    @Test
    public void testGivenViewWhenVisitingPlayControllerThenCallsInteract() {
        PlayController playController = new PlayController(this.game, this.state);
        this.view.visit(playController);
        verify(this.playView).interact(playController);
    }

    @Test(expected = AssertionError.class)
    public void testGivenViewWhenVisitingResumeControllerWithNullThenIsError() {
        this.view.visit((ResumeController) null);
    }

    @Test
    public void testGivenViewWhenVisitingResumeControllerThenCallsInteract() {
        ResumeController resumeController = new ResumeController(this.game, this.state);
        this.view.visit(resumeController);
        verify(this.resumeView).interact(resumeController);
    }

}
