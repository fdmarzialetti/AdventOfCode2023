package Day10;

abstract public class TypeTile {
    private String value;

    protected TypeTile(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    abstract public Coordinate determine_next_coordinate(Coordinate last, Coordinate act);

}
