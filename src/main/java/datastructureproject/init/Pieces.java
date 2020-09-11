package main.java.datastructureproject.init;

public class Pieces {
    String type;
    public Pieces(String type) {
        this.type = type;
    }
    /**
     * This method is not currently used and will probably get deleted later but just keeping it here for
     * now, just in case I change my mind
     * @param piece
     * @return
     */
    public String getPiece(Pieces piece) {
        switch (piece.toString()) {
            case "p":
                return "white_pawn";
            case "q":
                return "white_queen";
            case "r":
                return "white_rook";
            case "b":
                return "white_bishop";
            case "n":
                return "white_knight";
            case "k":
                return "white_king";
            case "P":
                return "black_pawn";
            case "Q":
                return "black_queen";
            case "R":
                return "black_rook";
            case "B":
                return "black_bishop";
            case "N":
                return "black_knight";
            case "K":
                return "black_king";
        }
        return "";
    }
    /**
     * Returns the piece as a String so it can actually be read
     */
    public String toString() {
        return type;
    }
}
