package main.java.datastructureproject.algorithms;

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
    private int[][] whitePawnSquareTable = new int[][] {
            {0,  0,  0,  0,  0,  0,  0,  0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {5,  5, 10, 25, 25, 10,  5,  5},
            {0,  0,  0, 20, 20,  0,  0,  0},
            {5, -5,-10,  0,  0,-10, -5,  5},
            {5, 10, 10,-20,-20, 10, 10,  5},
            {0,  0,  0,  0,  0,  0,  0,  0}
        };

    private int[][] blackPawnSquareTable = new int[][] {
        {0,  0,  0,  0,  0,  0,  0,  0},
        {5, 10, 10,-20,-20, 10, 10,  5},
        {5, -5,-10,  0,  0,-10, -5,  5},
        {0,  0,  0, 20, 20,  0,  0,  0},
        {5,  5, 10, 25, 25, 10,  5,  5},
        {10, 10, 20, 30, 30, 20, 10, 10}, 
        {50, 50, 50, 50, 50, 50, 50, 50}, 
        {0,  0,  0,  0,  0,  0,  0,  0}
    };

    private int[][] whiteKnightSquareTable = new int[][] {
        {-50,-40,-30,-30,-30,-30,-40,-50},
        {-40,-20,  0,  0,  0,  0,-20,-40},
        {-30,  0, 10, 15, 15, 10,  0,-30},
        {-30,  5, 15, 20, 20, 15,  5,-30},
        {-30,  0, 15, 20, 20, 15,  0,-30},
        {-30,  5, 10, 15, 15, 10,  5,-30},
        {-40,-20,  0,  5,  5,  0,-20,-40},
        {-50,-40,-30,-30,-30,-30,-40,-50}
    };

    private int[][] blackKnightSquareTable = new int[][] {
        {-50,-40,-30,-30,-30,-30,-40,-50},
        {-40,-20,  0,  5,  5,  0,-20,-40},
        {-30,  5, 10, 15, 15, 10,  5,-30},
        {-30,  0, 15, 20, 20, 15,  0,-30},
        {-30,  5, 15, 20, 20, 15,  5,-30},
        {-30,  0, 10, 15, 15, 10,  0,-30},
        {-40,-20,  0,  0,  0,  0,-20,-40},
        {-50,-40,-30,-30,-30,-30,-40,-50}
    };

    private int[][] whiteBishopSquareTable = new int[][] {
        {-20,-10,-10,-10,-10,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5, 10, 10,  5,  0,-10},
        {-10,  5,  5, 10, 10,  5,  5,-10},
        {-10,  0, 10, 10, 10, 10,  0,-10},
        {-10, 10, 10, 10, 10, 10, 10,-10},
        {-10,  5,  0,  0,  0,  0,  5,-10},
        {-20,-10,-10,-10,-10,-10,-10,-20}
    };

    private int[][] blackBishopSquareTable = new int[][] {
        {-20,-10,-10,-10,-10,-10,-10,-20},
        {-10,  5,  0,  0,  0,  0,  5,-10},
        {-10, 10, 10, 10, 10, 10, 10,-10},
        {-10,  0, 10, 10, 10, 10,  0,-10},
        {-10,  5,  5, 10, 10,  5,  5,-10},
        {-10,  0,  5, 10, 10,  5,  0,-10},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-20,-10,-10,-10,-10,-10,-10,-20}
    };

    private int[][] whiteRookSquareTable = new int[][] {
        {0,  0,  0,  0,  0,  0,  0,  0},
        {5, 10, 10, 10, 10, 10, 10,  5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {0,  0,  0,  5,  5,  0,  0,  0}
    };

    private int[][] blackRookSquareTable = new int[][] {
        {0,  0,  0,  5,  5,  0,  0,  0},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {5, 10, 10, 10, 10, 10, 10,  5},
        {0,  0,  0,  0,  0,  0,  0,  0}
    };

    private int[][] whiteQueenSquareTable = new int[][] {
        {-20,-10,-10, -5, -5,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5,  5,  5,  5,  0,-10},
        {-5,  0,  5,  5,  5,  5,  0, -5},
        {0,  0,  5,  5,  5,  5,  0, -5},
        {-10,  5,  5,  5,  5,  5,  0,-10},
        {-10,  0,  5,  0,  0,  0,  0,-10},
        {-20,-10,-10, -5, -5,-10,-10,-20}
    };

    private int[][] blackQueenSquareTable = new int[][] {
        {-20,-10,-10, -5, -5,-10,-10,-20},
        {-10,  0,  5,  0,  0,  0,  0,-10},
        {-10,  5,  5,  5,  5,  5,  0,-10},
        {0,  0,  5,  5,  5,  5,  0, -5},
        {-5,  0,  5,  5,  5,  5,  0, -5},
        {-10,  0,  5,  5,  5,  5,  0,-10},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-20,-10,-10, -5, -5,-10,-10,-20}
    };

    private int[][] whiteKingMiddleGameSquareTable = new int[][] {
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-20,-30,-30,-40,-40,-30,-30,-20},
        {-10,-20,-20,-20,-20,-20,-20,-10},
        {20, 20,  0,  0,  0,  0, 20, 20},
        {20, 30, 10,  0,  0, 10, 30, 20}
    };

    private int[][] blackKingMiddleGameSquareTable = new int[][] {
        {20, 30, 10,  0,  0, 10, 30, 20},
        {20, 20,  0,  0,  0,  0, 20, 20},
        {-10,-20,-20,-20,-20,-20,-20,-10},
        {-20,-30,-30,-40,-40,-30,-30,-20},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30}    
    };

    private int[][] whiteKingEndGameSquareTable = new int[][] {
        {-50,-40,-30,-20,-20,-30,-40,-50},
        {-30,-20,-10,  0,  0,-10,-20,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-30,  0,  0,  0,  0,-30,-30},
        {-50,-30,-30,-30,-30,-30,-30,-50}
    };

    private int[][] blackKingEndGameSquareTable = new int[][] {
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
     * @param eval evaluation value of our current board
     * @return ScoreMove which is just an object with the best score and what move gives it
     */
    private ScoreMove alphaBeta(String[][] tboard, int depth, String side, int alpha, int beta, String move) {
        if (depth <= 0) {
            return new ScoreMove(evaluate(tboard), "");
        } else {
            if (side.equals("white")) {
                String bestMove = "";
                StringList allMovesForSide = this.moves.allMovesForSide(Side.WHITE, tboard);

                allMovesForSide = sortByHighestValueMove(allMovesForSide, tboard);

                for (int i = 0; i < allMovesForSide.size(); i++) {
                    String m = allMovesForSide.get(i);
                    String temp = this.moves.convertBackFromUCI(m);
                    String temp2[] = temp.split("");
                    String saveValue = tboard[Integer.valueOf(temp2[2])][Integer.valueOf(temp2[3])];

                    this.bClass.doMove(temp, tboard);

                    ScoreMove sm = alphaBeta(tboard, depth - 1, "black", alpha, beta, bestMove);
                   
                    this.bClass.undoMove(temp, tboard, saveValue);

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
                StringList allMovesForSide = this.moves.allMovesForSide(Side.BLACK, tboard);
                
                allMovesForSide = sortByLowestValueMove(allMovesForSide, tboard);
                
                for (int i = 0; i < allMovesForSide.size(); i++) {
                    String m = allMovesForSide.get(i);
                    String temp = this.moves.convertBackFromUCI(m);
                    String temp2[] = temp.split("");
                    String saveValue = tboard[Integer.valueOf(temp2[2])][Integer.valueOf(temp2[3])];
                    
                    this.bClass.doMove(temp, tboard);

                    ScoreMove sm = alphaBeta(tboard, depth - 1, "white", alpha, beta, bestMove);
                    
                    this.bClass.undoMove(temp, tboard, saveValue);
                    
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
     * Does minmax search with alpha-beta pruning to get the best move from our available moves.
     * @return best move
     */
    public String minMaxMove() {
        String[][] tempBoard = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tempBoard[i][j] = this.board[i][j];
            }
        }
        
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        int depth = 4;

        if (gs.playing == Side.WHITE) {
            ScoreMove sm = alphaBeta(tempBoard, depth, "white", alpha, beta, "");
            
            return sm.returnMove();
            
        } else {  
            ScoreMove sm = alphaBeta(tempBoard, depth, "black", alpha, beta, "");
            
            return sm.returnMove();
        }
    }

    

    /**
    * Heurastic evaluation given the board as a parameter.
    * @param tboard temporary board to check heuristic of a move
    * @return evaluation of the current board
    */
    public int evaluate(String[][] tboard) {
        int value = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tboard[i][j].equals("k")) {
                    value += 20000;
                    if (gs.getTurnCount() > 12 && gs.getTurnCount() <= 28) {
                        value += (whiteKingMiddleGameSquareTable[i][j]);
                    } else if (gs.getTurnCount() > 28) {
                        value += (whiteKingEndGameSquareTable[i][j]);
                    }
                } else if (tboard[i][j].equals("K")) {
                    value -= 20000;
                    if (gs.getTurnCount() > 12 && gs.getTurnCount() <= 28) {
                        value -= (blackKingMiddleGameSquareTable[i][j]);
                    } else if (gs.getTurnCount() > 28) {
                        value -= (blackKingEndGameSquareTable[i][j]);
                    }
                } else if (tboard[i][j].equals("q")) {
                    value += 900 + (whiteQueenSquareTable[i][j]);
                } else if (tboard[i][j].equals("Q")) {
                    value -= 900 - (blackQueenSquareTable[i][j]);
                } else if (tboard[i][j].equals("n")) {
                    value += 320 + (whiteKnightSquareTable[i][j]);
                } else if (tboard[i][j].equals("N")) {
                    value -= 320 - (blackKnightSquareTable[i][j]);
                } else if (tboard[i][j].equals("b")) {
                    value += 330 + (whiteBishopSquareTable[i][j]);
                } else if (tboard[i][j].equals("B")) {
                    value -= 330 - (blackBishopSquareTable[i][j]);
                } else if (tboard[i][j].equals("r")) {
                    value += 500 + (whiteRookSquareTable[i][j]);
                } else if (tboard[i][j].equals("R")) {
                    value -= 500 - (blackRookSquareTable[i][j]);
                } else if (tboard[i][j].equals("p")) {
                    value += 100 + (whitePawnSquareTable[i][j]);
                } else if (tboard[i][j].equals("P")) {
                    value -= 100 - (blackPawnSquareTable[i][j]);
                }
            }
        }
        return value;
    }

    /**
    * We do an heurastic evaluation for a move and sort them by the highest evaluation.
    * @param moveList generated moves which are to be sorted
    * @param tboard the current board
    * @return all moves sorted
    */
    public StringList sortByHighestValueMove(StringList moveList, String[][] tboard) {
        for (int i = 1; i < moveList.size() - 1; i++) {
            int j = i - 1;
            while (j >= 0 && evaluateMove(moveList.get(j), tboard) < evaluateMove(moveList.get(j + 1), tboard)) {
                String storeMove = moveList.get(j);
                moveList.set(j, moveList.get(j + 1));
                moveList.set(j + 1, storeMove);
                j -= 1;
            }
        }

        return moveList;
    }

    /**
     * We do an heurastic evaluation for a move and sort them by the lowest evaluation.
     * @param moveList generated moves which are to be sorted
     * @param tboard the current board
     * @return all moves sorted
     */
    public StringList sortByLowestValueMove(StringList moveList, String[][] tboard) {
        for (int i = 1; i < moveList.size() - 1; i++) {
            int j = i - 1;
            while (j >= 0 && evaluateMove(moveList.get(j), tboard) > evaluateMove(moveList.get(j + 1), tboard)) {
                String storeMove = moveList.get(j);
                moveList.set(j, moveList.get(j + 1));
                moveList.set(j + 1, storeMove);
                j -= 1;
            }
        }

        return moveList;
    }
    
    /**
     * Evaluation for an spesific move. We just do a given move on to the board and return the evaluation from it.
     * @param move given move
     * @param tboard temporary board on which the move is played to
     * @return heuristic evaluation
     */
    public int evaluateMove(String move, String[][] tboard) {
        String[][] tempBoard = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tempBoard[i][j] = tboard[i][j];
            }
        }

        this.bClass.doMove(this.moves.convertBackFromUCI(move), tempBoard);

        return evaluate(tempBoard);
    }


}
