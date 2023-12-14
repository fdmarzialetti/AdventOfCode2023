package Day10.TypeTiles;

import Day10.Coordinate;
import Day10.TypeTile;

public class TypeF extends TypeTile {

    public TypeF(String value) {
        super(value);
    }

    @Override
    public Coordinate determine_next_coordinate(Coordinate last, Coordinate act) {

        Coordinate next = new Coordinate();
        if (act.getY().equals(last.getY())){
            next.setX(act.getX());
            next.setY(act.getY()+1);
        }else{
            next.setX(act.getX()+1);
            next.setY(act.getY());
        }
        return next;
    }


}
