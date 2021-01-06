/*
 * Fichier  : Case.java
 * Auteurs  : Allemano Enzo, Paulus Yohann
 * But      : Déclare la classe Case
 * Date     : 06.01.2021
 */
package engine;
import engine.pieces.Pieces;


public class Case {
    int x;
    int y;

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
    void addPiece(Pieces p){
        if (piece != null){
            removePiece();
        }
        piece = p;
        piece.setCase(this);
    }

    /**
     * Retire la pièce qui se trouve sur la case courante
     */
    void removePiece(){
        piece.setCase(null);
        piece = null;
    }

    /**
     * Permet de récupérer la position x de la case courante
     *
     * @return l'attribut x
     */
    public int getX(){
        return x;
    }

    /**
     * Permet de récupérer la position y de la case courante
     *
     * @return l'attribut y
     */
    public int getY(){
        return y;
    }

    /**
     * Permet de récupérer la pièce qui se trouve sur la case courante
     *
     * @return la pièce
     */
    public Pieces getPiece(){
        return piece;
    }
}
