package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.mouvements.Mouvements;

public class Dame extends Pieces {
    public Dame(PlayerColor couleur) {
        super(couleur, PieceType.QUEEN, new Mouvements()[]{
        new
        },7);
    }
}
