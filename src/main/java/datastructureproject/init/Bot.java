package main.java.datastructureproject.init;

import chess.engine.*;
import chess.bot.ChessBot;
import java.util.*;

/**
 * This class is currently heavily influenced by the TestBot class which might change in the future
 * This was the easiest way to start testing out the bot in lichess
 */

public class Bot implements ChessBot {
    private Board b;
    private Random random; 

    public Bot() {
        this.random = new Random();
        this.b = new Board();
    }

    @Override
    public String nextMove(GameState gs) {
        parseLatestMove(gs);
        String myMove;
        
            //Generate a chesslib move based on the position
            myMove = this.getMove();
            
            if (myMove != null) {
                //Transform the move into a UCI string representation
                return myMove.toString();
            }
            
        
        
        
        return null;
    }

    public String getMove() {
        Moves move = new Moves(b);
        ArrayList<String> moves = move.allMovesForBot();
        System.out.println(moves);
        
        //Returns null if no legal moves available, else returns a randomly selected legal move.
        if (moves.size() > 0) {
            return moves.get(random.nextInt(moves.size()));
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
                this.setMove(startingString, endingString, promoteString);
            });
        }
    }
    /**
     * 
     * @param starting starting position
     * @param ending destination
     * @param promote if a pawn can be promoted(not yet implemented)
     */

    public void setMove(String starting, String ending, String promote) {
        Moves m = new Moves(b);
        String latestmove = starting + ending;

        this.b.doMove(m.convertBackFromUCI(latestmove));
    }
}
