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


        int ecartX;
        int ecartY;
        int ecart = -1;
        int inverseur;
        int inverseurVertical;
        boolean isOk = false;


        if(direction == Direction.BAS_GAUCHE || direction == Direction.HAUT_GAUCHE){
            ecartX = fromX - toX;
        }
        else{
            ecartX = toX - fromX;
        }

        if (direction == Direction.HAUT_GAUCHE || direction == Direction.HAUT_DROITE){
            inverseurVertical = 1;
        }
        else{
            inverseurVertical = -1;
        }

        if (playerColor == PlayerColor.WHITE){
            inverseur = 1;
            ecartY = (toY - fromY) * inverseurVertical;
        }
        else{
            inverseur = -1;
            ecartY = (fromY - toY) * inverseurVertical;
        }

        if(ecartX == ecartY){
            ecart = ecartX;
        }

        if (ecart <= distance && ecart >= 0){
            isOk = true;
        }

        if(isOk){
            for (int i = 1; ecart != i; i++){
                int colonne;
                int ligne;

                if(direction == Direction.HAUT_GAUCHE || direction == Direction.BAS_GAUCHE){
                    colonne = fromX - i;
                }
                else{
                    colonne = fromX + i;
                }
                if(direction == Direction.HAUT_GAUCHE || direction == Direction.HAUT_DROITE){
                    ligne = fromY + (i * inverseur);
                }
                else{
                    ligne = fromY - (i * inverseur);
                }
                if(!plateau[colonne][ligne].isEmpty()){
                    isOk = false;
                    break;
                }
            }
        }

        return isOk;

    }
}
