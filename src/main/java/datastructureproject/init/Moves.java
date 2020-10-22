package main.java.datastructureproject.init;


import chess.model.Side;
import chess.engine.GameState;

public class Moves {
    /**
     * Board is pretty much constructed as an [][] array so it's easy to keep track of the
     * current situation with this implementation
    */
    private String[][] board;

    /**
     * Gamestate is used for later when checking thing's like castling and en passant,
     *  since it's easy to check previous moves and positions with it
     */
    private GameState gs;

    public Moves(Board board, GameState gs) {
        this.board = board.returnBoard();
        this.gs = gs;
    }
    /**
     * Checks all the possible moves that a knight can make and returns it as an ArrayList
     * @param sI starting vertical
     * @param sJ starting horizontal
     * @param p piece value but mostly used to check color
     * @param tboard temporary board to test possibilities with
     * @return all the moves that a knight can do
     */
    public StringList knightMoves(int sI, int sJ, String p, String[][] tboard) {
        StringList moves = new StringList();
        StringList movesTemp = new StringList();
        if (possibleMoves(sI + 1, sJ + 2, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ + 2)));
        }
        if (possibleMoves(sI + 2, sJ + 1, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 2) + String.valueOf(sJ + 1)));
        }
        if (possibleMoves(sI + 1, sJ - 2, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ - 2)));
        }
        if (possibleMoves(sI + 2, sJ - 1, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 2) + String.valueOf(sJ - 1)));
        }
        if (possibleMoves(sI - 1, sJ + 2, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ + 2)));
        }
        if (possibleMoves(sI - 2, sJ + 1, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 2) + String.valueOf(sJ + 1)));
        }
        if (possibleMoves(sI - 2, sJ - 1, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 2) + String.valueOf(sJ - 1)));
        }
        if (possibleMoves(sI - 1, sJ - 2, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ - 2)));
        }
        for (int i = 0; i < movesTemp.size(); i++) {
            moves.addUnique(convertToUCI(movesTemp.get(i)));
        }
        return moves;
    }

    /**
     * Checks all the possible moves that a queen can make and returns it as an ArrayList
     * @param sI starting vertical
     * @param sJ starting horizontal
     * @param p piece value but mostly used to check color
     * @param tboard temporary board to test possibilities with
     * @return all the moves that a queen can do
     */

    public StringList queenMoves(int sI, int sJ, String p, String[][] tboard) {
        StringList moves = new StringList();
        moves.addAll(bishopMoves(sI, sJ, p, tboard));
        moves.addAll(rookMoves(sI, sJ, p, tboard));
        return moves;
    }

    /**
     * Checks all the possible moves that a bishop can make and returns it as an ArrayList
     * @param sI starting vertical
     * @param sJ starting horizontal
     * @param p piece value but mostly used to check color
     * @param tboard temporary board to test possibilities with
     * @return all the moves that a bishop can do
     */
    
    public StringList bishopMoves(int sI, int sJ, String p, String[][] tboard) {
        StringList moves = new StringList();
        StringList movesTemp = new StringList();
        for (int i = sI + 1; i < 8; i++) {
            int c = possibleMoves(i, sJ + (i - sI), p, tboard);
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
            int c = possibleMoves(i, sJ - (i - sI), p, tboard);
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
        for (int i = sI - 1; i >= 0; i--) {
            int c = possibleMoves(i, sJ + (sI - i), p, tboard);
            boolean shouldBreak = true;
            switch (c) {
                case (0):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(i) + String.valueOf(sJ + (sI - i))));
                    shouldBreak = false;
                case(1):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(i) + String.valueOf(sJ + (sI - i))));
                    break;
                case(-1):
                    break;
            }
            if (shouldBreak) {
                break;
            }
        }
        for (int i = sI - 1; i >= 0; i--) {
            int c = possibleMoves(i, sJ - (sI - i), p, tboard);
            boolean shouldBreak = true;
            switch (c) {
                case (0):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(i) + String.valueOf(sJ - (sI - i))));
                    shouldBreak = false;
                case(1):
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(i) + String.valueOf(sJ - (sI - i))));
                    break;
                case(-1):
                    break;
            }
            if (shouldBreak) {
                break;
            }
        }
        
        for (int i = 0; i < movesTemp.size(); i++) {
            moves.addUnique(convertToUCI(movesTemp.get(i)));
        }
        return moves;
    }
    /**
     * Checks all the possible moves that a rook can make and returns it as an ArrayList
     * @param sI starting vertical
     * @param sJ starting horizontal
     * @param p piece value but mostly used to check color
     * @param tboard temporary board to test possibilities with
     * @return all the moves that a rook can do
     */
    public StringList rookMoves(int sI, int sJ, String p, String[][] tboard) {
        StringList moves = new StringList();
        StringList movesTemp = new StringList();
        for (int i = sI + 1; i < 8; i++) {
            int c = possibleMoves(i, sJ, p, tboard);
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
            int c = possibleMoves(i, sJ, p, tboard);
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
            int c = possibleMoves(sI, i, p, tboard);
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
            int c = possibleMoves(sI, i, p, tboard);
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
        for (int i = 0; i < movesTemp.size(); i++) {
            moves.addUnique(convertToUCI(movesTemp.get(i)));
        }
        return moves;
    }
    /**
     * Checks all the possible moves that a king can make and returns it as an ArrayList
     * @param sI starting vertical
     * @param sJ starting horizontal
     * @param p piece value but mostly used to check color
     * @param tboard temporary board to test possibilities with
     * @param side which color the king is
     * @return all the moves that a king can do
     */
    public StringList kingMoves(int sI, int sJ, String p, String[][] tboard, String side) {
        StringList moves = new StringList();
        StringList movesTemp = new StringList();
        if (possibleMoves(sI + 1, sJ + 1, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ + 1)));
        }
        if (possibleMoves(sI + 1, sJ, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ)));
        }
        if (possibleMoves(sI + 1, sJ - 1, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ - 1)));
        }
        if (possibleMoves(sI - 1, sJ + 1, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ + 1)));
        }
        if (possibleMoves(sI - 1, sJ, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ)));
        }
        if (possibleMoves(sI - 1, sJ - 1, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ - 1)));
        }
        if (possibleMoves(sI, sJ + 1, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI) + String.valueOf(sJ + 1)));
        }
        if (possibleMoves(sI, sJ - 1, p, tboard) >= 0) {
            movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI) + String.valueOf(sJ - 1)));
        }
        String[][] temporary = new String[8][8];
        String[][] temporary2 = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                temporary[i][j] = tboard[i][j];
                temporary2[i][j] = tboard[i][j];
            }
        }
        if (side.equals("white")) {
            if (canKingCastleKingSide(side, temporary)) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI) + String.valueOf(sJ + 2)));

            }
            if (canKingCastleQueenSide(side, temporary2)) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI) + String.valueOf(sJ - 2)));

            }
        }
        if (!side.equals("white")) {
            if (canKingCastleKingSide(side, temporary)) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI) + String.valueOf(sJ + 2)));

            }
            if (canKingCastleQueenSide(side, temporary2)) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI) + String.valueOf(sJ - 2)));

            }
        }
        
        for (int i = 0; i < movesTemp.size(); i++) {
            moves.addUnique(convertToUCI(movesTemp.get(i)));
        }
        return moves;
    }
    /**
     * Checks all the possible moves that a pawn can make and returns it as an ArrayList
     * @param sI starting vertical
     * @param sJ starting horizontal
     * @param p piece value but mostly used to check color
     * @param tboard temporary board to test possibilities with
     * @return all the moves that a pawn can do
     */
    public StringList pawnMoves(int sI, int sJ, String p, String[][] tboard) {
        StringList moves = new StringList();
        StringList movesTemp = new StringList();
        String piece = p.toString();
        char pawn = piece.charAt(0);
        if (Character.isUpperCase(pawn)) {
            if (sI == 1 && possibleMoves(sI + 2, sJ, p, tboard) == 0 && possibleMoves(sI + 1, sJ, p, tboard) == 0) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 2) + String.valueOf(sJ)));
            }
            if (possibleMoves(sI + 1, sJ, p, tboard) == 0) {
                if (sI + 1 == 7) {
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ)) + new String("Q"));
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ)) + new String("N"));
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ)) + new String("B"));
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ)) + new String("R"));
                } else {
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ)));
                }
            }
            if (possibleMoves(sI + 1, sJ + 1, p, tboard) == 1) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ + 1)));
            }
            if (possibleMoves(sI + 1, sJ - 1, p, tboard) == 1) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ - 1)));
            }
            if (enPassant()) {
                String see = convertBackFromUCI(gs.getLatestMove());
                String temp[] = see.split("");
                if (possibleMoves(sI + 1, sJ + 1, p, tboard) == 0 && Integer.valueOf(temp[2]) == sI && Integer.valueOf(temp[3]) == sJ + 1) {
                    moves.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ + 1) + "+"));
                }
                if (possibleMoves(sI + 1, sJ - 1, p, tboard) == 0 && Integer.valueOf(temp[2]) == sI && Integer.valueOf(temp[3]) == sJ - 1) {
                    moves.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI + 1) + String.valueOf(sJ - 1) + "+"));
                }
            } 
        } else {
            if (sI == 6 && possibleMoves(sI - 2, sJ, p, tboard) == 0 && possibleMoves(sI - 1, sJ, p, tboard) == 0) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 2) + String.valueOf(sJ)));
            }
            if (possibleMoves(sI - 1, sJ, p, tboard) == 0) {
                if (sI - 1 == 0) {
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ)) + new String("q"));
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ)) + new String("n"));
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ)) + new String("b"));
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ)) + new String("r"));
                } else {
                    movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ)));
                }
            }
            if (possibleMoves(sI - 1, sJ + 1, p, tboard) == 1) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ + 1)));
            }
            if (possibleMoves(sI - 1, sJ - 1, p, tboard) == 1) {
                movesTemp.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ - 1)));
            }
            if (enPassant()) {
                String see = convertBackFromUCI(gs.getLatestMove());
                String temp[] = see.split("");
                if (possibleMoves(sI - 1, sJ + 1, p, tboard) == 0 && Integer.valueOf(temp[2]) == sI && Integer.valueOf(temp[3]) == sJ + 1) {
                    moves.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ + 1) + "+"));
                }
                if (possibleMoves(sI - 1, sJ - 1, p, tboard) == 0 && Integer.valueOf(temp[2]) == sI && Integer.valueOf(temp[3]) == sJ - 1) {
                    moves.add(new String(String.valueOf(sI) + String.valueOf(sJ) + String.valueOf(sI - 1) + String.valueOf(sJ - 1) + "+"));
                }
            }
        }
        for (int i = 0; i < movesTemp.size(); i++) {
            moves.addUnique(convertToUCI(movesTemp.get(i)));
        }
        return moves;
    }

    /**
     * This is used to check if a move can be made or not
     * @param i current vertical value
     * @param j current horizontal value
     * @param p gets a String which is pretty much only used for to check whether if it's a capital letter or not
     * @param tboard temporary board to test moves with
     * @return -1 if possible move can't be made, 0 if there are no "hostile" pieces there and 1 if there are
     */

    public int possibleMoves(int i, int j, String p, String[][] tboard) {
        if (i < 0 || j < 0 || i > 7 || j > 7) {
            return -1;
        }
        char piece = p.charAt(0);
        if (Character.isUpperCase(piece)) {
            if (tboard[i][j].isBlank()) {
                return 0;
            } else {
                String pie = tboard[i][j];
                char pi = pie.charAt(0);
                if (Character.isLowerCase(pi)) {
                    return 1;
                } else {
                    return -1;
                }
            }
        } else {
            if (tboard[i][j].isBlank()) {
                return 0;
            } else {
                String pie = tboard[i][j];
                char pi = pie.charAt(0);
                if (Character.isUpperCase(pi)) {
                    return 1;
                } else {
                    return -1;
                }
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
                        uci += "a";
                        break;
                    case ("1"):
                        uci += "b";
                        break;
                    case ("2"):
                        uci += "c";
                        break;
                    case ("3"):
                        uci += "d";
                        break;
                    case ("4"):
                        uci += "e";
                        break;
                    case ("5"):
                        uci += "f";
                        break;
                    case ("6"):
                        uci += "g";
                        break;
                    case ("7"):
                        uci += "h";
                        break;
                }
            } else if (i == 0 || i == 2) {
                uci += String.valueOf(Math.abs(Integer.valueOf(temp[i]) - 8));
            }
        }
        String[] temp2 = new String[5];
        temp2 = uci.split("");
        String finalUci = "";
        
        finalUci += temp2[1] + temp2[0] + temp2[3] + temp2[2];
        if (s.length() > 4) {
            finalUci += temp[4];
        }
        
        return finalUci;
    }

    /**
     * Converts an UCI strings to a string consisting inbetween values of 0-7 which the Board class can read
     * @param s given UCI snippet
     * @return string consisting of 4 values inbetween 0-7
     */

    public String convertBackFromUCI(String s) {
        String[] temp = new String[5];
        temp = s.split("");
        String reg = "";
        for (int i = 0; i < temp.length; i++) {
            if (i == 0 || i == 2) {
                switch (temp[i]) {
                    case ("A"):
                        reg += "0";
                        break;
                    case ("a"):
                        reg += "0";
                        break;
                    case ("B"):
                        reg += "1";
                        break;
                    case ("b"):
                        reg += "1";
                        break;
                    case ("C"):
                        reg += "2";
                        break;
                    case ("c"):
                        reg += "2";
                        break;
                    case ("D"):
                        reg += "3";
                        break;
                    case ("d"):
                        reg += "3";
                        break;
                    case ("E"):
                        reg += "4";
                        break;
                    case ("e"):
                        reg += "4";
                        break;
                    case ("F"):
                        reg += "5";
                        break;
                    case ("f"):
                        reg += "5";
                        break;
                    case ("G"):
                        reg += "6";
                        break;
                    case ("g"):
                        reg += "6";
                        break;
                    case ("H"):
                        reg += "7";
                        break;
                    case ("h"):
                        reg += "7";
                        break;
                }
            } else if (i == 1 || i == 3) {
                reg += String.valueOf(Math.abs(Integer.valueOf(temp[i]) - 8));
            }
        }
        String[] temp2 = new String[5];
        temp2 = reg.split("");
        String finalReg = "";
        
        finalReg += temp2[1] + temp2[0] + temp2[3] + temp2[2];
        if (s.length() > 4) {
            finalReg += temp[4];
        }
        return finalReg;
    }
    /**
     * Used to generate all the possible moves a side can make.
     * @param side which side moves are generated
     * @param tboard temporary board which can be used to generate future moves
     * @return possible moves
     */
    public StringList allMovesForSide(Side side, String[][] tboard) {
        StringList allMoves = new StringList();
        if (side == Side.WHITE) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (tboard[i][j].equals("q")) {
                        StringList temp = queenMoves(i, j, "q", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("b")) {
                        StringList temp = bishopMoves(i, j, "b", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("r")) {
                        StringList temp = rookMoves(i, j, "r", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("n")) {
                        StringList temp = knightMoves(i, j, "n", tboard);
                        allMoves.addAll(temp);
                    }                   
                    if (tboard[i][j].equals("k")) {
                        StringList temp = kingMoves(i, j, "k", tboard, "white");
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("p")) {
                        StringList temp = pawnMoves(i, j, "p", tboard);
                        allMoves.addAll(temp);
                    }
                    
                }
            }
            StringList allMovesIfKingInCheck = new StringList();
            for (int i = 0; i < allMoves.size(); i++) {
                String temp = convertBackFromUCI(allMoves.get(i));
                String[] temp2 = temp.split("");
                String[][] testBoard = new String[8][8];
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 8; k++) {
                        testBoard[j][k] = tboard[j][k];
                    }
                }
                testBoard[Integer.valueOf(temp2[2])][Integer.valueOf(temp2[3])] = testBoard[Integer.valueOf(temp2[0])][Integer.valueOf(temp2[1])];
                testBoard[Integer.valueOf(temp2[0])][Integer.valueOf(temp2[1])] = "";
                if (!isKingInCheck("white", testBoard)) {
                    allMovesIfKingInCheck.add(allMoves.get(i));
                }
            }
            return allMovesIfKingInCheck;

        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (tboard[i][j].equals("Q")) {
                        StringList temp = queenMoves(i, j, "Q", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("B")) {
                        StringList temp = bishopMoves(i, j, "B", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("R")) {
                        StringList temp = rookMoves(i, j, "R", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("N")) {
                        StringList temp = knightMoves(i, j, "N", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("K")) {
                        StringList temp = kingMoves(i, j, "K", tboard, "black");
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("P")) {
                        StringList temp = pawnMoves(i, j, "P", tboard);
                        allMoves.addAll(temp);
                    }
                }
            }
            StringList allMovesIfKingInCheck = new StringList();
            for (int i = 0; i < allMoves.size(); i++) {
                String temp = convertBackFromUCI(allMoves.get(i));
                String[] temp2 = temp.split("");
                String[][] testBoard = new String[8][8];
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 8; k++) {
                        testBoard[j][k] = tboard[j][k];
                    }
                }
                testBoard[Integer.valueOf(temp2[2])][Integer.valueOf(temp2[3])] = testBoard[Integer.valueOf(temp2[0])][Integer.valueOf(temp2[1])];
                testBoard[Integer.valueOf(temp2[0])][Integer.valueOf(temp2[1])] = "";
                if (!isKingInCheck("black", testBoard)) {
                    allMovesIfKingInCheck.add(allMoves.get(i));
                }
            }
            return allMovesIfKingInCheck;
        }
        
    }

    /**
     * Checks if the king is in check from the given board. Pretty much used for to check if king is check after a certain move
     *  to reduce the amount of possible moves.
     * @param color specifies the side so it checks the opposing side moves
     * @param tboard temporary board used so it doesn't affect the played one
     * @return false if the king is not in check after a certain move, true otherwise
     */

    public boolean isKingInCheck(String color, String[][] tboard) {
        StringList allMoves = new StringList();
        if (color.equals("white")) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (tboard[i][j].equals("P")) {
                        StringList temp = pawnMoves(i, j, "P", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("B")) {
                        StringList temp = bishopMoves(i, j, "B", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("R")) {
                        StringList temp = rookMoves(i, j, "R", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("N")) {
                        StringList temp = knightMoves(i, j, "N", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("Q")) {
                        StringList temp = queenMoves(i, j, "Q", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("K")) {
                        StringList temp = kingMoves(i, j, "K", tboard, "black");
                        allMoves.addAll(temp);
                    }
                    
                }
            }
            for (int i = 0; i < allMoves.size(); i++) {
                String temp = convertBackFromUCI(allMoves.get(i));
                String temp2[] = temp.split("");
                if (tboard[Integer.valueOf(temp2[2])][Integer.valueOf(temp2[3])].equals("k")) {
                    return true;
                }
            }
            return false;
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (tboard[i][j].equals("p")) {
                        StringList temp = pawnMoves(i, j, "p", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("b")) {
                        StringList temp = bishopMoves(i, j, "b", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("r")) {
                        StringList temp = rookMoves(i, j, "r", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("n")) {
                        StringList temp = knightMoves(i, j, "n", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("q")) {
                        StringList temp = queenMoves(i, j, "q", tboard);
                        allMoves.addAll(temp);
                    }
                    if (tboard[i][j].equals("k")) {
                        StringList temp = kingMoves(i, j, "k", tboard, "white");
                        allMoves.addAll(temp);
                    }
                }
            }
            for (int i = 0; i < allMoves.size(); i++) {
                String temp = convertBackFromUCI(allMoves.get(i));
                String temp2[] = temp.split("");
                if (tboard[Integer.valueOf(temp2[2])][Integer.valueOf(temp2[3])].equals("K")) {
                    return true;
                }
                
            }
            return false;
        }
    }

    /**
     * Checks whether an en passant move can be made
     * @return true if move is technically possible, false otherwise
     */
    public boolean enPassant() {
        if (!gs.moves.isEmpty()) {
            String see = convertBackFromUCI(gs.getLatestMove());
            String temp[] = see.split("");
            if (board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])].equals("p")) {
                if (Integer.valueOf(temp[0]) - 2 == Integer.valueOf(temp[2])) {
                    return true;
                }
            }
            if (board[Integer.valueOf(temp[2])][Integer.valueOf(temp[3])].equals("P")) {
                if (Integer.valueOf(temp[0]) + 2 == Integer.valueOf(temp[2])) {
                    return true;
                }
                
            }
        }
        return false;
    }

    /**
     * Used to check whether castling is possible by first checking if king has moved
     * @param side which color the king is
     * @return true if king has moved, false otherwise
     */    
    public boolean hasKingMoved(String side) {
        if (side.equals("white")) {
            for (String s : gs.moves) {
                String[] temp = s.split("");
                if (temp[0].equals("e") && temp[1].equals("1")) {
                    return true;
                }
            }
        } else {
            for (String s : gs.moves) {
                String[] temp = s.split("");
                if (temp[0].equals("e") && temp[1].equals("8")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the rook on the king side has moved. Used for castling
     * @param side color of the rook
     * @return true if it has moved, false otherwise
     */
    public boolean hasRookMovedKingSide(String side) {
        if (side.equals("white")) {
            for (String s : gs.moves) {
                String[] temp = s.split("");
                if ((temp[0].equals("h") && temp[1].equals("1")) || (temp[2].equals("h") && temp[3].equals("1"))) {
                    return true;
                }
            }
        } else {
            for (String s : gs.moves) {
                String[] temp = s.split("");
                if ((temp[0].equals("h") && temp[1].equals("8")) || (temp[2].equals("h") && temp[3].equals("8"))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the rook on the queen side has moved. Used for castling
     * @param side color of the rook
     * @return true if it has moved, false otherwise
     */
    public boolean hasRookMovedQueenSide(String side) {
        if (side.equals("white")) {
            for (String s : gs.moves) {
                String[] temp = s.split("");
                if ((temp[0].equals("a") && temp[1].equals("1")) || (temp[0].equals("a") && temp[1].equals("1"))) {
                    return true;
                }
            }
        } else {
            for (String s : gs.moves) {
                String[] temp = s.split("");
                if ((temp[0].equals("a") && temp[1].equals("8")) || (temp[0].equals("a") && temp[1].equals("8"))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if all the conditions for castling are in order for king side
     * @param side color of the king
     * @param tboard temporary board on which we can place temporary kings to check that they not in check
     * @return true if castling is possible, false otherwise
     */
    public boolean canKingCastleKingSide(String side, String[][] tboard) {
        if (side.equals("white")) {
            if (tboard[7][5].equals("") && tboard[7][6].equals("") && tboard[7][4].equals("k") && tboard[7][7].equals("r")) {
                tboard[7][5] = "k";
                tboard[7][6] = "k";
                if (!hasRookMovedKingSide(side) && !hasKingMoved(side) && !isKingInCheck(side, tboard)) {
                    return true;
                }
            }
        } else {
            if (tboard[0][5].equals("") && tboard[0][6].equals("") && tboard[0][4].equals("K") && tboard[0][7].equals("R")) {
                tboard[0][5] = "K";
                tboard[0][6] = "K";
                if (!hasRookMovedKingSide(side) && !hasKingMoved(side) && !isKingInCheck(side, tboard)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if all the conditions for castling are in order for queen side
     * @param side color of the king
     * @param tboard temporary board on which we can place temporary kings to check that they not in check
     * @return true if castling is possible, false otherwise
     */
    public boolean canKingCastleQueenSide(String side, String[][] tboard) {
        if (side.equals("white")) {
            if (tboard[7][3].equals("") && tboard[7][2].equals("") && tboard[7][1].equals("") && tboard[7][4].equals("k") && tboard[7][0].equals("r")) {
                tboard[7][3] = "k";
                tboard[7][2] = "k";
                if (!hasRookMovedQueenSide(side) && !hasKingMoved(side) && !isKingInCheck(side, tboard)) {
                    return true;
                }
            }
        } else {
            if (tboard[0][3].equals("") && tboard[0][2].equals("") && tboard[0][1].equals("") && tboard[0][4].equals("K") && tboard[0][0].equals("R")) {
                tboard[0][3] = "K";
                tboard[0][2] = "K";
                if (!hasRookMovedQueenSide(side) && !hasKingMoved(side) && !isKingInCheck(side, tboard)) {
                    return true;
                }
            }
        }
        return false;
    }
}
