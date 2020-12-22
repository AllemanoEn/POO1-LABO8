package engine;


import chess.PlayerColor;

public class Plateau {

    static final int dimension = 8;
    Case[][] plateau;
    int turn;
    boolean echec;

    public Plateau(){
        plateau = new Case[8][8];
        for(int colonne = 0; colonne < dimension ; colonne ++){
            for(int ligne = 0; ligne < dimension ; ligne ++){
                plateau[colonne][ligne] = new Case(colonne,ligne);
            }
        }
    }




}
