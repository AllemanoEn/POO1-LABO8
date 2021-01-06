package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;
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

    public TypeMouvement mouvementValide(Case[][] plateau, int toX, int toY){
        TypeMouvement mouvement = super.mouvementValide(plateau, toX, toY);

        if (peutEtrePromu(toY) && mouvement != TypeMouvement.INTERDIT){
            mouvement = TypeMouvement.PROMOTION;
        }

        return mouvement;
    }

    public boolean getFirstMove(){
        return firstMove;
    }

    public void setFirstMoveFalse(){
        firstMove = false;
        this.distance = 1;
    }

    private boolean peutEtrePromu (int y){
        return y == 0 || y == 7;
    }

}
