package engine;
import engine.pieces.Pieces;


public class Case {
    int x;
    int y;

    private Pieces piece;

    Case(int x, int y){
        this.x = x;
        this.y = y;
    }

   public boolean isEmpty(){
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
