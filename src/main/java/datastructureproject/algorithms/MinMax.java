package main.java.datastructureproject.algorithms;

import java.util.*;
import main.java.datastructureproject.init.*;
import chess.engine.*;
import chess.model.Side;

public class MinMax {
    /**
     * Moves is used to generate all the possible moves that can be made
     */
    private Moves moves;

    /**
     * Used to track the current board of the game
     */
    private String[][] board;

    /**
     * Gamestate is used to check things like the side playing
     */
    private GameState gs;

    public MinMax(Board board, GameState gs) {
        this.board = board.returnBoard();
        this.gs = gs;
        this.moves = new Moves(board, gs);
    }

    /**
     * Tries to find the move which nets up the most value by first checking the value of the move and after that the potential loss
     * @return moves which have the most value by summing the potential gain and loss
     */
    public ArrayList<String> minmaxMove() {
        ArrayList<String> moves = this.moves.allMovesForSide(gs.playing, this.board);
        
        if (gs.playing == Side.WHITE) {
            int max = Integer.MAX_VALUE;
            ArrayList<String> bestMoves = new ArrayList<>();
            for (String s : moves) {
                String[][] tempBoard = new String[8][8];
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        tempBoard[i][j] = this.board[i][j];
                    }
                }
                String temp = this.moves.convertBackFromUCI(s);
                String temp2[] = temp.split("");
                int potentialGain = value(Integer.valueOf(temp2[2]), Integer.valueOf(temp2[3]), tempBoard);
                tempBoard[Integer.valueOf(temp2[2])][Integer.valueOf(temp2[3])] = tempBoard[Integer.valueOf(temp2[0])][Integer.valueOf(temp2[1])];
                tempBoard[Integer.valueOf(temp2[0])][Integer.valueOf(temp2[1])] = "";
                int potentialLoss = potentialLoss("white", tempBoard);
                if (potentialGain + potentialLoss < max) {
                    max = potentialGain + potentialLoss;
                    bestMoves.clear();
                    bestMoves.add(s);
                }
                if (potentialGain + potentialLoss == max) {
                    bestMoves.add(s);
                }
            }
            return bestMoves;
        } else {
            int max = Integer.MIN_VALUE;
            ArrayList<String> bestMoves = new ArrayList<>();
            for (String s : moves) {
                String[][] tempBoard = new String[8][8];
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        tempBoard[i][j] = this.board[i][j];
                    }
                }
                String temp = this.moves.convertBackFromUCI(s);
                String temp2[] = temp.split("");
                int potentialGain = value(Integer.valueOf(temp2[2]), Integer.valueOf(temp2[3]), tempBoard);
                tempBoard[Integer.valueOf(temp2[2])][Integer.valueOf(temp2[3])] = tempBoard[Integer.valueOf(temp2[0])][Integer.valueOf(temp2[1])];
                tempBoard[Integer.valueOf(temp2[0])][Integer.valueOf(temp2[1])] = "";
                int potentialLoss = potentialLoss("black", tempBoard);
                if (potentialGain + potentialLoss > max) {
                    max = potentialGain + potentialLoss;
                    bestMoves.clear();
                    bestMoves.add(s);
                }
                if (potentialGain + potentialLoss == max) {
                    bestMoves.add(s);
                }
            }
            return bestMoves;
        }
    }

    /**
     * Checks the value of a potential move
     * @param i vertical axis
     * @param j horizontal axis
     * @param tboard temporary board
     * @return value of the move
     */

    public int value(int i, int j, String[][] tboard) {
        String piece = tboard[i][j];
        switch(piece) {
            case "q":
                return 90;
            case "Q":
                return -90;
            case "r":
                return 50;
            case "R":
                return -50;
            case "b":
                return 30;
            case "B":
                return -30;
            case "n":
                return 30;
            case "N":
                return -30;
            case "p":
                return 10;
            case "P":
                return -10;
            case "k":
                return 1000000000;
            case "K":
                return -1000000000;
            default:
                return 0;
        }
    }

    /**
     * Checks the highest loss from a move by playing it on a temporary board before hand
     * @param side checks the opposite sides moves with values
     * @param tboard temporary board
     * @return highest loss from a move that an opponent can do
     */

    public int potentialLoss(String side, String[][] tboard) {
        if (side.equals("white")) {
            int loss = Integer.MIN_VALUE;
            ArrayList<String> opponentMoves = this.moves.allMovesForSide(Side.BLACK, tboard);
            for (String s : opponentMoves) {
                String temp = this.moves.convertBackFromUCI(s);
                String[] temp2 = temp.split("");
                if (value(Integer.valueOf(temp2[2]), Integer.valueOf(temp2[3]), tboard) > loss) {
                    loss = value(Integer.valueOf(temp2[2]), Integer.valueOf(temp2[3]), tboard);
                }
            }
            return loss;
        } else {
            int loss = Integer.MAX_VALUE;
            ArrayList<String> opponentMoves = this.moves.allMovesForSide(Side.WHITE, tboard);
            for (String s : opponentMoves) {
                String temp = this.moves.convertBackFromUCI(s);
                String[] temp2 = temp.split("");
                if (value(Integer.valueOf(temp2[2]), Integer.valueOf(temp2[3]), tboard) < loss) {
                    loss = value(Integer.valueOf(temp2[2]), Integer.valueOf(temp2[3]), tboard);
                }
            }
            return loss;
        }
    }


}
