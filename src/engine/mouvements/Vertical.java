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
    boolean TrajectoireLibre(Case[][] plateau, int fromX, int fromY, int toX, int toY, int distance, PlayerColor playerColor) {
        return false;
    }
}
