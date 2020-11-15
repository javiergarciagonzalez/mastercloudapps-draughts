package usantatecla.draughts.views;

import usantatecla.draughts.controllers.ResumeController;
import usantatecla.draughts.utils.YesNoDialog;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResumeViewTest {

    private static final String MESSAGE = "¿Queréis jugar otra";

    @InjectMocks
    private ResumeView resumeView;

    @Mock
    private ResumeController resumeController;
    @Mock
    private YesNoDialog yesNoDialog;


    @Before
    public void before() {
        initMocks(this);
    }

    @Test(expected = AssertionError.class)
    public void testGivenResumeViewWhenInteractWithNullThenIsError() {
        this.resumeView.interact(null);
    }

    @Test
    public void testGivenResumeViewWhenInteractWithPositiveAnswerThenReset(){
        when(yesNoDialog.read(MESSAGE)).thenReturn(true);
        resumeView.interact(resumeController);
        verify(resumeController).reset();
    }

    @Test
    public void testGivenResumeViewWhenInteractWithNegativeAnswerThenNext(){
        when(yesNoDialog.read(MESSAGE)).thenReturn(false);
        resumeView.interact(resumeController);
        verify(resumeController).next();
    }
}
