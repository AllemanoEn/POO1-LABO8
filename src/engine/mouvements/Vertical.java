/*
 * Fichier  : Vertical.java
 * Auteurs  : Allemano Enzo, Paulus Yohann
 * But      : Déclare le mouvement Vertical
 * Date     : 06.01.2021
 */
package engine.mouvements;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;


public class Vertical extends Mouvements {

    /**
     * Constructeur
     * Crée un mouvement vertical et lui attribu une direction
     *
     * @param direction         Direction à attribuer au mouvement
     * @throws RuntimeException Si la direction est différente de BAS ou HAUT
     */
    public Vertical(Direction direction){
        if( direction != Direction.BAS && direction != Direction.HAUT){
            throw new RuntimeException("Les uniques directions vertical c'est en BAS et en HAUT");
        }
        this.direction = direction;
    }

    public boolean trajectoireLibre(Case[][] plateau, int fromX, int fromY, int toX, int toY, int distance, PlayerColor playerColor) {

        int ecart;
        int inverseur;
        boolean isOk = false;

        if (playerColor == PlayerColor.WHITE){
            inverseur = 1;
        }
        else{
            inverseur = -1;
        }

        if(direction == Direction.HAUT){
            ecart = (toY - fromY) * inverseur;
        }
        else{
            ecart = (fromY - toY) * inverseur;
        }

        if (fromX == toX && ecart <= distance && ecart >= 0){
            isOk = true;
        }

        if(direction == Direction.HAUT && plateau[fromX][fromY].getPiece().getType() == PieceType.PAWN && !plateau[toX][toY].isEmpty()){
            return false;
        }

        if(isOk){
            for (int i = 1; ecart != i; i++){
                int ligne = 0;
                if(direction == Direction.HAUT){
                    ligne = fromY + (i * inverseur) ;
                }
                else{
                    ligne = fromY - (i * inverseur);
                }
                if(!plateau[fromX][ligne].isEmpty()){
                    isOk = false;
                    break;
                }
            }
        }
        return isOk;
    }
}

