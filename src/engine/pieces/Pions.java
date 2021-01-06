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
    TypeMouvement dernierCoup;

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

        if (mouvement == TypeMouvement.INTERDIT){
            mouvement = enPassant(plateau, toX, toY);
        }
        return mouvement;
    }

    private TypeMouvement enPassant(Case[][] plateau, int toX, int toY){

        int xDestination;

        if (getX() - toX > 0){
            xDestination = toX - 1;
        }
        else {
            xDestination = toX + 1;
        }

        if(!plateau[xDestination][toY].isEmpty()){
            return TypeMouvement.INTERDIT;
        }


        if(plateau[xDestination][toY].getPiece().getType() != PieceType.PAWN ||
                plateau[xDestination][toY].getPiece().getCouleur() == getCouleur()){
            return TypeMouvement.INTERDIT;
        }

        Pions pions = (Pions)plateau[xDestination][toY].getPiece();
        if (pions.dernierCoup != TypeMouvement.DOUBLE){
            return TypeMouvement.INTERDIT;
        }

        return TypeMouvement.EN_PASSANT;
    }


    private boolean peutEtrePromu (int y){
        return y == 0 || y == 7;
    }


    public boolean getFirstMove(){
        return firstMove;
    }

    public void setFirstMoveFalse(){
        firstMove = false;
        distance = 1;

    }

    public void setDernierCoup(TypeMouvement dernierCoup){
        this.dernierCoup = dernierCoup;
    }

}
