package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.mouvements.Mouvements;

public class Cavaliers extends Pieces {

    public Cavaliers(PlayerColor couleur) {
        super(couleur, PieceType.KNIGHT, 3, new Mouvements[0]);
    }

    public String toString(){
        return "Cavaliers";
    }
}
