package engine.mouvements;

import chess.PlayerColor;
import engine.Case;

public class Horizontal extends Mouvements{

    public Horizontal(Direction direction){
        if( direction != Direction.GAUCHE && direction != Direction.DROITE){
            throw new RuntimeException("Pas bien, horizontal c'est GAUCHE et DROITE");
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
