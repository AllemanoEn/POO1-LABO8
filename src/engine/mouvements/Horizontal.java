/*
 * Fichier  : Horizontal.java
 * Auteurs  : Allemano Enzo, Paulus Yohann
 * But      : Déclare le mouvement Horizontal
 * Date     : 06.01.2021
 */
package engine.mouvements;

import chess.PlayerColor;
import engine.Case;

public class Horizontal extends Mouvements{

    /**
     * Constructeur
     * Crée un mouvement horizontal et lui attribu une direction
     *
     * @param direction         Direction à attribuer au mouvement
     * @throws RuntimeException Si la direction est différente de GAUCHE ou DROITE
     */
    public Horizontal(Direction direction){
        if( direction != Direction.GAUCHE && direction != Direction.DROITE){
            throw new RuntimeException("Les uniques directions horizontal sont GAUCHE et DROITE");
        }
        this.direction = direction;
    }

    public boolean TrajectoireLibre(Case[][] plateau, int fromX, int fromY, int toX, int toY, int distance, PlayerColor playerColor) {

        int ecart;
        boolean isOk = false;

        if(direction == Direction.GAUCHE){
            ecart = fromX - toX;
        }
        else{
            ecart = toX - fromX;
        }

        if (fromY == toY && ecart <= distance && ecart >= 0){
            isOk = true;
        }

        if(isOk){
            for (int i = 1; ecart != i; i++){
                int colonne;
                if(direction == Direction.GAUCHE){
                   colonne = fromX - i ;
                }
                else{
                    colonne = fromX + i ;
                }
                if(!plateau[colonne][fromY].isEmpty()){
                    isOk = false;
                    break;
                }
            }
        }

        return isOk;
    }
}
