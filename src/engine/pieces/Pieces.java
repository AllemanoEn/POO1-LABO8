package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;
import engine.mouvements.Mouvements;

public abstract class Pieces {

    PlayerColor couleur;
    PieceType type;
    int distance;
    Case c;
    Mouvements[] mouvements;
    int dernierTourDeplace;



    public Pieces (PlayerColor couleur, PieceType type, int distance, Mouvements[] mouvements){
        this.couleur = couleur;
        this.type = type;
        this.distance = distance;
        this.mouvements = new Mouvements[mouvements.length];
        dernierTourDeplace = 0;

        for(int i = 0; i != mouvements.length; i++){
            this.mouvements[i]=mouvements[i];
        }

    }

    public TypeMouvement mouvementValide(Case[][] plateau, int toX, int toY){


        if (getCase() == null){
            return TypeMouvement.INTERDIT;
        }

        //Permet de ne pas pouvoir manger ces propres pièces
        if(plateau[toX][toY].getPiece() != null && plateau[toX][toY].getPiece().getCouleur() == this.couleur){
            return TypeMouvement.INTERDIT;
        }

        for (Mouvements m : mouvements){
            if (m.TrajectoireLibre(plateau,getX(),getY(),toX,toY,getDistance(),getCouleur())){
                return TypeMouvement.CLASSIC;
            }
        }
        return TypeMouvement.INTERDIT;
    }

    public PlayerColor getCouleur(){
        return couleur;
    }

    public PieceType getType(){
        return type;
    }

    public int getDistance(){
        return distance;
    }

    Case getCase(){
        return c;
    }

    public int getX(){
        return c.getX();
    }

    public int getY(){
        return c.getY();
    }

    public void setCase(Case c){
        this.c = c;
    }
}
