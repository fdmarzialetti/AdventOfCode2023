package Day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {

    List<List<Long>> historyValues= new ArrayList<>();

    public Long calculate(String part){
        Long total=0L;
        if(part.equals("part1")) {
            for (List<Long> values : historyValues) {
                total += predict(values);
            }
        }else
            for(List<Long> values:historyValues){
                total += predict2(values);
            }
        return total;
    };

    public Long predict (List<Long> values){
        if(values.stream().allMatch(v->v==0L)){
        return values.get(values.size()-1);
      }
        List<Long> auxList = new ArrayList<>();
        for(int i=0;i<values.size()-1;i++){
            auxList.add(values.get(i+1)-values.get(i));
        }
        return values.get(values.size()-1)+predict(auxList);
    };

    public Long predict2 (List<Long> values){
        if(values.stream().allMatch(v->v==0L)){
            return values.get(0);
        }
        List<Long> auxList = new ArrayList<>();
        for(int i=0;i<values.size()-1;i++){
            auxList.add(values.get(i+1)-values.get(i));
        }
        return values.get(0)-predict2(auxList);
    };

    public void readAndProcessInput() throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/inputs/inputDay9"));
        for(String line:input){
           historyValues.add(Arrays.stream(line.split(" ")).map(v->Long.parseLong(v)).collect(Collectors.toList()));
        }
    }

}
