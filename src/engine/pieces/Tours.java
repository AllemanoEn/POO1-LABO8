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

    public Tours(PlayerColor couleur) {
        super(couleur, PieceType.ROOK, 7, new Mouvements[]{
                new Horizontal(Direction.DROITE),
                new Horizontal(Direction.GAUCHE),
                new Vertical(Direction.HAUT),
                new Vertical(Direction.BAS),
        });
    }

    public String toString(){
        return "Tours";
    }

    public boolean getFirstMove(){
        return firstMove;
    }

    public void setFirstMoveFalse(){
        firstMove = false;
    }
}
