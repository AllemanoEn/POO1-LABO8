package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.mouvements.*;

public class Tours extends Pieces {

    public Tours(PlayerColor couleur) {
        super(couleur, PieceType.ROOK, 7, new Mouvements[]{
                new Horizontal(Direction.DROITE),
                new Horizontal(Direction.GAUCHE),
                new Vertical(Direction.HAUT),
                new Vertical(Direction.BAS),
        });
    }

    public String toString(){
        return "Tours";
    }
}
