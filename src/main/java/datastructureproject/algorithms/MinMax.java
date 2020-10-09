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

    /**
     * Used for making moves on temporary boards
     */
    private Board bClass;


    /**
     * These arrays are used to get an estimation of how beneficial current board positions are
     */
    private int[][] WhitePawnSquareTable = new int[][] {
            {0,  0,  0,  0,  0,  0,  0,  0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {5,  5, 10, 25, 25, 10,  5,  5},
            {0,  0,  0, 20, 20,  0,  0,  0},
            {5, -5,-10,  0,  0,-10, -5,  5},
            {5, 10, 10,-20,-20, 10, 10,  5},
            {0,  0,  0,  0,  0,  0,  0,  0}
        };

        private int[][] BlackPawnSquareTable = new int[][] {
            {0,  0,  0,  0,  0,  0,  0,  0},
            {5, 10, 10,-20,-20, 10, 10,  5},
            {5, -5,-10,  0,  0,-10, -5,  5},
            {0,  0,  0, 20, 20,  0,  0,  0},
            {5,  5, 10, 25, 25, 10,  5,  5},
            {10, 10, 20, 30, 30, 20, 10, 10}, 
            {50, 50, 50, 50, 50, 50, 50, 50}, 
            {0,  0,  0,  0,  0,  0,  0,  0}
        };

        private int[][] WhiteKnightSquareTable = new int[][] {
            {-50,-40,-30,-30,-30,-30,-40,-50},
            {-40,-20,  0,  0,  0,  0,-20,-40},
            {-30,  0, 10, 15, 15, 10,  0,-30},
            {-30,  5, 15, 20, 20, 15,  5,-30},
            {-30,  0, 15, 20, 20, 15,  0,-30},
            {-30,  5, 10, 15, 15, 10,  5,-30},
            {-40,-20,  0,  5,  5,  0,-20,-40},
            {-50,-40,-30,-30,-30,-30,-40,-50}
        };

        private int[][] BlackKnightSquareTable = new int[][] {
            {-50,-40,-30,-30,-30,-30,-40,-50},
            {-40,-20,  0,  5,  5,  0,-20,-40},
            {-30,  5, 10, 15, 15, 10,  5,-30},
            {-30,  0, 15, 20, 20, 15,  0,-30},
            {-30,  5, 15, 20, 20, 15,  5,-30},
            {-30,  0, 10, 15, 15, 10,  0,-30},
            {-40,-20,  0,  0,  0,  0,-20,-40},
            {-50,-40,-30,-30,-30,-30,-40,-50}
        };

        private int[][] WhiteBishopSquareTable = new int[][] {
            {-20,-10,-10,-10,-10,-10,-10,-20},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-10,  0,  5, 10, 10,  5,  0,-10},
            {-10,  5,  5, 10, 10,  5,  5,-10},
            {-10,  0, 10, 10, 10, 10,  0,-10},
            {-10, 10, 10, 10, 10, 10, 10,-10},
            {-10,  5,  0,  0,  0,  0,  5,-10},
            {-20,-10,-10,-10,-10,-10,-10,-20}
        };

        private int[][] BlackBishopSquareTable = new int[][] {
            {-20,-10,-10,-10,-10,-10,-10,-20},
            {-10,  5,  0,  0,  0,  0,  5,-10},
            {-10, 10, 10, 10, 10, 10, 10,-10},
            {-10,  0, 10, 10, 10, 10,  0,-10},
            {-10,  5,  5, 10, 10,  5,  5,-10},
            {-10,  0,  5, 10, 10,  5,  0,-10},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-20,-10,-10,-10,-10,-10,-10,-20}
        };

        private int[][] WhiteRookSquareTable = new int[][] {
            {0,  0,  0,  0,  0,  0,  0,  0},
            {5, 10, 10, 10, 10, 10, 10,  5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {0,  0,  0,  5,  5,  0,  0,  0}
        };

        private int[][] BlackRookSquareTable = new int[][] {
            {0,  0,  0,  5,  5,  0,  0,  0},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {5, 10, 10, 10, 10, 10, 10,  5},
            {0,  0,  0,  0,  0,  0,  0,  0}
        };

        private int[][] WhiteQueenSquareTable = new int[][] {
            {-20,-10,-10, -5, -5,-10,-10,-20},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-10,  0,  5,  5,  5,  5,  0,-10},
            {-5,  0,  5,  5,  5,  5,  0, -5},
            { 0,  0,  5,  5,  5,  5,  0, -5},
            {-10,  5,  5,  5,  5,  5,  0,-10},
            {-10,  0,  5,  0,  0,  0,  0,-10},
            {-20,-10,-10, -5, -5,-10,-10,-20}
        };

        private int[][] BlackQueenSquareTable = new int[][] {
            {-20,-10,-10, -5, -5,-10,-10,-20},
            {-10,  0,  5,  0,  0,  0,  0,-10},
            {-10,  5,  5,  5,  5,  5,  0,-10},
            {0,  0,  5,  5,  5,  5,  0, -5},
            {-5,  0,  5,  5,  5,  5,  0, -5},
            {-10,  0,  5,  5,  5,  5,  0,-10},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-20,-10,-10, -5, -5,-10,-10,-20}
          };

        private int[][] WhiteKingMiddleGameSquareTable = new int[][] {
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-20,-30,-30,-40,-40,-30,-30,-20},
            {-10,-20,-20,-20,-20,-20,-20,-10},
            {20, 20,  0,  0,  0,  0, 20, 20},
            {20, 30, 10,  0,  0, 10, 30, 20}
        };

        private int[][] BlackKingMiddleGameSquareTable = new int[][] {
            {20, 30, 10,  0,  0, 10, 30, 20},
            {20, 20,  0,  0,  0,  0, 20, 20},
            {-10,-20,-20,-20,-20,-20,-20,-10},
            {-20,-30,-30,-40,-40,-30,-30,-20},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30}    
        };

        private int[][] WhiteKingEndGameSquareTable = new int[][] {
            {-50,-40,-30,-20,-20,-30,-40,-50},
            {-30,-20,-10,  0,  0,-10,-20,-30},
            {-30,-10, 20, 30, 30, 20,-10,-30},
            {-30,-10, 30, 40, 40, 30,-10,-30},
            {-30,-10, 30, 40, 40, 30,-10,-30},
            {-30,-10, 20, 30, 30, 20,-10,-30},
            {-30,-30,  0,  0,  0,  0,-30,-30},
            {-50,-30,-30,-30,-30,-30,-30,-50}
        };

        private int[][] BlackKingEndGameSquareTable = new int[][] {
            {-50,-30,-30,-30,-30,-30,-30,-50},
            {-30,-30,  0,  0,  0,  0,-30,-30},
            {-30,-10, 20, 30, 30, 20,-10,-30},
            {-30,-10, 30, 40, 40, 30,-10,-30},
            {-30,-10, 30, 40, 40, 30,-10,-30},
            {-30,-10, 20, 30, 30, 20,-10,-30},
            {-30,-20,-10,  0,  0,-10,-20,-30},
            {-50,-40,-30,-20,-20,-30,-40,-50},
        };

    public MinMax(Board board, GameState gs) {
        this.board = board.returnBoard();
        this.gs = gs;
        this.moves = new Moves(board, gs);
        this.bClass = board;
    }


    /**
     * Minmax algorithm with alpha-beta pruning.
     * @param tboard temporary board on which moves are played
     * @param depth how far in to the tree the algorithm goes
     * @param side whose turn is it
     * @param alpha
     * @param beta
     * @return ScoreMove which is just an object with the best score and what move gives it
     */
    private ScoreMove alphaBeta(String[][] tboard, int depth, String side, int alpha, int beta, String move) {
        if (depth <= 0) {
            return new ScoreMove(evaluate(tboard), new String(""));
        } else {
            String[][] tempBoard = new String[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    tempBoard[i][j] = tboard[i][j];
                }
            }
            if (side.equals("white")) {
                String bestMove = "";
                ArrayList<String> allMovesForSide = this.moves.allMovesForSide(Side.WHITE, tboard);

                for (String m : allMovesForSide) {
                    String temp = this.moves.convertBackFromUCI(m);
                    this.bClass.doMove(temp, tempBoard);
                    ScoreMove sm = alphaBeta(tempBoard, depth - 1, "black", alpha, beta, bestMove);
                    String temp2[] = temp.split("");
                    tempBoard[Integer.valueOf(temp2[0])][Integer.valueOf(temp2[1])] = tboard[Integer.valueOf(temp2[0])][Integer.valueOf(temp2[1])];
                    tempBoard[Integer.valueOf(temp2[2])][Integer.valueOf(temp2[3])] = tboard[Integer.valueOf(temp2[2])][Integer.valueOf(temp2[3])];
                    if (sm.returnScore() > alpha) {
                        alpha = sm.returnScore();
                        bestMove = m;                       
                    }
                    if (alpha >= beta) {
                        break;
                    }
                    
                }
                return new ScoreMove(alpha, bestMove);

            } else {
                String bestMove = "";
                ArrayList<String> allMovesForSide = this.moves.allMovesForSide(Side.BLACK, tboard);
                for (String m : allMovesForSide) {
                    
                    String temp = this.moves.convertBackFromUCI(m);
                    this.bClass.doMove(temp, tempBoard);
                    ScoreMove sm = alphaBeta(tempBoard, depth - 1, "white", alpha, beta, bestMove);
                    String temp2[] = temp.split("");
                    tempBoard[Integer.valueOf(temp2[0])][Integer.valueOf(temp2[1])] = tboard[Integer.valueOf(temp2[0])][Integer.valueOf(temp2[1])];
                    tempBoard[Integer.valueOf(temp2[2])][Integer.valueOf(temp2[3])] = tboard[Integer.valueOf(temp2[2])][Integer.valueOf(temp2[3])];
                    if (sm.returnScore() < beta) {
                        beta = sm.returnScore();
                        bestMove = m;
                        
                    }
                    if (alpha >= beta) {
                        break;
                    }
                    
                }
                return new ScoreMove(beta, bestMove);
            }
        }
    }


    /**
     * First scans the nearest children of the starting node. Implemented this way in case we have several contenders for the best move to make.
     * After that it runs alpha-beta on all the nodes we got and returns the best contenders as a list.
     * @return List of all the best moves we can make after alpha-beta pruning all the possible moves.
     */
    public ArrayList<String> minMaxMove() {
        //ArrayList<String> allMovesForSide = this.moves.allMovesForSide(gs.playing, this.board);
        if (gs.playing == Side.WHITE) {
            ArrayList<String> bestMoves = new ArrayList<>();
            int alpha = Integer.MIN_VALUE;
            int beta = Integer.MAX_VALUE;
            
            ScoreMove sm = alphaBeta(this.board, 4, "white", alpha, beta, "");
            bestMoves.add(sm.returnMove());
            
            return bestMoves;
            
        } else {
            ArrayList<String> bestMoves = new ArrayList<>();
            int alpha = Integer.MIN_VALUE;
            int beta = Integer.MAX_VALUE;
            
            ScoreMove sm = alphaBeta(this.board, 4, "black", alpha, beta, "");
            bestMoves.add(sm.returnMove());
            
            return bestMoves;
        }
        
        
    }


    /**
    * Heurastic evaluation given the board as a parameter.
    * @param tboard temporary board to check heuristic of a move
    * @return
    */
    public int evaluate(String[][] tboard) {
        int value = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tboard[i][j].equals("k")) {
                    value += 20000;
                    if (gs.getTurnCount() > 12 && gs.getTurnCount() <= 28) {
                        value += (WhiteKingMiddleGameSquareTable[i][j]);
                    } else if (gs.getTurnCount() > 28) {
                        value += (WhiteKingEndGameSquareTable[i][j]);
                    }
                }
                if (tboard[i][j].equals("K")) {
                    value -= 20000;
                    if (gs.getTurnCount() > 12 && gs.getTurnCount() <= 28) {
                        value -= (BlackKingMiddleGameSquareTable[i][j]);
                    } else if (gs.getTurnCount() > 28) {
                        value -= (BlackKingEndGameSquareTable[i][j]);
                    }
                }
                if (tboard[i][j].equals("q")) {
                    value += 900 + (WhiteQueenSquareTable[i][j]);
                }
                if (tboard[i][j].equals("Q")) {
                    value -= 900 - (BlackQueenSquareTable[i][j]);
                }
                if (tboard[i][j].equals("n")) {
                    value += 320 + (WhiteKnightSquareTable[i][j]);
                }
                if (tboard[i][j].equals("N")) {
                    value -= 320 - (BlackKnightSquareTable[i][j]);
                }
                if (tboard[i][j].equals("b")) {
                    value += 330 + (WhiteBishopSquareTable[i][j]);
                }
                if (tboard[i][j].equals("B")) {
                    value -= 330 - (BlackBishopSquareTable[i][j]);
                }
                if (tboard[i][j].equals("r")) {
                    value += 500 + (WhiteRookSquareTable[i][j]);
                }
                if (tboard[i][j].equals("R")) {
                    value -= 500 - (BlackRookSquareTable[i][j]);
                }
                if (tboard[i][j].equals("p")) {
                    value += 100 + (WhitePawnSquareTable[i][j]);
                }
                if (tboard[i][j].equals("P")) {
                    value -= 100 - (BlackPawnSquareTable[i][j]);
                }
            }
        }
        return value;
    }

    


}
