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

        int ecart = 0;

        if(direction == Direction.HAUT){
            ecart = toY - fromY;
            if(ecart <= distance && fromX == toX){
                for (int i = fromY; i != toY ;++i){
                    if(!plateau[fromX][i].isEmpty()){
                        return false;
                    }
                }
            }
        } else {
            ecart = fromY - toY;
            if(ecart <= distance && fromX == toX){
                for (int i = fromY; i != toY ;--i){
                    if(!plateau[fromX][i].isEmpty()){
                        return false;
                    }
                }
            }

        }
        return true;
    }
}
