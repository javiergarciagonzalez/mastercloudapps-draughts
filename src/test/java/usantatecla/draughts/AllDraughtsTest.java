package usantatecla.draughts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import usantatecla.draughts.controllers.AllControllerTest;
import usantatecla.draughts.models.AllModelTest;
import usantatecla.draughts.views.AllViewTest;
import usantatecla.draughts.utils.AllUtilsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    AllControllerTest.class,
    AllModelTest.class,
    AllViewTest.class,
    AllUtilsTest.class
})
public class AllDraughtsTest {}