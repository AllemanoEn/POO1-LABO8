package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.mouvements.Diagonale;
import engine.mouvements.Direction;
import engine.mouvements.Mouvements;
import engine.mouvements.Vertical;

public class Pions extends Pieces {

    boolean firstMove = true;

    public Pions(PlayerColor couleur) {
        super(couleur, PieceType.PAWN, 2, new Mouvements[]{new Vertical(Direction.HAUT),
                new Diagonale(Direction.HAUT_DROITE),
                new Diagonale(Direction.HAUT_GAUCHE)
                });
    }

    public String toString(){
        return "Pions";
    }

    public boolean getFirstMove(){
        return firstMove;
    }

    public void setFirstMoveFalse(){
        firstMove = false;
        this.distance = 1;
    }
}
