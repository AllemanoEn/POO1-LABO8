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

    private final PlayerColor couleur;
    private final PieceType type;
    int distance;
    private Case c;
    private final Mouvements[] mouvements;
    private int dernierTourDeplace;

    /**
     * Constructeur
     *
     * @param couleur       La couleur de la piece
     * @param type          Le type de la piece
     * @param distance      La distance que la piece peut parcourir
     * @param mouvements    Tous les mouvements que la piece peut effectuer
     */
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

    /**
     * Controle si un mouvement est valide
     *
     * @param plateau       Le plateau
     * @param toX           Destination X
     * @param toY           Destination Y
     * @param tour          Numéro du tour
     * @return Un TypeMouvement catégorisant le mouvement tester
     */
    public TypeMouvement mouvementValide(Case[][] plateau, int toX, int toY, int tour){


        if (getCase() == null){
            return TypeMouvement.INTERDIT;
        }

        //Permet de ne pas pouvoir manger ces propres pièces
        if(plateau[toX][toY].getPiece() != null && plateau[toX][toY].getPiece().getCouleur() == this.couleur){
            return TypeMouvement.INTERDIT;
        }

        for (Mouvements m : mouvements){
            if (m.trajectoireLibre(plateau,getX(),getY(),toX,toY,getDistance(),getCouleur())){
                return TypeMouvement.CLASSIC;
            }
        }
        return TypeMouvement.INTERDIT;
    }

    // Fonction get
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

    // Fonction set
    public void setCase(Case c){
        this.c = c;
    }

    public String textValue(){
        return toString();
    }

}
