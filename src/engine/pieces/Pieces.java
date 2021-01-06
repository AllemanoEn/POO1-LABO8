/*
 * Fichier  : Pieces.java
 * Auteurs  : Allemano Enzo, Paulus Yohann
 * But      : Décalre la classe abstraite Pieces
 * Date     : 06.01.2021
 */
package engine.pieces;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.Case;
import engine.mouvements.Mouvements;

public abstract class Pieces implements ChessView.UserChoice {

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

    public TypeMouvement mouvementValide(Case[][] plateau, int toX, int toY, int tour){


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

    public String textValue(){
        return toString();
    }

}
