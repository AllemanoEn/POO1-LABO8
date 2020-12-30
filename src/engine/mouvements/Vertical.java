package engine.mouvements;

import chess.PlayerColor;
import engine.Case;

public class Vertical extends Mouvements {

    public Vertical(Direction direction){
        if( direction != Direction.BAS && direction != Direction.HAUT){
            throw new RuntimeException("Pas bien, vertical c'est en BAS et en HAUT");
        }
        this.direction = direction;
    }

    public boolean TrajectoireLibre(Case[][] plateau, int fromX, int fromY, int toX, int toY, int distance, PlayerColor playerColor) {

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

        if(isOk){
            for (int i = 1; ecart != i; i++){
                int ligne;
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

