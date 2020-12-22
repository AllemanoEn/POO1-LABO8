package engine.mouvements;

public class Vertical extends Mouvements {

    public Vertical(Direction direction){
        if( direction != Direction.BAS && direction != Direction.HAUT){
            throw new RuntimeException("Pas bien, vertical c'est en BAS et en HAUT");
        }
        this.direction = direction;
    }
}
