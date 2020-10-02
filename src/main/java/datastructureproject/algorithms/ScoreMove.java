package main.java.datastructureproject.algorithms;

/**
 * This class is used mostly by the alpha-beta pruning algorithm
 *  so it can return both the best move it got, and the value for it.
 */

public class ScoreMove {


    private int score;
    private String move;
    

    public ScoreMove(int score, String move) {
        this.score = score;
        this.move = move;
    }

    public String returnMove() {
        return move;
    }

    public int returnScore() {
        return score;
    }
}