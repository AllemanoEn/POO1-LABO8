package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.mouvements.*;

public class Dame extends Pieces {
    public Dame(PlayerColor couleur) {
        super(couleur, PieceType.QUEEN, 7, new Mouvements[]{
                new Horizontal(Direction.DROITE),
                new Horizontal(Direction.GAUCHE),
                new Vertical(Direction.HAUT),
                new Vertical(Direction.BAS),
                new Diagonale(Direction.HAUT_GAUCHE),
                new Diagonale(Direction.HAUT_DROITE),
                new Diagonale(Direction.BAS_GAUCHE),
                new Diagonale(Direction.BAS_DROITE),
                });
    }

    public String toString(){
        return "Dame";
    }

}
