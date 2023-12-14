package Day11;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day11 {
    private List<Coordinate> galaxies = new ArrayList<>();
    private Set<Integer> expandColumns = new HashSet<>();
    private Set<Integer> expandRows = new HashSet<>();

    public void readAndProcesInput() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/inputs/inputDay11"));
        Boolean[] visitColumn = new Boolean[input.get(0).length()];
        Arrays.fill(visitColumn, false);
        for(int y=0;y<input.size();y++){
            List<String> row = List.of(input.get(y).split(""));
            if(row.stream().allMatch(s->s.equals("."))){
                expandRows.add(y);
            }
            for(int x=0;x<input.size();x++){
                if(row.get(x).equals("#")){
                    galaxies.add(new Coordinate(x,y));
                }
                if(!visitColumn[x]){
                    Boolean findGalaxyInColumn = false;
                    Integer posY = 0;
                    while(posY<input.size() && !findGalaxyInColumn ){
                        findGalaxyInColumn = input.get(posY).split("")[x].equals("#");
                        posY++;
                    }
                    if(!findGalaxyInColumn){
                        expandColumns.add(x);
                    }
                    visitColumn[x]=true;
                }
            }
        }
    }

    public Long calculate_paths_part_one(){
        Long pathCount=0L;
        List<Coordinate> auxGalaxies = new ArrayList<>(galaxies);
        for(int i=0;i<galaxies.size();i++){
            Coordinate galaxy = auxGalaxies.get(0);
            auxGalaxies.remove(0);
            for(Coordinate g:auxGalaxies){
                pathCount += Math.abs(g.getX()-galaxy.getX()) + Math.abs(g.getY()-galaxy.getY());
                for(Integer expandY:expandRows){
                    if(inBetween(expandY,g.getY(),galaxy.getY())){
                        pathCount++;
                    }
                }
                for(Integer expandX:expandColumns){
                    if(inBetween(expandX,g.getX(),galaxy.getX())){
                        pathCount++;
                    }
                }
            }
        }
        return pathCount;
    }

    public Long calculate_paths_part_two(){
        Long pathCount=0L;
        List<Coordinate> auxGalaxies = new ArrayList<>(galaxies);
        for(int i=0;i<galaxies.size();i++){
            Coordinate galaxy = auxGalaxies.get(0);
            auxGalaxies.remove(0);
            for(Coordinate g:auxGalaxies){
                pathCount += Math.abs(g.getX()-galaxy.getX()) + Math.abs(g.getY()-galaxy.getY());
                for(Integer expandY:expandRows){
                    if(inBetween(expandY,g.getY(),galaxy.getY())){
                        pathCount+=1000000-1;
                    }
                }
                for(Integer expandX:expandColumns){
                    if(inBetween(expandX,g.getX(),galaxy.getX())){
                        pathCount+=1000000-1;
                    }
                }
            }
        }
        return pathCount;
    }

    public Boolean inBetween(Integer n1, Integer n2, Integer n3){
        return (n2<n1&&n3>n1)||(n2>n1&&n3<n1);
    }
}
