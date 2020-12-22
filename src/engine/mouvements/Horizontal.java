package engine.mouvements;

public class Horizontal extends Mouvements{
    public Horizontal(Direction direction){
        if( direction != Direction.GAUCHE && direction != Direction.DROITE){
            throw new RuntimeException("Pas bien, horizontal c'est GAUCHE et DROITE");
        }
        this.direction = direction;
    }
}
