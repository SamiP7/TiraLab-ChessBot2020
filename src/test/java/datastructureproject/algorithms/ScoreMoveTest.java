package test.java.datastructureproject.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;
import logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
