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
    int jouerDernierTour;

    public Pions(PlayerColor couleur) {
        super(couleur, PieceType.PAWN, 2, new Mouvements[]{new Vertical(Direction.HAUT),
                new Diagonale(Direction.HAUT_DROITE),
                new Diagonale(Direction.HAUT_GAUCHE)
                });

        jouerDernierTour = 0;
    }

    public String toString(){
        return "Pions";
    }

    public TypeMouvement mouvementValide(Case[][] plateau, int toX, int toY, int tour){
        TypeMouvement mouvement = super.mouvementValide(plateau, toX, toY, tour);

        if (peutEtrePromu(toY) && mouvement != TypeMouvement.INTERDIT){
            mouvement = TypeMouvement.PROMOTION;
        }

        if (mouvement == TypeMouvement.INTERDIT){
            mouvement = enPassant(plateau, toX, toY, tour);
        }
        return mouvement;
    }

    private TypeMouvement enPassant(Case[][] plateau, int toX, int toY, int tour){

        if(!plateau[toX][toY].isEmpty()){
            return TypeMouvement.INTERDIT;
        }

        if(plateau[toX][getY()].getPiece() == null){
            return TypeMouvement.INTERDIT;
        }
        if(plateau[toX][getY()].getPiece().getType() != PieceType.PAWN ||
                plateau[toX][getY()].getPiece().getCouleur() == getCouleur()){
            return TypeMouvement.INTERDIT;
        }


        Pions pions = (Pions)plateau[toX][getY()].getPiece();
        if (pions.dernierCoup != TypeMouvement.DOUBLE || pions.getJouerDernierTour() != tour - 1 ){
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

    public void setDernierCoup(TypeMouvement dernierCoup, int tour){
        this.dernierCoup = dernierCoup;
        jouerDernierTour = tour;
    }

    int getJouerDernierTour(){
        return jouerDernierTour;
    }

}
