import chess.ChessController;
import chess.ChessView;
import chess.views.gui.GUIView;
import engine.Plateau;

public class Main {
    public static void main(String ... args){
        ChessController chessboard = new Plateau();
        ChessView view = new GUIView(chessboard);
        chessboard.start(view);
    }
}

