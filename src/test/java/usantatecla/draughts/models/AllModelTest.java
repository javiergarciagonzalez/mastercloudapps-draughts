package usantatecla.draughts.models;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    BoardTest.class,
    CoordinateTest.class,
    DirectionTest.class,
    DraughtTest.class,
    GameTest.class,
    PawnTest.class,
    PieceTest.class,
    StateTest.class,
    TurnTest.class
})
public class AllModelTest {

}
