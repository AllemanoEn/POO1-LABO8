package engine;


import chess.ChessController;
import chess.ChessView;
import chess.PlayerColor;
import engine.pieces.Pieces;

import java.util.ArrayList;
import java.util.Arrays;

public class Plateau implements ChessController {

    static final int dimension = 8;
    private ChessView view;
    Case[][] plateau;
    int turn;
    boolean echec;

    public Plateau(){
        plateau = new Case[dimension][dimension];
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
        Case caseFrom = plateau[fromX][fromY];
        Case caseTo = plateau[toX][toY];

        Pieces p = caseFrom.getPiece();




        return false;
    }

    @Override
    public void newGame() {
        turn = 1;
        echec = false;

       
        for (int row = 0; row < dimension; ++row) {
            for (int col = 0; col < dimension; ++col) {
                Case caseCourrante = plateau[row][col];
                if (!caseCourrante.isEmpty()) {
                    plateau[row][col].removePiece();
                }
            }
        }


        ArrayList<Pieces> whitePieces = new ArrayList<>(
                Arrays.asList(
                        new Tours(PlayerColor.WHITE),
                        new Cavaliers(PlayerColor.WHITE),
                        new Fous(PlayerColor.WHITE),
                        new Dame(PlayerColor.WHITE),
                        new Roi(PlayerColor.WHITE),
                        new Fous(PlayerColor.WHITE),
                        new Cavaliers(PlayerColor.WHITE),
                        new Tours(PlayerColor.WHITE)
                )
        );


        ArrayList<Pieces> blackPieces = new ArrayList<>(
                Arrays.asList(
                        new Tours(PlayerColor.BLACK),
                        new Cavaliers(PlayerColor.BLACK),
                        new Fous(PlayerColor.BLACK),
                        new Dame(PlayerColor.BLACK),
                        new Roi(PlayerColor.BLACK),
                        new Fous(PlayerColor.BLACK),
                        new Cavaliers(PlayerColor.BLACK),
                        new Tours(PlayerColor.BLACK)
                )
        );

        for (int col = 0; col < dimension; ++col) {
            Pieces pawnWhite = new Pions(PlayerColor.WHITE);
            Pieces pawnBlack = new Pions(PlayerColor.BLACK);
            plateau[1][col].addPiece(pawnWhite);
            plateau[6][col].addPiece(pawnBlack);

            view.putPiece(pawnWhite.getType(), pawnWhite.getCouleur(), col, 1);
            view.putPiece(pawnBlack.getType(), pawnBlack.getCouleur(), col, 6);
        }


        for (int i = 0; i < dimension; ++i) {
            Pieces p = whitePieces.get(i);

            plateau[0][i].addPiece(p);

            view.putPiece(p.getType(), p.getCouleur(), i, 0);
        }


        for (int i = 0; i < dimension; ++i) {
            Pieces p = blackPieces.get(i);

            plateau[7][i].addPiece(p);

            view.putPiece(p.getType(), p.getCouleur(), i, 7);
        }
    }

}