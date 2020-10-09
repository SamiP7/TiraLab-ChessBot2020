package test.java.datastructureproject.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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


import java.util.*;
import main.java.datastructureproject.init.*;
import chess.engine.*;
import jdk.jfr.Timestamp;
import chess.model.Side;

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
        StringList moves = m.knightMoves(0, 1, new Pieces("N"), b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("b8c6"));
        ans.add(new String("b8a6"));
        List<String> movesList = new ArrayList<>();
        for (int i = 0; i < moves.size(); i++) {
            movesList.add(moves.get(i));
        }
        Collections.sort(movesList);
        Collections.sort(ans);
        assertEquals(movesList, ans);
    }

    @Test
    public void getBishopPossibleMovesAtTheStart() {
        StringList moves = m.bishopMoves(0, 2, new Pieces("B"), b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        List<String> movesList = new ArrayList<>();
        for (int i = 0; i < moves.size(); i++) {
            movesList.add(moves.get(i));
        }
        Collections.sort(movesList);
        Collections.sort(ans);
        assertEquals(movesList, ans);
    }

    @Test
    public void getPawnPossibleMovesAtTheStart() {
        StringList moves = m.pawnMoves(6, 2, new Pieces("p"), b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("c2c3"));
        ans.add(new String("c2c4"));
        List<String> movesList = new ArrayList<>();
        for (int i = 0; i < moves.size(); i++) {
            movesList.add(moves.get(i));
        }
        Collections.sort(movesList);
        Collections.sort(ans);
        assertEquals(movesList, ans);
    }

    @Test
    public void getKingPossibleWhenMiddleOfTheBoard() {
        StringList moves = m.kingMoves(4, 3, new Pieces("k"), b.returnBoard(), new String("white"));
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("d4c3"));
        ans.add(new String("d4d3"));
        ans.add(new String("d4c4"));
        ans.add(new String("d4e3"));
        ans.add(new String("d4c5"));
        ans.add(new String("d4d5"));
        ans.add(new String("d4e4"));
        ans.add(new String("d4e5"));
        List<String> movesList = new ArrayList<>();
        for (int i = 0; i < moves.size(); i++) {
            movesList.add(moves.get(i));
        }
        Collections.sort(movesList);
        Collections.sort(ans);
        assertEquals(movesList, ans);
    }

    @Test
    public void allMovesForBlackAtTheStart() {
        StringList moves = m.allMovesForSide(gs.playing, b.returnBoard());
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
        List<String> movesList = new ArrayList<>();
        for (int i = 0; i < moves.size(); i++) {
            movesList.add(moves.get(i));
        }
        Collections.sort(movesList);
        Collections.sort(ans);
        assertEquals(movesList, ans);
    }

    @Test
    public void allMovesForWhiteAtTheStart() {
        StringList moves = m.allMovesForSide(Side.WHITE, b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("a2a3"));
        ans.add(new String("a2a4"));
        ans.add(new String("b2b4"));
        ans.add(new String("b2b3"));
        ans.add(new String("c2c3"));
        ans.add(new String("c2c4"));
        ans.add(new String("d2d3"));
        ans.add(new String("d2d4"));
        ans.add(new String("e2e3"));
        ans.add(new String("e2e4"));
        ans.add(new String("f2f3"));
        ans.add(new String("f2f4"));
        ans.add(new String("g2g3"));
        ans.add(new String("g2g4"));
        ans.add(new String("h2h3"));
        ans.add(new String("h2h4"));
        ans.add(new String("b1a3"));
        ans.add(new String("b1c3"));
        ans.add(new String("g1f3"));
        ans.add(new String("g1h3"));
        List<String> movesList = new ArrayList<>();
        for (int i = 0; i < moves.size(); i++) {
            movesList.add(moves.get(i));
        }
        Collections.sort(movesList);
        Collections.sort(ans);
        assertEquals(movesList, ans);
    }

    @Test
    public void bishopMovesFromMiddle() {
        StringList moves = m.bishopMoves(4, 3, new Pieces("B"), b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("d4e3"));
        ans.add(new String("d4f2"));
        ans.add(new String("d4b2"));
        ans.add(new String("d4c3"));
        ans.add(new String("d4c5"));
        ans.add(new String("d4b6"));
        ans.add(new String("d4e5"));
        ans.add(new String("d4f6"));
        List<String> movesList = new ArrayList<>();
        for (int i = 0; i < moves.size(); i++) {
            movesList.add(moves.get(i));
        }
        Collections.sort(movesList);
        Collections.sort(ans);
        assertEquals(movesList, ans);
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

    @Test
    public void castlingTestWhiteKingSide() {
        String[][] test = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test[i][j] = b.returnBoard()[i][j];
            }
        }
        test[7][4] = "";
        test[7][5] = "r";
        test[7][6] = "k";
        test[7][7] = "";
        b.returnBoard()[7][6] = "";
        b.returnBoard()[7][5] = "";
        b.doMove(m.convertBackFromUCI("e1g1"), this.b.returnBoard());
        assertEquals(b.returnBoard(), test);
    }

    @Test
    public void castlingTestWhiteQueenSide() {
        String[][] test = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test[i][j] = b.returnBoard()[i][j];
            }
        }
        test[7][4] = "";
        test[7][3] = "r";
        test[7][2] = "k";
        test[7][1] = "";
        test[7][0] = "";
        b.returnBoard()[7][3] = "";
        b.returnBoard()[7][2] = "";
        b.returnBoard()[7][1] = "";
        b.doMove(m.convertBackFromUCI("e1c1"), this.b.returnBoard());
        assertEquals(b.returnBoard(), test);
    }

    @Test
    public void canKingCastle() {
        String[][] test = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test[i][j] = b.returnBoard()[i][j];
            }
        }
        test[7][3] = "";
        test[7][2] = "";
        test[7][1] = "";
        assertEquals(m.canKingCastleQueenSide("white", test), true);
    }

    @Test
    public void testEnPassant() {
        String[][] test = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test[i][j] = b.returnBoard()[i][j];
            }
        }
        test[6][2] = "";
        test[1][3] = "";
        test[5][2] = "P";

        b.returnBoard()[6][2] = "";
        b.returnBoard()[1][3] = "";
        b.returnBoard()[4][2] = "p";
        b.returnBoard()[4][3] = "P";
        b.doMove(m.convertBackFromUCI("d4c3"), this.b.returnBoard());
        assertEquals(b.returnBoard(), test);
    }

    
}
