package usantatecla.draughts.models;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TurnTest {

    private Turn turn;

    public TurnTest() {
        this.turn = new Turn();
    }

    @Test
    public void testGivenNewTurnWhenChangeTurnThenIsOtherTurn() {
        assertEquals(this.turn.getColor(), Color.WHITE);
        this.turn.change();
        assertEquals(this.turn.getColor(), Color.BLACK);
    }

    @Test
    public void testGivenNewTurnWhenGettingTheOppositeColorThenItReturnsBlack() {
        assertEquals(this.turn.getColor(), Color.WHITE);
        assertEquals(this.turn.getOppositeColor(), Color.BLACK);
    }

    @Test
    public void testGivenNewTurnWhenGettingTheOppositeColorAfterChangingTurnThenItReturnsBlack() {
        assertEquals(this.turn.getColor(), Color.WHITE);
        this.turn.change();
        assertEquals(this.turn.getOppositeColor(), Color.WHITE);
        assertEquals(this.turn.getColor(), Color.BLACK);
    }
}
