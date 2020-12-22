package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.mouvements.*;

public class Roi extends Pieces {

    public Roi(PlayerColor couleur) {
        super(couleur, PieceType.KING, 1, new Mouvements[]{
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
        return "Roi";
    }


}
