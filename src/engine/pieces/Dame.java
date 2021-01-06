/*
 * Fichier  : Dame.java
 * Auteurs  : Allemano Enzo, Paulus Yohann
 * But      : Crée la pièce dame
 * Date     : 06.01.2021
 */
package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.mouvements.*;

public class Dame extends Pieces {

    /**
     * Constructeur
     *
     * @param couleur   La couleur de la piece
     */
    public Dame(PlayerColor couleur) {
        super(couleur, PieceType.QUEEN, 7, new Mouvements[]{
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

    public String toString(){
        return "Dame";
    }

}
