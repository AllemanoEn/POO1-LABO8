/*
 * Fichier  : Roi.java
 * Auteurs  : Allemano Enzo, Paulus Yohann
 * But      : Crée la pièce roi
 * Date     : 06.01.2021
 */
package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;
import engine.mouvements.*;

public class Roi extends Pieces {

    private boolean firstMove = true;

    /**
     * Constructeur
     *
     * @param couleur   La couleur de la piece
     */
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

    /**
     * Controle si un mouvement est valide
     *
     * @param plateau   Le plateau
     * @param toX       Destination X
     * @param toY       Destination Y
     * @param tour      Numéro du tour
     * @return Un TypeMouvement catégorisant le mouvement tester
     */
    public TypeMouvement mouvementValide(Case[][] plateau, int toX, int toY, int tour) {

        TypeMouvement mouvement = super.mouvementValide(plateau, toX, toY, tour);

        if (mouvement == TypeMouvement.INTERDIT && firstMove) {
            if (petitRoque(plateau, toX, toY)) {
                mouvement = TypeMouvement.PETIT_ROQUE;
            } else if (grandRoque(plateau, toX, toY)) {
                mouvement = TypeMouvement.GRAND_ROQUE;
            }
        }
        return mouvement;
    }

    /**
     * Controle si le roque est possible
     *
     * @param plateau       Le plateau
     * @param c             case de la tour
     * @param mouvements    déplacement a effectuer
     * @return Vrai si le roque est possible, sinon faux
     */
    private boolean roque(Case[][] plateau, Case c, Mouvements mouvements){
        if (c.isEmpty() || c.getPiece().getType() != PieceType.ROOK){
            return false;
        }

        Tours tour = (Tours)c.getPiece();

        if (!tour.firstMove){
            return false;
        }

        return mouvements.trajectoireLibre(plateau,getX(),getY(),c.getX(),c.getY(),8,getCouleur());
    }

    /**
     * Controle si le petit roque est possible
     *
     * @param plateau   Le plateau
     * @param toX       Destination X
     * @param toY       Destination Y
     * @return Vrai si il est possible de faire le petit roque, sinon faux
     */
    private boolean petitRoque(Case[][] plateau, int toX, int  toY){
        if (!getFirstMove() || (toX !=6 || toY != getY())){
            return false;
        }
        Case tour = plateau[7][getY()];
        Mouvements droite = new Horizontal(Direction.DROITE);

        return roque(plateau, tour, droite);
    }

    /**
     * Controle si le grand roque est possible
     *
     * @param plateau   Le plateau
     * @param toX       Destination X
     * @param toY       Destination Y
     * @return Vrai si il est possible de faire le grand roque, sinon faux
     */
    private boolean grandRoque(Case[][] plateau, int toX, int  toY){
        if (!getFirstMove() || (toX !=3 && toY != getY())){
            return false;
        }
        Case tour = plateau[0][getY()];
        Mouvements gauche = new Horizontal(Direction.GAUCHE);

        return roque(plateau, tour, gauche);
    }

    // Fonction get
    public boolean getFirstMove(){
        return firstMove;
    }

    // Fonction set
    public void setFirstMoveFalse(){
        firstMove = false;
    }

    public String toString(){
        return "Roi";
    }

}
