package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.mouvements.Diagonale;
import engine.mouvements.Direction;
import engine.mouvements.Mouvements;
import engine.mouvements.Vertical;

public class Pions extends Pieces {

    public Pions(PlayerColor couleur) {
        super(couleur, PieceType.PAWN, 1, new Mouvements[]{new Vertical(Direction.HAUT)
                //new Diagonale(Direction.HAUT_DROITE),
                //new Diagonale(Direction.HAUT_GAUCHE)
                });
    }

    public String toString(){
        return "Pions";
    }
}
