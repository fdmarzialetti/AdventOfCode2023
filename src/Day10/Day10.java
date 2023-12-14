package Day10;
import Day10.TypeTiles.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day10 {
    private List<List<Tile>> tiles = new ArrayList<>();
    private Tile startingTile;

    private Coordinate firstLastCoordinate;

    public void readAndProcessInput() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/inputs/inputDay10"));
        for(int y=0;y<input.size();y++){
            List<String>line = List.of(input.get(y).split(""));
            List<Tile> auxTiles = new ArrayList<>();
            for(int x=0;x<line.size();x++){
                Tile tile = new Tile();
                tile.setCoordinate(new Coordinate(x,y));
                switch (line.get(x)) {
                    case "S":
                        tile.setType(null);
                        startingTile = tile;
                        break;
                    case "-":
                        tile.setType(new TypeH("-"));
                        break;
                    case "7":
                        tile.setType(new Type7("7"));
                        break;
                    case "J":
                        tile.setType(new TypeJ("J"));
                        break;
                    case "L":
                        tile.setType(new TypeL("L"));
                        break;
                    case "|":
                        tile.setType(new TypeV("|"));
                        break;
                    case "F":
                        tile.setType(new TypeF("F"));
                        break;
                    case ".":
                        tile.setType(new TypeP("."));
                        break;
                }
                auxTiles.add(tile);
            }
            tiles.add(auxTiles);
        }
        determine_first_tile_type();
    }

    public void determine_first_tile_type(){
        Coordinate start = startingTile.getCoordinate();
        String rightValue =tiles.get(start.getY()).get(start.getX()+1).getType().getValue();
        String downValue =tiles.get(start.getY()+1).get(start.getX()).getType().getValue();
        if(List.of("-","J","7").contains(rightValue)){
            startingTile.setType(new TypeH("S"));
            firstLastCoordinate = new Coordinate(start.getX()-1,start.getY());
        }else{
            if(List.of("|","J","L").contains(downValue)){
                startingTile.setType(new TypeV("S"));
                firstLastCoordinate = new Coordinate(start.getX(),start.getY()-1);
            }else{
                startingTile.setType(new TypeH("S"));
                firstLastCoordinate = new Coordinate(start.getX()-1,start.getY());
            }
        }
    }

    public void printTiles(){
        for (List<Tile> tilesY:tiles){
            String buffer="";
            for(Tile tilesX:tilesY){
                buffer+= tilesX.getType().getValue();
            }
            System.out.println(buffer);
        }
    }

    public Integer calculate_steps(){
        Integer steps=0;
        Coordinate actual = new Coordinate(startingTile.getCoordinate().getX(),startingTile.getCoordinate().getY());
        Coordinate last = new Coordinate(firstLastCoordinate.getX(), firstLastCoordinate.getY());
        do{
            Coordinate next = tiles.get(actual.getY()).get(actual.getX()).getType().determine_next_coordinate(last,actual);
            last.setX(actual.getX());
            last.setY(actual.getY());
            actual.setX(next.getX());
            actual.setY(next.getY());
            steps++;
        }while(!actual.compareCoordinate(startingTile.getCoordinate()));
        return steps/2;
    }
}
