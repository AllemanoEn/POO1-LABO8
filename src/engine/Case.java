package engine;
import engine.pieces.Pieces;


public class Case {
    int x;
    int y;

    Pieces piece;

    Case(int x, int y){
        this.x = x;
        this.y = y;
    }

   boolean isEmpty(){
        return piece == null;
   }

   void addPiece(Pieces p){
        if (piece != null){
            removePiece();
        }
        piece = p;
        piece.setCase(this);
   }
   void removePiece(){
        piece.setCase(null);
        piece = null;
   }

   int getX(){
        return x;
   }
   int getY(){
        return y;
   }
   Pieces getPiece(){
        return piece;
   }

}
