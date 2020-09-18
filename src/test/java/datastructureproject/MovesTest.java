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
import chess.engine.*;
import jdk.jfr.Timestamp;

public class MovesTest {
    
    Board b;
    Moves m;
    GameState gs;

    @Before
    public void setUp() {
        this.b = new Board();
        this.gs = new GameState();
        this.m = new Moves(b, gs);
    }

    @Test
    public void getKnightPossibleMovesAtTheStart() {
        ArrayList<String> moves = m.knightMoves(0, 1, new Pieces("N"), b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("b8c6"));
        ans.add(new String("b8a6"));
        assertEquals(moves, ans);
    }

    @Test
    public void getBishopPossibleMovesAtTheStart() {
        ArrayList<String> moves = m.bishopMoves(0, 2, new Pieces("B"), b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        assertEquals(moves, ans);
    }

    @Test
    public void getPawnPossibleMovesAtTheStart() {
        ArrayList<String> moves = m.pawnMoves(6, 2, new Pieces("p"), b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("c2c3"));
        ans.add(new String("c2c4"));
        assertEquals(moves, ans);
    }

    @Test
    public void getKingPossibleWhenMiddleOfTheBoard() {
        ArrayList<String> moves = m.kingMoves(4, 3, new Pieces("k"), b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("d4c3"));
        ans.add(new String("d4d3"));
        ans.add(new String("d4c4"));
        ans.add(new String("d4e3"));
        ans.add(new String("d4c5"));
        ans.add(new String("d4d5"));
        ans.add(new String("d4e4"));
        ans.add(new String("d4e5"));
        assertEquals(moves, ans);
    }

    @Test
    public void allMovesForWhiteAtTheStart() {
        ArrayList<String> moves = m.allMovesForBot(gs.playing);
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("b8c6"));
        ans.add(new String("b8a6"));
        ans.add(new String("g8h6"));
        ans.add(new String("g8f6"));
        ans.add(new String("a7a6"));
        ans.add(new String("a7a5"));
        ans.add(new String("b7b6"));
        ans.add(new String("b7b5"));
        ans.add(new String("c7c6"));
        ans.add(new String("c7c5"));
        ans.add(new String("d7d6"));
        ans.add(new String("d7d5"));
        ans.add(new String("e7e6"));
        ans.add(new String("e7e5"));
        ans.add(new String("f7f6"));
        ans.add(new String("f7f5"));
        ans.add(new String("g7g6"));
        ans.add(new String("g7g5"));
        ans.add(new String("h7h6"));
        ans.add(new String("h7h5"));
        
        assertEquals(moves, ans);
    }

    @Test
    public void bishopMovesFromMiddle() {
        ArrayList<String> moves = m.bishopMoves(4, 3, new Pieces("B"), b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("d4b2"));
        ans.add(new String("d4c3"));
        ans.add(new String("d4e3"));
        ans.add(new String("d4f2"));
        ans.add(new String("d4c5"));
        ans.add(new String("d4b6"));
        ans.add(new String("d4e5"));
        ans.add(new String("d4f6"));
        assertEquals(moves, ans);
    }

    @Test
    public void convertToUCITest() {
        String str = "0021";
        String test = m.convertToUCI(str);
        assertEquals(test, "a8b6");
    }

    @Test
    public void convertToUCIWithPromotionTest() {
        String str = "6777N";
        String test = m.convertToUCI(str);
        assertEquals(test, "h2h1N");
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
