package Day2;

import com.sun.tools.jconsole.JConsoleContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day2 {

    final Map<String,Integer> configuration = new HashMap<>();
    private List<Game> games;

    public Day2(){
        configuration.put("red",12);
        configuration.put("green",13);
        configuration.put("blue",14);
        games=new ArrayList<>();
    }
    public void partOne() throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("src/inputs/inputDay2"));
        for(int i=0; i<= allLines.size()-1;i++){
            List<String> line = Arrays.asList(allLines.get(i).split(":"));
            List<String> sets = Arrays.asList(line.get(1).split(";"));
            Game game=new Game(i+1,sets);
            for (String set: sets){
                List<String> pairs=Arrays.asList(set.split(","));
                for(String pair:pairs){
                    String[] valueKey = pair.split(" "); // valueKey[" ",int,String]
                    //game.addToColorsTotal(valueKey[2],Integer.valueOf(valueKey[1]));
                    game.addToSets(valueKey[2],Integer.valueOf(valueKey[1]));
                }
            }
            games.add(game);
        }
        int total=0;
        int power=0;
        for(Game g:games){
            if(!g.hadAnImposibleSet(configuration)){
                total+=g.getId();
            }
            power+=g.getPowerSet();
        }
        System.out.println("PossibleTotal: "+total);
        System.out.println("PowerTotal: "+power);
    }
}
