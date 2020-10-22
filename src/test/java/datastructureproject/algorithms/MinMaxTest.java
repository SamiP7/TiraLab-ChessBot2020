package test.java.datastructureproject.algorithms;

import org.junit.Before;
import org.junit.Test;
import chess.model.Side;
import chess.engine.GameState;
import static org.junit.Assert.*;
import main.java.datastructureproject.algorithms.*;
import main.java.datastructureproject.init.Board;

public class MinMaxTest {
    Board b;
    MinMax m;
    GameState gs;

    @Before
    public void setUp() {
        this.b = new Board();
        this.gs = new GameState();
        this.m = new MinMax(b, gs);
    }

    @Test
    public void testFastCheckmate() {
        b.doMove("6444", b.returnBoard());
        b.doMove("1434", b.returnBoard());
        b.doMove("7542", b.returnBoard());
        b.doMove("0122", b.returnBoard());
        b.doMove("7337", b.returnBoard());
        b.doMove("0625", b.returnBoard());
        gs.playing = Side.WHITE;
        assertEquals("h5f7", m.minMaxMove());
    }

    @Test
    public void testBlockFastCheckmate() {
        b.doMove("6444", b.returnBoard());
        b.doMove("1434", b.returnBoard());
        b.doMove("7542", b.returnBoard());
        b.doMove("0122", b.returnBoard());
        b.doMove("7337", b.returnBoard());
        gs.playing = Side.BLACK;
        assertEquals("g7g6", m.minMaxMove());
    }

    @Test
    public void goodQueenCapture() {
        b.doMove("6444", b.returnBoard());
        b.doMove("1323", b.returnBoard());
        b.doMove("7346", b.returnBoard());
        gs.playing = Side.BLACK;
        assertEquals("c8g4", m.minMaxMove());
    }
}
