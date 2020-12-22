package engine.mouvements;

import chess.PlayerColor;
import engine.Case;

public abstract class Mouvements {
    Direction direction;

    boolean TrajectoireLibre(Case[][] plateau, int fromX, int fromY, int toX, int toY, int distance, PlayerColor playerColor) {
        return false;
    }

}
