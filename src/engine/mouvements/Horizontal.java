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
    boolean TrajectoireLibre(Case[][] plateau, int fromX, int fromY, int toX, int toY, int distance, PlayerColor playerColor) {

        for (int i = 0; distance != i; i++){
            
        }



        return false;
    }
}
