package Day10.TypeTiles;

import Day10.Coordinate;
import Day10.TypeTile;

public class TypeP extends TypeTile {
    public TypeP(String value) {
        super(value);
    }

    @Override
    public Coordinate determine_next_coordinate(Coordinate last, Coordinate act) {
        return null;
    }


}
