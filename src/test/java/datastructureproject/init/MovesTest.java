package test.java.datastructureproject.init;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import main.java.datastructureproject.init.*;
import chess.engine.*;
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
        StringList moves = m.knightMoves(0, 1, "N", b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("b8c6"));
        ans.add(new String("b8a6"));
        List<String> movesList = new ArrayList<>();
        for (int i = 0; i < moves.size(); i++) {
            movesList.add(moves.get(i));
        }
        Collections.sort(movesList);
        Collections.sort(ans);
        assertEquals(ans, movesList);
    }

    @Test
    public void getBishopPossibleMovesAtTheStart() {
        StringList moves = m.bishopMoves(0, 2, "B", b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        List<String> movesList = new ArrayList<>();
        for (int i = 0; i < moves.size(); i++) {
            movesList.add(moves.get(i));
        }
        Collections.sort(movesList);
        Collections.sort(ans);
        assertEquals(ans, movesList);
    }

    @Test
    public void getPawnPossibleMovesAtTheStart() {
        StringList moves = m.pawnMoves(6, 2, "p", b.returnBoard());
        ArrayList<String> ans = new ArrayList<>();
        ans.add(new String("c2c3"));
        ans.add(new String("c2c4"));
        List<String> movesList = new ArrayList<>();
        for (int i = 0; i < moves.size(); i++) {
            movesList.add(moves.get(i));
        }
        Collections.sort(movesList);
        Collections.sort(ans);
        assertEquals(ans, movesList);
    }

    @Test
    public void getKingPossibleWhenMiddleOfTheBoard() {
        StringList moves = m.kingMoves(4, 3, "k", b.returnBoard(), "white");
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
        assertEquals(ans, movesList);
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
        assertEquals(ans, movesList);
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
        assertEquals(ans, movesList);
    }

    @Test
    public void bishopMovesFromMiddle() {
        StringList moves = m.bishopMoves(4, 3, "B", b.returnBoard());
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
        assertEquals(ans, movesList);
    }

    @Test
    public void convertToUCITest() {
        String str = "0021";
        String test = m.convertToUCI(str);
        assertEquals("a8b6", test);
    }

    @Test
    public void convertToUCIWithPromotionTest() {
        String str = "6777N";
        String test = m.convertToUCI(str);
        assertEquals("h2h1N", test);
    }

    @Test
    public void convertBackFromUCITest() {
        String str = "a8b6";
        String test = m.convertBackFromUCI(str);
        assertEquals("0021", test);
    }

    @Test
    public void convertBackFromUCITest2() {
        String str = "A8B6";
        String test = m.convertBackFromUCI(str);
        assertEquals("0021", test);
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
        assertEquals(test, b.returnBoard());
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
        assertEquals(test, b.returnBoard());
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
        assertEquals(true, m.canKingCastleQueenSide("white", test));
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
        assertEquals(test, b.returnBoard());
    }

    @Test
    public void enPassantIsPossible() {
        gs.moves.add("d2d4");
        gs.moves.add("e7e5");
        gs.moves.add("b1c3");
        gs.moves.add("e5e4");
        gs.moves.add("f2f4");
        b.returnBoard()[4][4] = "P";
        b.returnBoard()[1][4] = "";
        b.returnBoard()[7][1] = "";
        b.returnBoard()[5][2] = "n";
        b.returnBoard()[6][3] = "";
        b.returnBoard()[6][5] = "";
        b.returnBoard()[4][3] = "p";
        b.returnBoard()[4][5] = "p";
        StringList possibleMoves = m.pawnMoves(4, 4, "P", b.returnBoard());

        assertEquals(2, possibleMoves.size());
    }
    
}
