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
        int ecart;
        boolean isOk = false;

        if(direction == Direction.BAS_GAUCHE || direction == Direction.HAUT_GAUCHE){
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
