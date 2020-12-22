package engine.mouvements;

import chess.PlayerColor;
import engine.Case;

public class Diagonale extends Mouvements {
    public Diagonale(Direction direction){
        if( direction != Direction.BAS_DROITE && direction != Direction.BAS_GAUCHE &&
                direction != Direction.HAUT_DROITE && direction != Direction.HAUT_GAUCHE){
            throw new RuntimeException("Pas bien, diagonale c'est BAS_DROITE, BAS_GAUCHE, HAUT_DROITE et HAUT_GAUCHE");
        }
        this.direction = direction;
    }

    public boolean TrajectoireLibre(Case[][] plateau, int fromX, int fromY, int toX, int toY, int distance, PlayerColor playerColor) {
        return false;
    }
}
