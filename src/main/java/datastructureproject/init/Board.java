package main.java.datastructureproject.init;

import java.util.*;

public class Board {

    private String[][] board;

    public Board() {
        this.board = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = "";
            }
        }
        newGame();
    }

    /**
     * Creates a standard piece output at the start of a chess game
     */
    public void newGame() {
        String[] bStart = new String[]{"r", "n", "b", "q", "k", "b", "n", "r"};
        String[] wStart = new String[]{"R", "N", "B", "Q", "K", "B", "N", "R"};
        for (int i = 0; i < 8; i++) {
            board[6][i] = "p";
            board[1][i] = "P";
            board[7][i] = bStart[i];
            board[0][i] = wStart[i];
        }
    }

    /**
     * Returns the board as an array which can then be used elsewhere to analyze the board
     * @return The current layout of the board as an array
     */
    public String[][] returnBoard() {
        return board;
    }

    /**
     * Makes a move on the board. If statements are used to check if the move is special, like en passant, castling or promotion.
     * @param move Given an 4 value string consisting of values inbetween 0-7, it uses the first 2 values as
     * a starting position and the latter ones as the destination
     */
    public void doMove(String move) {
        String[] temp = new String[5];
        temp = move.split("");
        if (board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])].equals("k") && Integer.valueOf(temp[1]) != Integer.valueOf(temp[3]) + 1
                &&  Integer.valueOf(temp[1]) != Integer.valueOf(temp[3]) - 1 &&  Integer.valueOf(temp[0]) == Integer.valueOf(temp[2])) {
            if (Integer.valueOf(temp[3]) > Integer.valueOf(temp[1])) {
                board[7][7] = "";
                board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])] = "";
                board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1]) + 1] = "r";
                board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])] = "k";
                return;
            }
            if (Integer.valueOf(temp[3]) < Integer.valueOf(temp[1])) {
                board[7][0] = "";
                board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])] = "";
                board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1]) - 1] = "r";
                board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])] = "k";
                return;
            }
        }
        if (board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])].equals("K") && Integer.valueOf(temp[1]) != Integer.valueOf(temp[3]) + 1
                &&  Integer.valueOf(temp[1]) != Integer.valueOf(temp[3]) - 1 &&  Integer.valueOf(temp[0]) == Integer.valueOf(temp[2])) {
            if (Integer.valueOf(temp[3]) > Integer.valueOf(temp[1])) {
                board[0][7] = "";
                board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])] = "";
                board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1]) + 1] = "R";
                board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])] = "K";
                return;
            }
            if (Integer.valueOf(temp[3]) < Integer.valueOf(temp[1])) {
                board[0][0] = "";
                board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])] = "";
                board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1]) - 1] = "R";
                board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])] = "K";
                return;
            }
        }
        if (move.length() > 4) {
            if (Integer.valueOf(temp[2]) == 7) {
                board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])] = temp[4].toUpperCase();
                board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])] = "";
                return;
            } else {
                board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])] = temp[4].toLowerCase();
                board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])] = "";
            }
            
        }
        if (board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])].equals("p") && board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])].equals("")
                && (Integer.valueOf(temp[1]) - 1 == Integer.valueOf(temp[3]) || Integer.valueOf(temp[1]) + 1 == Integer.valueOf(temp[3]))) {
            board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])] = board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])];
            board[Integer.valueOf(temp[2]) - 1][Integer.valueOf(temp[3])] = "";
            board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])] = "";
            return;
        }
        if (board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])].equals("P") && board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])].equals("")
                && (Integer.valueOf(temp[1]) - 1 == Integer.valueOf(temp[3]) || Integer.valueOf(temp[1]) + 1 == Integer.valueOf(temp[3]))) {
            board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])] = board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])];
            board[Integer.valueOf(temp[2]) - 1][Integer.valueOf(temp[3])] = "";
            board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])] = "";
            return;
        }
        board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])] = board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])];
        board[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])] = "";
    }
    
}