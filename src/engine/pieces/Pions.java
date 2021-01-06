/*
 * Fichier  : Pions.java
 * Auteurs  : Allemano Enzo, Paulus Yohann
 * But      : Crée la pièce pion
 * Date     : 06.01.2021
 */
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

    /**
     * Constructeur
     *
     * @param couleur   La couleur de la piece
     */
    public Pions(PlayerColor couleur) {
        super(couleur, PieceType.PAWN, 2, new Mouvements[]{new Vertical(Direction.HAUT),
                new Diagonale(Direction.HAUT_DROITE),
                new Diagonale(Direction.HAUT_GAUCHE)
                });

        jouerDernierTour = 0;
    }



    /**
     * Controle si un mouvement est valide
     *
     * @param plateau   Le plateau
     * @param toX       Destination X
     * @param toY       Destination Y
     * @param tour      Numéro du tour
     * @return Un TypeMouvement catégorisant le mouvement tester
     */
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


    /**
     * Controle si la prise en passant est possible
     *
     * @param plateau   Le plateau
     * @param toX       Destination X
     * @param toY       Destination Y
     * @param tour      Numéro du tour
     * @return Un TypeMouvement définissant si la prise en passant peut etre faite ou non
     */
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

    /**
     * Controle si un pion peut etre promu
     *
     * @param y     la valeur y
     * @return Vrai si il peut etre promu, sinon faux
     */
    private boolean peutEtrePromu (int y){
        return y == 0 || y == 7;
    }

    // Fonction get
    public boolean getFirstMove(){
        return firstMove;
    }

    int getJouerDernierTour(){
        return jouerDernierTour;
    }

    // Fonction set
    public void setFirstMoveFalse(){
        firstMove = false;
        distance = 1;
    }

    public void setDernierCoup(TypeMouvement dernierCoup, int tour){
        this.dernierCoup = dernierCoup;
        jouerDernierTour = tour;
    }


    public String toString(){
        return "Pions";
    }
}
