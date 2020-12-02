package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;

public abstract class Pieces {

    PlayerColor couleur;
    PieceType type;
    int distance;

    public Pieces (PlayerColor couleur, PieceType type, int distance){
        this.couleur = couleur;
        this.type = type;
        this.distance = distance;
    }

    public PlayerColor getCouleur(){
        return couleur;
    }

    public PieceType type(){
        return type;
    }

    public int distance(){
        return distance;
    }

}
