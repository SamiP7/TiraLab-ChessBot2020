package main.java.datastructureproject.init;

import main.java.datastructureproject.init.*;
import java.util.*;

public class Moves {
    /**
     * Board is pretty much constructed as an [][] array so it's easy to keep track of the
     * current situation with this implementation
    */
    private String[][] board;

    public Moves(Board board) {
        this.board = board.returnBoard();
    }
    /**
     * Checks all the possible moves that a knight can make and returns it as an ArrayList
     * @param sI starting vertical
     * @param sJ starting horizontal
     * @param p piece value but mostly used to check color
     * @return
     */
    public ArrayList<String> knightMoves(int sI, int sJ, Pieces p) {
        ArrayList<String> moves = new ArrayList<>();
        HashSet<String> movesTemp = new HashSet<>();
        ArrayList<String> convert = new ArrayList<>();
        if (possibleMoves(sI + 1, sJ + 2, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ + 2)));
        }
        if (possibleMoves(sI + 2, sJ + 1, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 2) + String.valueOf(sJ + 1)));
        }
        if (possibleMoves(sI + 1, sJ - 2, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ - 2)));
        }
        if (possibleMoves(sI + 2, sJ - 1, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 2) + String.valueOf(sJ - 1)));
        }
        if (possibleMoves(sI - 1, sJ + 2, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ + 2)));
        }
        if (possibleMoves(sI - 2, sJ + 1, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 2) + String.valueOf(sJ + 1)));
        }
        if (possibleMoves(sI - 2, sJ - 1, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 2) + String.valueOf(sJ - 1)));
        }
        if (possibleMoves(sI - 1, sJ - 2, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ - 2)));
        }
        convert.addAll(movesTemp);
        for (String i : convert) {
            moves.add(convertToUCI(i));
        }
        return moves;
    }

    /**
     * Checks all the possible moves that a queen can make and returns it as an ArrayList
     * @param sI starting vertical
     * @param sJ starting horizontal
     * @param p piece value but mostly used to check color
     * @return
     */

    public ArrayList<String> queenMoves(int sI, int sJ, Pieces p) {
        ArrayList<String> moves = new ArrayList<>();
        moves.addAll(bishopMoves(sI,sJ,p));
        moves.addAll(rookMoves(sI,sJ,p));
        return moves;
    }

    /**
     * Checks all the possible moves that a bishop can make and returns it as an ArrayList
     * @param sI starting vertical
     * @param sJ starting horizontal
     * @param p piece value but mostly used to check color
     * @return
     */
    
    public ArrayList<String> bishopMoves(int sI, int sJ, Pieces p) {
        ArrayList<String> moves = new ArrayList<>();
        HashSet<String> movesTemp = new HashSet<>();
        ArrayList<String> convert = new ArrayList<>();
        for (int i = sI + 1; i < 8; i++) {
            int c = possibleMoves(i, sJ + (i - sI), p);
            boolean shouldBreak = true;
            switch (c) {
                case (0):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(i) + String.valueOf(sJ + (i - sI))));
                    shouldBreak = false;
                case(1):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(i) + String.valueOf(sJ + (i - sI))));
                    break;
                case(-1):
                    break;
            }
            if (shouldBreak) {
                break;
            }
        }
        for (int i = sI + 1; i < 8; i++) {
            int c = possibleMoves(i, sJ - (i - sI), p);
            boolean shouldBreak = true;
            switch (c) {
                case (0):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(i) + String.valueOf(sJ - (i - sI))));
                    shouldBreak = false;
                case(1):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(i) + String.valueOf(sJ - (i - sI))));
                    break;
                case(-1):
                    break;
            }
            if (shouldBreak) {
                break;
            }
        }
        for (int i = sJ + 1; i < 8; i++) {
            int c = possibleMoves(sI + (i - sJ), i, p);
            boolean shouldBreak = true;
            switch (c) {
                case (0):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + (i - sJ)) + String.valueOf(i)));
                    shouldBreak = false;
                case(1):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + (i - sJ)) + String.valueOf(i)));
                    break;
                case(-1):
                    break;
            }
            if (shouldBreak) {
                break;
            }
        }
        for (int i = sJ + 1; i < 8; i++) {
            int c = possibleMoves(sI - (i - sJ), i, p);
            boolean shouldBreak = true;
            switch (c) {
                case (0):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - (i - sJ)) + String.valueOf(i)));
                    shouldBreak = false;
                case(1):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - (i - sJ)) + String.valueOf(i)));
                    break;
                case(-1):
                    break;
            }
            if (shouldBreak) {
                break;
            }
        }
        convert.addAll(movesTemp);
        for (String i : convert) {
            moves.add(convertToUCI(i));
        }
        return moves;
    }
    /**
     * Checks all the possible moves that a rook can make and returns it as an ArrayList
     * @param sI starting vertical
     * @param sJ starting horizontal
     * @param p piece value but mostly used to check color
     * @return
     */
    public ArrayList<String> rookMoves(int sI, int sJ, Pieces p) {
        ArrayList<String> moves = new ArrayList<>();
        HashSet<String> movesTemp = new HashSet<>();
        ArrayList<String> convert = new ArrayList<>();
        for (int i = sI + 1; i < 8; i++) {
            int c = possibleMoves(i, sJ, p);
            boolean shouldBreak = true;
            switch (c) {
                case (0):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(i) + String.valueOf(sJ)));
                    shouldBreak = false;
                case (1):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(i) + String.valueOf(sJ)));
                    break;
                case (-1):
                    break;
            }
            if (shouldBreak) {
                break;
            }
        }
        for (int i = sI - 1; i >= 0; i--) {
            int c = possibleMoves(i, sJ, p);
            boolean shouldBreak = true;
            switch (c) {
                case (0):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(i) + String.valueOf(sJ)));
                    shouldBreak = false;
                case (1):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(i) + String.valueOf(sJ)));
                    break;
                case (-1):
                    break;
            }
            if (shouldBreak) {
                break;
            }
        }
        for (int i = sJ + 1; i < 8; i++) {
            int c = possibleMoves(sI, i, p);
            boolean shouldBreak = true;
            switch (c) {
                case (0):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI) + String.valueOf(i)));
                    shouldBreak = false;
                case (1):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI) + String.valueOf(i)));
                    break;
                case (-1):
                    break;
            }
            if (shouldBreak) {
                break;
            }
        }
        for (int i = sJ - 1; i >= 0; i--) {
            int c = possibleMoves(sI, i, p);
            boolean shouldBreak = true;
            switch (c) {
                case (0):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI) + String.valueOf(i)));
                    shouldBreak = false;
                case (1):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI) + String.valueOf(i)));
                    break;
                case (-1):
                    break;
            }
            if (shouldBreak) {
                break;
            }
        }
        convert.addAll(movesTemp);
        for (String i : convert) {
            moves.add(convertToUCI(i));
        }
        return moves;
    }
    /**
     * Checks all the possible moves that a king can make and returns it as an ArrayList
     * @param sI starting vertical
     * @param sJ starting horizontal
     * @param p piece value but mostly used to check color
     * @return
     */
    public ArrayList<String> kingMoves(int sI, int sJ, Pieces p) {
        ArrayList<String> moves = new ArrayList<>();
        HashSet<String> movesTemp = new HashSet<>();
        ArrayList<String> convert = new ArrayList<>();
        if (possibleMoves(sI + 1, sJ + 1, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ + 1)));
        }
        if (possibleMoves(sI + 1, sJ, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ)));
        }
        if (possibleMoves(sI + 1, sJ - 1, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ - 1)));
        }
        if (possibleMoves(sI - 1, sJ + 1, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ + 1)));
        }
        if (possibleMoves(sI - 1, sJ, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ)));
        }
        if (possibleMoves(sI - 1, sJ - 1, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ - 1)));
        }
        if (possibleMoves(sI, sJ + 1, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI) + String.valueOf(sJ + 1)));
        }
        if (possibleMoves(sI, sJ - 1, p) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI) + String.valueOf(sJ - 1)));
        }
        convert.addAll(movesTemp);
        for (String i : convert) {
            moves.add(convertToUCI(i));
        }
        return moves;
    }
    /**
     * Checks all the possible moves that a pawn can make and returns it as an ArrayList
     * @param sI starting vertical
     * @param sJ starting horizontal
     * @param p piece value but mostly used to check color
     * @return
     */
    public ArrayList<String> pawnMoves(int sI, int sJ, Pieces p) {
        ArrayList<String> moves = new ArrayList<>();
        HashSet<String> movesTemp = new HashSet<>();
        ArrayList<String> convert = new ArrayList<>();
        String piece = p.toString();
        char pawn = piece.charAt(0);
        if (Character.isUpperCase(pawn)) {
            if (sI == 1 && possibleMoves(sI + 2, sJ, p) == 0 && possibleMoves(sI + 1, sJ, p) == 0) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 2) + String.valueOf(sJ)));
            }
            if (possibleMoves(sI + 1, sJ, p) == 0) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ)));
            }
            if (possibleMoves(sI + 1, sJ + 1, p) == 1) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ + 1)));
            }
            if (possibleMoves(sI + 1, sJ - 1, p) == 1) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ - 1)));
            }    
        } else {
            if (sI == 6 && possibleMoves(sI - 2, sJ, p) == 0 && possibleMoves(sI - 1, sJ, p) == 0) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 2) + String.valueOf(sJ)));
            }
            if (possibleMoves(sI - 1, sJ, p) == 0 ) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ)));
            }
            if (possibleMoves(sI - 1, sJ + 1, p) == 1 ) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ + 1)));
            }
            if (possibleMoves(sI - 1, sJ - 1, p) == 1 ) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ - 1)));
            }
        }
        convert.addAll(movesTemp);
        for (String i : convert) {
            moves.add(convertToUCI(i));
        }
        return moves;
    }

    /**
     * This is used to check if a move can be made or not
     * @param i current vertical value
     * @param j current horizontal value
     * @param p gets a piece value which is pretty much only used for to check if it's a capital or not
     * @return -1 if possible move can't be made, 0 if there are no "hostile" pieces there and 1 if there are
     */

    public int possibleMoves(int i, int j, Pieces p) {
        if (i < 0 || j < 0 || i > 7 || j > 7) {
            return -1;
        }
        String piece = p.toString();
        char piecee = piece.charAt(0);
        if (Character.isUpperCase(piecee)) {
            if (board[i][j].isBlank()) {
                return 0;
            } else {
                String pie = board[i][j];
                char pi = pie.charAt(0);
                if (Character.isLowerCase(pi)) {
                    return 1;
                }
                return -1;
            }
        } else {
            if (board[i][j].isBlank()) {
                return 0;
            } else {
                String pie = board[i][j];
                char pi = pie.charAt(0);
                if (Character.isUpperCase(pi)) {
                    return 1;
                }
                return -1;
            }
        }
        
    }

    /**
     * Converts a string with 4 values inbetween 0-7 which the board class uses to an UCI so it can make moves
     * on the lichess server
     * @param s given string
     * @return string converted to UCI
     */
    
    public String convertToUCI(String s) {
        String[] temp = new String[5];
        temp = s.split("");
        String uci = "";
        for (int i = 0; i < temp.length; i++) {
            if (i == 1 || i == 3) {
                switch (temp[i]) {
                    case ("0"):
                        uci+="a";
                        break;
                    case ("1"):
                        uci+="b";
                        break;
                    case ("2"):
                        uci+="c";
                        break;
                    case ("3"):
                        uci+="d";
                        break;
                    case ("4"):
                        uci+="e";
                        break;
                    case ("5"):
                        uci+="f";
                        break;
                    case ("6"):
                        uci+="g";
                        break;
                    case ("7"):
                        uci+="h";
                        break;
                }
            } else {
                uci+=String.valueOf(Math.abs(Integer.valueOf(temp[i]) - 8));
            }
        }
        String[] temp2 = new String[5];
        temp2 = uci.split("");
        String final_uci = "";
        
        final_uci+=temp2[1]+temp2[0]+temp2[3]+temp2[2];
        if (temp2.length > 4) {
            final_uci+=temp[4];
        }
        
        return final_uci;
    }

    /**
     * Converts an UCI strings to a string consisting inbetween values of 0-7 which the Board class can read
     * @param s given UCI snippet
     * @return string consistint of 4 values inbetween 0-7
     */

    public String convertBackFromUCI(String s) {
        String[] temp = new String[5];
        temp = s.split("");
        String reg = "";
        for (int i = 0; i < temp.length; i++) {
            if (i == 0 || i == 2) {
                switch (temp[i]) {
                    case ("A"):
                        reg+="0";
                        break;
                    case ("a"):
                        reg+="0";
                        break;
                    case ("B"):
                        reg+="1";
                        break;
                    case ("b"):
                        reg+="1";
                        break;
                    case ("C"):
                        reg+="2";
                        break;
                    case ("c"):
                        reg+="2";
                        break;
                    case ("D"):
                        reg+="3";
                        break;
                    case ("d"):
                        reg+="3";
                        break;
                    case ("E"):
                        reg+="4";
                        break;
                    case ("e"):
                        reg+="4";
                        break;
                    case ("F"):
                        reg+="5";
                        break;
                    case ("f"):
                        reg+="5";
                        break;
                    case ("G"):
                        reg+="6";
                        break;
                    case ("g"):
                        reg+="6";
                        break;
                    case ("H"):
                        reg+="7";
                        break;
                    case ("h"):
                        reg+="7";
                        break;
                }
            } else {
                reg+=String.valueOf(Math.abs(Integer.valueOf(temp[i]) - 8));
            }
        }
        String[] temp2 = new String[5];
        temp2 = reg.split("");
        String final_reg = "";
        
        final_reg+=temp2[1]+temp2[0]+temp2[3]+temp2[2];
        if (temp2.length > 4) {
            final_reg+=temp[4];
        }
        return final_reg;
    }
    /**
     * @return all possible moves that the bot can do
     */
    public ArrayList<String> allMovesForBot() {
        ArrayList<String> allMoves = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == "p") {
                        ArrayList<String> temp = pawnMoves(i, j, new Pieces("p"));
                        allMoves.addAll(temp);
                    }
                    if (board[i][j] == "b") {
                        ArrayList<String> temp = bishopMoves(i, j, new Pieces("b"));
                        allMoves.addAll(temp);
                    }
                    if (board[i][j] == "r") {
                        ArrayList<String> temp = rookMoves(i, j, new Pieces("r"));
                        allMoves.addAll(temp);
                    }
                    if (board[i][j] == "n") {
                        ArrayList<String> temp = knightMoves(i, j, new Pieces("n"));
                        allMoves.addAll(temp);
                    }
                    if (board[i][j] == "q") {
                        ArrayList<String> temp = queenMoves(i, j, new Pieces("q"));
                        allMoves.addAll(temp);
                    }
                    if (board[i][j] == "k") {
                        ArrayList<String> temp = kingMoves(i, j, new Pieces("k"));
                        allMoves.addAll(temp);
                    }
                    
                }
            }
        
        return allMoves;
    }
}
