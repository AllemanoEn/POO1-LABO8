package engine;


import chess.ChessController;
import chess.ChessView;
import chess.PlayerColor;

public class Plateau implements ChessController {

    static final int dimension = 8;
    private ChessView view;
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

    @Override
    public void start(ChessView view){
        this.view = view;
        view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public void newGame() {

    }


}
