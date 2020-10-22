package test.java.datastructureproject.init;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import main.java.datastructureproject.init.*;


public class BoardTest {

    Board b;
    
    @Before
    public void setUp() {
        this.b = new Board();
    }

    @Test
    public void doMove() {
        String[][] test = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test[i][j] = b.returnBoard()[i][j];
            }
        }

        b.doMove("1121", b.returnBoard());
        test[1][1] = "";
        test[2][1] = "P";

        assertEquals(test, b.returnBoard());
    }

    @Test
    public void undoMove() {
        String[][] test = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test[i][j] = b.returnBoard()[i][j];
            }
        }

        b.doMove("1121", b.returnBoard());
        b.undoMove("1121", b.returnBoard(), "");

        assertEquals(test, b.returnBoard());
    }

    @Test
    public void tryCastle() {
        String[][] test = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test[i][j] = b.returnBoard()[i][j];
            }
        }
        test[0][0] = "";
        test[0][1] = "";
        test[0][2] = "K";
        test[0][3] = "R";
        test[0][4] = "";
        b.returnBoard()[0][1] = "";
        b.returnBoard()[0][2] = "";
        b.returnBoard()[0][3] = "";
        b.doMove("0402", b.returnBoard());

        assertEquals(test, b.returnBoard());
    }

    @Test
    public void undoCastle() {
        String[][] test = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test[i][j] = b.returnBoard()[i][j];
            }
        }
        test[0][1] = "";
        test[0][2] = "";
        test[0][3] = "";
        b.returnBoard()[0][1] = "";
        b.returnBoard()[0][2] = "";
        b.returnBoard()[0][3] = "";
        b.doMove("0402", b.returnBoard());
        b.undoMove("0402", b.returnBoard(), "");

        assertEquals(test, b.returnBoard());
    }

    @Test
    public void undoPromote() {
        String[][] test = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test[i][j] = b.returnBoard()[i][j];
            }
        }
        test[1][1] = "";
        test[6][1] = "P";
        b.returnBoard()[1][1] = "";
        b.returnBoard()[6][1] = "P";

        b.doMove("6170Q", b.returnBoard());
        b.undoMove("6170Q", b.returnBoard(), "r");

        assertEquals(test, b.returnBoard());
    }

    @Test
    public void undoPromoteWhite() {
        String[][] test = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test[i][j] = b.returnBoard()[i][j];
            }
        }
        test[6][1] = "";
        test[1][1] = "p";
        b.returnBoard()[6][1] = "";
        b.returnBoard()[1][1] = "p";

        b.doMove("1102Q", b.returnBoard());
        b.undoMove("1102Q", b.returnBoard(), "B");

        assertEquals(test, b.returnBoard());
    }

    @Test
    public void undoEnPassant() {
        String[][] test = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test[i][j] = b.returnBoard()[i][j];
            }
        }
        test[6][0] = "";
        test[1][1] = "";
        test[3][0] = "p";
        test[3][1] = "P";
        b.returnBoard()[6][0] = "";
        b.returnBoard()[1][1] = "";
        b.returnBoard()[3][0] = "p";
        b.returnBoard()[3][1] = "P";

        b.doMove("3021+", b.returnBoard());
        b.undoMove("3021+", b.returnBoard(), "P");

        assertEquals(test, b.returnBoard());
    }

}
