package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;
import engine.mouvements.Mouvements;

public class Cavaliers extends Pieces {

    public Cavaliers(PlayerColor couleur) {
        super(couleur, PieceType.KNIGHT, 3, new Mouvements[0]);
    }


    public TypeMouvement mouvementValide(Case[][] plateau, int toX, int toY, int tour){

        int xDeplacement = getX() - toX;
        int yDeplacement = getY() - toY;

        Case to = plateau[toX][toY];

        if ((Math.abs(xDeplacement) == 2 && Math.abs(yDeplacement) == 1 ||
                Math.abs(xDeplacement) == 1 && Math.abs(yDeplacement) == 2) &&
                (to.isEmpty() || to.getPiece().getCouleur() != getCouleur())){
            return TypeMouvement.CLASSIC;
        }

        return TypeMouvement.INTERDIT;
    }



    public String toString(){
        return "Cavaliers";
    }
}
