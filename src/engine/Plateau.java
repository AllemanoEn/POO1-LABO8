/*
 * Fichier  : Pions.java
 * Auteurs  : Allemano Enzo, Paulus Yohann
 * But      : Déclare la classe plateau et contrôle le déroulement de la partie
 * Date     : 06.01.2021
 */
package engine;


import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Plateau implements ChessController {

    private static final int dimension = 8;
    private ChessView view;
    private Case[][] plateau;
    private int tour;
    private  boolean echec;
    private Pieces roiBlanc;
    private Pieces roiNoir;

    /**
     * Constructeur, crée le plateau de jeu
     */
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

        // En cas d'échec on affiche le message
        if(echec){
            view.displayMessage("Echec");
        }

        if (caseFrom.isEmpty()){
            return false;
        }

        // Piece deplacee
        Pieces p = caseFrom.getPiece();

        // Permet de savoir quel couleur doit jouer
        if (tour % 2 == 1 && p.getCouleur() != PlayerColor.WHITE || tour % 2 == 0 && p.getCouleur() != PlayerColor.BLACK){
            return false;
        }


        TypeMouvement mouvementATester = p.mouvementValide(plateau,toX,toY, tour);
        if ( mouvementATester == TypeMouvement.INTERDIT){
            return false;
        }

        // On test si le roque est à effectuer, si oui on le fait
        if (mouvementATester == TypeMouvement.PETIT_ROQUE || mouvementATester == TypeMouvement.GRAND_ROQUE){
            return roquer((Roi)p,mouvementATester, toX,toY);
        }

        // Effectue le déplacement des pieces
        caseFrom.removePiece();
        caseTo.addPiece(p);

        // Se le coup met le roi en echec, on annule
        if(echec(couleurAdversaire((p.getCouleur())))){
            caseTo.removePiece();
            caseFrom.addPiece(p);
            return false;
        }

        // On applique les modifications sur l'interface graphique
        view.displayMessage("");
        view.removePiece(fromX,fromY);
        view.putPiece(p.getType(),p.getCouleur(),toX,toY);

        // Si on a déplacé un pion, on lui retire son droit de se déplacer de 2 cases
        // On enregistre les caractéristiques de son déplacement (Type et numéro du tour)
        if (p.getType() == PieceType.PAWN){
            if(((Pions)p).getPremierCoup()){
                ((Pions)p).setPremierCoupFalse();
                if (Math.abs(fromY-toY) > 1){
                    ((Pions)p).setDernierCoup(TypeMouvement.DOUBLE, tour);
                }
                else {
                    ((Pions)p).setDernierCoup(TypeMouvement.CLASSIC, tour);
                }
            }
        }

        // Si on déplace une tour, elle ne peut plus roquer
        if (p.getType() == PieceType.ROOK){
            if(((Tours)p).getFirstMove()){
                ((Tours)p).setFirstMoveFalse();
            }
        }
        // Si on déplace le roi, il ne peut plus roquer
        if (p.getType() == PieceType.KING){
            if(((Roi)p).getFirstMove()){
                ((Roi)p).setFirstMoveFalse();
            }
        }

        // Si il y a une promotion a faire, on l'effectue
        if (mouvementATester == TypeMouvement.PROMOTION){
            promouvoir(p);
        }

        // Si il y a une prise en passant a faire, on l'effectue
        if (mouvementATester == TypeMouvement.EN_PASSANT){
            enPassant(fromY ,toX);
        }

        // Si notre coup met le roi adverse en echec, on affiche le message
        if(echec((p.getCouleur()))){
            view.displayMessage("Echec");
        }

        tour++;

        return true;
    }

    /**
     * Permet d'effectuer visuellement la prise en passant
     *
     * @param fromY     Départ Y
     * @param toX       Destination X
     */
    private void enPassant (int fromY,int toX) {

        plateau[toX][fromY].removePiece();
        view.removePiece(toX,fromY);

    }

    /**
     * Permet d'effectuer la promotion d'un pion
     * @param p         La piece a promouvoir
     */
    private void promouvoir(Pieces p) {

        // Les nouvelles pieces possibles
        Pieces dame = new Dame(p.getCouleur());
        Pieces cavalier = new Cavaliers(p.getCouleur());
        Pieces tour = new Tours(p.getCouleur());
        Pieces fou = new Fous(p.getCouleur());

        // Coordonnees de la case sur laquelle a lieu la promotion
        int x,y;
        x = p.getX();
        y = p.getY();

        Pieces pieceSelectionee = view.askUser("Promotion", "Choisir une pièce pour la promotion",dame,cavalier,tour,fou);

        // On effectue la promotion du pion
        plateau[x][y].removePiece();
        plateau[x][y].addPiece(pieceSelectionee);
        view.removePiece(x, y);
        view.putPiece(pieceSelectionee.getType(), pieceSelectionee.getCouleur(), pieceSelectionee.getX(), pieceSelectionee.getY());
    }

    /**
     * Permet d'effectuer les deux roques
     * @param roi       Le roi qui va effectuer le roque
     * @param roque     Différentie le petit et le grand roque
     * @param toX       Destination X
     * @param toY       Destination Y
     * @return Vrai si le roque c'est effectué correctement, sinon faux
     */
    private boolean roquer(Roi roi, TypeMouvement roque, int toX, int toY){

        Case roiNext;
        Case roiFrom = plateau[roi.getX()][roi.getY()];

        Case tourFrom;
        Case tourTo;

        // On récupère la case se trouvant a cote du roi
        if (roque == TypeMouvement.PETIT_ROQUE){
            roiNext = plateau[roi.getX() + 1][roi.getY()];
        }
        else {
            roiNext = plateau[roi.getX() - 1][roi.getY()];
        }

        Case roiTo = plateau[toX][toY];

        // Le roque est interdit si le roi est en echec
        if (echec){
            return false;
        }

        // On déplace le roi d'une case et contrôle si il est en echec
        roiFrom.removePiece();
        roiNext.addPiece(roi);
        if(echec(couleurAdversaire((roi.getCouleur())))){
            roiNext.removePiece();
            roiFrom.addPiece(roi);
            return false;
        }

        // On déplace finalement le roi à la case de destination et on controle si il est en echec
        roiNext.removePiece();
        roiTo.addPiece(roi);
        if(echec(couleurAdversaire((roi.getCouleur())))){
            roiTo.removePiece();
            roiFrom.addPiece(roi);
            return false;
        }

        // On applique les changement sur l'interface graphique
        view.putPiece(roi.getType(),roi.getCouleur(),roiTo.getX(),roiTo.getY());
        view.removePiece(roiFrom.getX(),roiFrom.getY());

        if (roque == TypeMouvement.PETIT_ROQUE){
            tourFrom = plateau[roi.getX() + 1][roi.getY()];
            tourTo = plateau[roi.getX() - 1][roi.getY()];
        }
        else {
            tourFrom = plateau[roi.getX() - 2][roi.getY()];
            tourTo = plateau[roi.getX() + 1][roi.getY()];
        }

        // On place la tour sur ça nouvelle case
        Pieces tour = tourFrom.getPiece();
        tourFrom.removePiece();
        view.removePiece(tourFrom.getX(),tourFrom.getY());

        tourTo.addPiece(tour);
        view.putPiece(tour.getType(),tour.getCouleur(),tour.getX(),tour.getY());

        // Si notre roque met le roi adverse en echec, on affiche le message
        if(echec((roi.getCouleur()))){
            view.displayMessage("Echec");
        }


        roi.setFirstMoveFalse();
        ((Tours)tour).setFirstMoveFalse();


        this.tour++;
        return true;
    }

    /**
     * Permet de définir si le roi est en echec
     * @param couleurAdversaire     La couleur de notre adversaire
     * @return Vrai si le roi est en echec, sinon faux
     */
    private boolean echec(PlayerColor couleurAdversaire){
        Pieces roi;
        if (couleurAdversaire == PlayerColor.WHITE){
            roi = roiNoir;
        }
        else {
            roi = roiBlanc;
        }

        // On parcour le plateau a la recherche d'une piece qui peut manger le roi
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                Case caseActuelle = plateau[i][j];

                if (caseActuelle.isEmpty() || caseActuelle.getPiece().getCouleur() == roi.getCouleur()){
                    continue;
                }
                if (caseActuelle.getPiece().mouvementValide(plateau,roi.getX(),roi.getY(),tour) != TypeMouvement.INTERDIT){
                    echec = true;
                    return echec;
                }
            }
        }
        echec = false;
        return echec;
    }

    /**
     * Permet de recupere la couleur de notre adversaire
     * @param couleur       Notre couleur
     * @return La couleur de notre adversaire
     */
    private PlayerColor couleurAdversaire(PlayerColor couleur){
        if (couleur == PlayerColor.WHITE){
            return PlayerColor.BLACK;
        }
        return PlayerColor.WHITE;
    }


    @Override
    public void newGame() {
        tour = 1;
        echec = false;

        // On vide l'echiquier
        for (int col = 0; col < dimension; ++col) {
            for (int row = 0; row < dimension; ++row) {
                Case caseCourrante = plateau[col][row];
                if (!caseCourrante.isEmpty()) {
                    plateau[col][row].removePiece();
                }
            }
        }

        // On Cree les pieces puis on les poses sur le plateau
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