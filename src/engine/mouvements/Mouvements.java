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
    public Direction direction;

    /**
     *Prototype de la fonction TrajectoireLibre.
     * Détermine si la trajectoire qui correspond à un mouvement, ne comporte ou pas d'obstacle (de pièces).
     * La longueur de la trajectoire étudiée est définie dans les paramètres
     *
     * @param plateau       Représente l'échiquier en cours d'utilisation
     * @param fromX         Position X du début de la trajectoire à étuider
     * @param fromY         Position Y du début de la trajectoire à étuider
     * @param toX           Position X de la fin de la trajectoire à étuider
     * @param toY           Position Y de la fin de la trajectoire à étuider
     * @param distance      Distance de la trajectoire à étuider
     * @param playerColor   Couleur du joueur courant
     * @return              Vrai si la trajectoire est livre et faux si elle ne l'est pas
     */
    public boolean trajectoireLibre(Case[][] plateau, int fromX, int fromY, int toX, int toY, int distance, PlayerColor playerColor) {
        return false;
    }

}
