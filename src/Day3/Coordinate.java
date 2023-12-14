package Day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Coordinate {
    private int x;
    private int y;

    private Boolean isGear;
    private List<Integer> gears = new ArrayList<>();

    public Coordinate(int x,int y){
        this.x=x;
        this.y=y;
        isGear=false;
    }

    public String printCoordinates(){
        return "X:"+x+" Y:"+y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void checkAdyacent(int heigth, int width, List<EnginePart> parts){
        for(int i=x-1;i<=x+1;i++){
            for(int j=y-1;j<=y+1;j++){
                if(!(i<0 | j<0 | i>=heigth | j>=width)){
                    for(EnginePart part:parts){
                        if(!part.isCheck()){
                            if(part.containCoordinate(new Coordinate(i,j))){
                                part.checkPart();
                            }
                        }
                    }
                }
            }
        }
    }

    public void checkGears(int heigth, int width, List<EnginePart> parts){
        parts.forEach(p->p.setCheck(false));
        for(int i=x-1;i<=x+1;i++){
            for(int j=y-1;j<=y+1;j++){
                if(!(i<0 | j<0 | i>=heigth | j>=width)){
                    if(isGear){
                        for(EnginePart part:parts){
                            if(!part.isCheck()){
                                if(part.containCoordinate(new Coordinate(i,j))){
                                    gears.add(part.getNumber());
                                    part.checkPart();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Integer calculateGear(){
        if(gears.size()==2){
            return gears.stream().reduce(1,(a,b)->a*b);
        }
        return 0;
    }

    public void setIsGear(){
        isGear=true;
    }

    public Boolean getIsGear(){
        return isGear;
    }
}
