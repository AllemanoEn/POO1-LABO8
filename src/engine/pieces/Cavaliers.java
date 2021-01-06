/*
 * Fichier  : Cavaliers.java
 * Auteurs  : Allemano Enzo, Paulus Yohann
 * But      : Crée la pièce cavalier
 * Date     : 06.01.2021
 */
package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;
import engine.mouvements.Mouvements;

public class Cavaliers extends Pieces {

    /**
     * Constructeur
     *
     * @param couleur   La couleur de la piece
     */
    public Cavaliers(PlayerColor couleur) {
        super(couleur, PieceType.KNIGHT, 3, new Mouvements[0]);
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

        int xDeplacement = getX() - toX;
        int yDeplacement = getY() - toY;

        Case to = plateau[toX][toY];

        if ((Math.abs(xDeplacement) == 2 && Math.abs(yDeplacement) == 1 ||
                Math.abs(xDeplacement) == 1 && Math.abs(yDeplacement) == 2) &&
                (to.isEmpty() || to.getPiece().getCouleur() != getCouleur())){
            return TypeMouvement.CLASSIC;
        }

        return TypeMouvement.INTERDIT;
    }



    public String toString(){
        return "Cavaliers";
    }
}
