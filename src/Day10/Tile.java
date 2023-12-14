package Day10;

import java.util.List;

public class Tile {
    private TypeTile type;
    private Coordinate coordinate;
    private List<Coordinate> Adyacents;
    private Tile previous;
    private Tile next;

    public TypeTile getType() {
        return type;
    }

    public void setType(TypeTile type) {
        this.type = type;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public List<Coordinate> getAdyacents() {
        return Adyacents;
    }

    public void setAdyacents(List<Coordinate> adyacents) {
        Adyacents = adyacents;
    }

    public Tile getPrevious() {
        return previous;
    }

    public void setPrevious(Tile previous) {
        this.previous = previous;
    }

    public Tile getNext() {
        return next;
    }

    public void setNext(Tile next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return type.toString() + coordinate.toString();

    }
}
