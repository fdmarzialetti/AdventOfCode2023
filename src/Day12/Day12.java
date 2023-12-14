package Day12;

import java.util.ArrayList;
import java.util.List;

public class Day12 {

    public void parte1() {
        List<Integer> group = List.of(3, 2, 1);
        Integer sizeArrangement = "?###????????".length();
        List<String> damagedSprings = new ArrayList<>();
        for(Integer num:group){
            String springGroup = "";
            springGroup+="#".repeat(num);
            damagedSprings.add(springGroup);
        }
        System.out.println(damagedSprings);
    }


    // posible strings
    List<String> posibles = new ArrayList<>();


}
