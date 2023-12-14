package Day10.TypeTiles;

import Day10.Coordinate;
import Day10.TypeTile;

public class TypeV extends TypeTile {

    public TypeV(String value) {
        super(value);
    }

    @Override
    public Coordinate determine_next_coordinate(Coordinate last, Coordinate act) {
        Coordinate next = new Coordinate();
        next.setX(act.getX());
        if(last.getY()<act.getY()){
            next.setY(act.getY()+1);
        }else {
            next.setY(act.getY()-1);
        }
        return next;
    }

}
