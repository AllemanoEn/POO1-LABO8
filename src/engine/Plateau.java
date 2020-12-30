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
    Pieces roiBlanc;
    Pieces roiNoir;

    public Plateau(){
        plateau = new Case[dimension][dimension];
        for(int colonne = 0; colonne < dimension; colonne ++){
            for(int ligne = 0; ligne < dimension; ligne ++){
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

        if(echec){
            view.displayMessage("Echec");
        }

        if (caseFrom.isEmpty()){
            return false;
        }

        Pieces p = caseFrom.getPiece();

        if (tour % 2 == 1 && p.getCouleur() != PlayerColor.WHITE || tour % 2 == 0 && p.getCouleur() != PlayerColor.BLACK){
            return false;
        }


        TypeMouvement test = p.mouvementValide(plateau,toX,toY);
        if ( test == TypeMouvement.INTERDIT){
            return false;
        }

        caseFrom.removePiece();
        caseTo.addPiece(p);

        //ça fait des choses bizzares mais c'est normal je crois

        if(Echec(couleurAdversaire((p.getCouleur())))){
            caseTo.removePiece();
            caseFrom.addPiece(p);
            return false;
        }


        view.displayMessage("");
        view.removePiece(fromX,fromY);
        view.putPiece(p.getType(),p.getCouleur(),toX,toY);


        if(Echec((p.getCouleur()))){
            view.displayMessage("Echec");
        }

        ++tour;

        return true;
    }


    boolean Echec(PlayerColor couleurAdversaire){
        Pieces roi;
        if (couleurAdversaire == PlayerColor.WHITE){
            roi = roiNoir;
        }
        else {
            roi = roiBlanc;
        }
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                Case caseActuelle = plateau[i][j];

                if (caseActuelle.isEmpty() || caseActuelle.getPiece().getCouleur() == roi.getCouleur()){
                    continue;
                }
                if (caseActuelle.getPiece().mouvementValide(plateau,roi.getX(),roi.getY()) != TypeMouvement.INTERDIT){
                    echec = true;
                    return echec;
                }
            }
        }
        echec = false;
        return echec;
    }

    PlayerColor couleurAdversaire(PlayerColor couleur){
        if (couleur == PlayerColor.WHITE){
            return PlayerColor.BLACK;
        }
        return PlayerColor.WHITE;
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

        roiBlanc = new Roi(PlayerColor.WHITE);
        roiNoir = new Roi(PlayerColor.BLACK);

        ArrayList<Pieces> whitePieces = new ArrayList<>(
                Arrays.asList(
                        new Tours(PlayerColor.WHITE),
                        new Cavaliers(PlayerColor.WHITE),
                        new Fous(PlayerColor.WHITE),
                        new Dame(PlayerColor.WHITE),
                        roiBlanc,
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
                        roiNoir,
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