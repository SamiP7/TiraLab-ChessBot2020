package test.java.datastructureproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Stream;
import logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



import main.java.datastructureproject.init.*;

public class MovesTest {
    
    Board b;
    Moves m;

    @Before
    public void setUp() {
        this.b = new Board();
        this.m = new Moves(b);
    }

    @Test
    public void getKnightPossibleMovesAtTheStart() {
        ArrayList<String> moves = m.knightMoves(0, 1, new Pieces("N"));
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("b8c6"));
        ans.add(new String("b8a6"));
        assertEquals(moves, ans);
    }

    @Test
    public void convertToUCITest() {
        String str = "0021";
        String test = m.convertToUCI(str);
        assertEquals(test, "a8b6");
    }

    @Test
    public void convertBackFromUCITest() {
        String str = "a8b6";
        String test = m.convertBackFromUCI(str);
        assertEquals(test, "0021");
    }

    @Test
    public void convertBackFromUCITest2() {
        String str = "A8B6";
        String test = m.convertBackFromUCI(str);
        assertEquals(test, "0021");
    }
}
