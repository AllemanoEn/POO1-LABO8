/*
 * Fichier  : Tours.java
 * Auteurs  : Allemano Enzo, Paulus Yohann
 * But      : Crée la pièce tour
 * Date     : 06.01.2021
 */
package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.mouvements.*;

public class Tours extends Pieces {

    boolean firstMove = true;

    /**
     * Constructeur
     *
     * @param couleur   La couleur de la piece
     */
    public Tours(PlayerColor couleur) {
        super(couleur, PieceType.ROOK, 7, new Mouvements[]{
                new Horizontal(Direction.DROITE),
                new Horizontal(Direction.GAUCHE),
                new Vertical(Direction.HAUT),
                new Vertical(Direction.BAS),
        });
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
        return "Tours";
    }
}
