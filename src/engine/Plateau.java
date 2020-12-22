package engine;


import chess.ChessController;
import chess.ChessView;
import chess.PlayerColor;
import engine.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Plateau implements ChessController {

    static final int dimension = 8;
    private ChessView view;
    Case[][] plateau;
    int tour;
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
        if (caseFrom.isEmpty())
            return false;
        Pieces p = caseFrom.getPiece();

        if (tour % 2 == 1 && p.getCouleur() != PlayerColor.WHITE || tour % 2 == 0 && p.getCouleur() != PlayerColor.BLACK)
            return false;

        boolean test = p.mouvementValide(plateau,toX,toY);
        if (!test)
            return false;

        caseFrom.removePiece();
        caseTo.addPiece(p);



        view.displayMessage("");
        view.removePiece(fromX,fromY);
        view.putPiece(p.getType(),p.getCouleur(),toX,toY);


        ++tour;

        return true;
    }

    @Override
    public void newGame() {
        tour = 1;
        echec = false;

        for (int col = 0; col < dimension; ++col) {
            for (int row = 0; row < dimension; ++row) {
                Case caseCourrante = plateau[col][row];
                if (!caseCourrante.isEmpty()) {
                    plateau[col][row].removePiece();
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
            plateau[col][1].addPiece(pawnWhite);
            plateau[col][6].addPiece(pawnBlack);

            view.putPiece(pawnWhite.getType(), pawnWhite.getCouleur(), col, 1);
            view.putPiece(pawnBlack.getType(), pawnBlack.getCouleur(), col, 6);
        }


        for (int i = 0; i < dimension; ++i) {
            Pieces p = whitePieces.get(i);

            plateau[i][0].addPiece(p);

            view.putPiece(p.getType(), p.getCouleur(), i, 0);
        }


        for (int i = 0; i < dimension; ++i) {
            Pieces p = blackPieces.get(i);

            plateau[i][7].addPiece(p);

            view.putPiece(p.getType(), p.getCouleur(), i, 7);
        }
    }

}