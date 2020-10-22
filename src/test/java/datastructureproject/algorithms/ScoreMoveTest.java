package test.java.datastructureproject.algorithms;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import main.java.datastructureproject.algorithms.*;

public class ScoreMoveTest {
    ScoreMove sm;

    @Test
    public void getAndSetMove() {
        this.sm = new ScoreMove(5, "a1a5");

        assertEquals(sm.returnMove(), "a1a5");
    }

    @Test
    public void getAndSetScore() {
        this.sm = new ScoreMove(5, "a1a5");

        assertEquals(sm.returnScore(), 5);
    }

}
