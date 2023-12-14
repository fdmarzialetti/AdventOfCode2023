package Day3;

import java.util.ArrayList;
import java.util.List;

public class EnginePart {
    private Integer number;
    private List<Coordinate> coordinates = new ArrayList<>();

    private Boolean check;

    public EnginePart(Integer number){
        check=false;
        this.number=number;
    }

    public Integer getNumber(){
        return number;
    }

    public List<Coordinate> getCoordinates(){
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates){
        this.coordinates=coordinates;
    }

    public Boolean containCoordinate(Coordinate coordinate){
        return coordinates.stream().anyMatch(c->c.getY()==coordinate.getY() && c.getX()==coordinate.getX());
    }
    public void checkPart(){
        this.check=true;
    }

    public Boolean isCheck(){
        return check;
    }

    public void setCheck(Boolean check){
        this.check=check;
    }

}
