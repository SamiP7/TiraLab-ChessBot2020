package main.java.datastructureproject.init;

import chess.engine.*;
import chess.bot.ChessBot;
import java.util.*;
import main.java.datastructureproject.algorithms.*;

/**
 * This class is currently heavily influenced by the TestBot class which might change in the future
 * This was the easiest way to start testing out the bot in lichess
 */

public class Bot implements ChessBot {

    private Board b;
    private Random random;

    public Bot() {

        this.b = new Board();
        this.random = new Random();
    }

    @Override
    public String nextMove(GameState gs) {
        parseLatestMove(gs);
        String myMove;    
        myMove = this.getMove(gs);
            
        if (myMove != null) {
            //Transform the move into a UCI string representation
            return myMove.toString();
        } else {
            Moves noGoodMoves = new Moves(b, gs);
            StringList move = noGoodMoves.allMovesForSide(gs.playing, b.returnBoard());
            if (move.size() > 0) {
                return move.get(random.nextInt(move.size()));
            }
        }

        return null;
    }

    public String getMove(GameState gs) {

        MinMax bestMove = new MinMax(b, gs);
        String move = bestMove.minMaxMove();
        

        //Returns null if no legal moves available, else returns a randomly selected legal move.
        if (move != null && !move.equals("")) {
            if (move.length() > 4) {
                String temp[] = move.split("");
                if (temp[4].equals("+")) {
                    return new String(temp[0] + temp[1] + temp[2] + temp[3]);
                }
            }
            return move;
        } else {
            return null;
        }
    }

    public void parseLatestMove(GameState gs) {
        this.b = new Board();

        // We play all of the moves onto a new board to ensure a previously
        // started game can be resumed correctly, inefficient but it works
        if (!gs.moves.isEmpty()) {
            gs.moves.forEach(moveString -> {
                String startingString = moveString.substring(0, 2).toUpperCase();
                String endingString = moveString.substring(2, 4).toUpperCase();
                String promoteString = moveString.length() > 4 ? moveString
                        .substring(4).toUpperCase() : "".toUpperCase();
                this.setMove(startingString, endingString, promoteString, gs);
            });
        }
    }
    /**
     * 
     * @param starting starting position
     * @param ending destination
     * @param promote if a pawn can be promoted(not yet implemented)
     */

    public void setMove(String starting, String ending, String promote, GameState gs) {
        Moves m = new Moves(b, gs);
        String latestmove = starting + ending;
        if (promote.length() > 0) {
            latestmove += promote;
        }
        this.b.doMove(m.convertBackFromUCI(latestmove), this.b.returnBoard());
    }
}
