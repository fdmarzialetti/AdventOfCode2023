package Day10.TypeTiles;

import Day10.Coordinate;
import Day10.TypeTile;

public class TypeH extends TypeTile {

    public TypeH(String value) {
        super(value);
    }

    @Override
    public Coordinate determine_next_coordinate(Coordinate last, Coordinate act) {

        Coordinate next = new Coordinate();
        next.setY(act.getY());
        if(last.getX()<act.getX()){
            next.setX(act.getX()+1);
        }else {
            next.setX(act.getX()-1);
        }
        return next;
    }


}
