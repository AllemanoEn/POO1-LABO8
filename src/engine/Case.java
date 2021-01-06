/*
 * Fichier  : Case.java
 * Auteurs  : Allemano Enzo, Paulus Yohann
 * But      : Déclare la classe Case
 * Date     : 06.01.2021
 */
package engine;
import engine.pieces.Pieces;


public class Case {

    private final int x;
    private final int y;
    private Pieces piece;

    /**
     * Constructeur
     * Attribue une coordonnée x y à un case
     *
     * @param x position x
     * @param y position y
     */
    Case(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Détermine si une case est vide
     *
     * @return vrai si vide faux sinon
     */
    public boolean isEmpty(){
        return piece == null;
    }

    /**
     * Ajoute une pièce sur la case courante
     * Si une pièce se trouve préalablement sur la case, cette pièce est retirée via la méthode removePiece()
     *
     * @param p pièce à ajouter
     */
    public void addPiece(Pieces p){
        if (piece != null){
            removePiece();
        }
        piece = p;
        piece.setCase(this);
    }

    /**
     * Retire la pièce qui se trouve sur la case courante
     */
    public void removePiece(){
        piece.setCase(null);
        piece = null;
    }

   // Fonction get
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Pieces getPiece(){
        return piece;
    }
}
