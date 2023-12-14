package Day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private int id;
    private List<String> gameLine;

    private Map<String,Integer> colorsMin;

    private List<Map<String,Integer>> sets;

    public Game(int id, List<String> gameLine){
       this.id=id;
       this.gameLine=gameLine;
       colorsMin =new HashMap<>();
       colorsMin.put("red",0);
       colorsMin.put("green",0);
       colorsMin.put("blue",0);
       sets=new ArrayList<>();
    }

    public void addToSets(String color,Integer cant){
        Map<String,Integer> set = new HashMap<>();
        set.put(color,cant);
        sets.add(set);
    }

    public Boolean hadAnImposibleSet(Map<String,Integer> config){
        Boolean impossibleSet = false;
        for(Map<String,Integer>set:sets){
            String color = set.keySet().toArray()[0].toString();
            Integer value=set.get(color);
            impossibleSet = impossibleSet | config.get(color)<set.get(color);
        }
        return impossibleSet;
    }

    public int getId() {
        return id;
    }

    public Integer getPowerSet(){
        for(Map<String,Integer>set:sets){
            String color = set.keySet().toArray()[0].toString();
            Integer value=set.get(color);
            if(colorsMin.get(color)<=value){
                colorsMin.replace(color,value);
            }
        }
        return colorsMin.values().stream().reduce(1, (a, b) -> a * b);
    }
}
