package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.mouvements.Diagonale;
import engine.mouvements.Direction;
import engine.mouvements.Mouvements;

public class Fous extends Pieces {

    public Fous(PlayerColor couleur) {
        super(couleur, PieceType.BISHOP, 7, new Mouvements[]{new Diagonale(Direction.BAS_DROITE),
                new Diagonale(Direction.BAS_GAUCHE),
                new Diagonale(Direction.HAUT_DROITE),
                new Diagonale(Direction.HAUT_GAUCHE)});
    }
    public String toString(){
        return "Fous";
    }
}
