import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void testGenerateFeedback() {
        NumberGameLogic gameLogic = new NumberGameLogic();
        assertEquals("BBBB,",gameLogic.generateFeedback("1234","1234"));
        assertEquals("BB,CC",gameLogic.generateFeedback("1234","1243"));

    }
    @Test
    public void testGoal() {
        NumberGameLogic gameLogic = new NumberGameLogic();
        assertEquals(4,gameLogic.makeGoal().length());
    }

}


