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

    boolean firstMove = true;

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

    boolean roque(Case[][] plateau, Case c, Mouvements mouvements){
        if (c.isEmpty() || c.getPiece().getType() != PieceType.ROOK){
            return false;
        }

        Tours tour = (Tours)c.getPiece();

        if (!tour.firstMove){
            return false;
        }

        return mouvements.TrajectoireLibre(plateau,getX(),getY(),c.getX(),c.getY(),8,getCouleur());
    }

    boolean petitRoque(Case[][] plateau, int toX, int  toY){
        if (!getFirstMove() || (toX !=6 || toY != getY())){
            return false;
        }
        Case tour = plateau[7][getY()];
        Mouvements droite = new Horizontal(Direction.DROITE);

        return roque(plateau, tour, droite);
    }

    boolean grandRoque(Case[][] plateau, int toX, int  toY){
        if (!getFirstMove() || (toX !=3 && toY != getY())){
            return false;
        }
        Case tour = plateau[0][getY()];
        Mouvements gauche = new Horizontal(Direction.GAUCHE);

        return roque(plateau, tour, gauche);
    }



    public String toString(){
        return "Roi";
    }
    public boolean getFirstMove(){
        return firstMove;
    }

    public void setFirstMoveFalse(){
        firstMove = false;
    }

}
