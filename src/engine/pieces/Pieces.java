package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public abstract class Pieces {

    PlayerColor couleur;
    PieceType type;
    int distance;
    Case c;



    public Pieces (PlayerColor couleur, PieceType type, int distance){
        this.couleur = couleur;
        this.type = type;
        this.distance = distance;
    }

    public PlayerColor getCouleur(){
        return couleur;
    }

    public PieceType geType(){
        return type;
    }

    public int getDistance(){
        return distance;
    }

    Case getCase(){
        return c;
    }

    void setCase(Case c){
        this.c = c;
    }
}
