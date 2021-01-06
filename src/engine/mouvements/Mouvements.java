/*
 * Fichier  : Mouvements.java
 * Auteurs  : Allemano Enzo, Paulus Yohann
 * But      : Déclare la classe abstraite Mouvements
 * Date     : 06.01.2021
 */
package engine.mouvements;

import chess.PlayerColor;
import engine.Case;

public abstract class Mouvements {
    Direction direction;

    public boolean TrajectoireLibre(Case[][] plateau, int fromX, int fromY, int toX, int toY, int distance, PlayerColor playerColor) {
        return false;
    }

}
