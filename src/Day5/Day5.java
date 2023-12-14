package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Day5 {

    private List<Seed> seeds=new ArrayList<>();

    private List<CategoryMapping> categories = new ArrayList<>();

    public Long partOne(){
        for(Seed s:seeds){
            for(CategoryMapping c:categories){
                s.addDestinationValue(c.calculateConvertion(s.lastDestination()));
            }
        }
        return seeds.stream().map(x -> x.getDestinations().get(x.getDestinations().size()-1)).mapToLong(Long::valueOf).min().getAsLong();
    }

    public Long partTwo(){
        Long minDestination = (long) Double.POSITIVE_INFINITY;
        Integer pairs = seeds.size()/2;
        Long lastDestination=0L;
        for(int pair=0;pair<pairs;pair+=2) {
            lastDestination = pairMinDestination(seeds.get(pair).getId(), seeds.get(pair+1).getId());
            if (lastDestination < minDestination) {
                minDestination = lastDestination;
            }
        }
        return lastDestination;
    }

    public Long pairMinDestination(Long start, Long range){
        Long minDestination = (long) Double.POSITIVE_INFINITY;
        Long lastDestination=0L;
        for(long i=start;i<start+range;i++){
            lastDestination=i;
            for(CategoryMapping c:categories){
                lastDestination = c.calculateConvertion(lastDestination);
            }
            if(lastDestination<=minDestination){
                minDestination = lastDestination;
            }
        }
        return minDestination;
    }

    public void readAndProcessInput() throws IOException {
        String file = Files.readString(Paths.get("src/inputs/inputDay5"));
        String[] sections= file.split("\\n\\s*\\n");
        String[] seedsSection = sections[0].split(":");
        List<String> seedsValues = List.of(seedsSection[1].trim().split(" "));
        seedsValues.stream().forEach(s->seeds.add(new Seed(Long.valueOf(s))));
        for(int i=1;i<sections.length;i++){ // Por cada seccion
            String[] tittleAndNumbers = sections[i].split(":");
            String[] tittle = tittleAndNumbers[0].split("-");
            String[] values = tittleAndNumbers[1].trim().split("\\n");
            String source = tittle[0];
            String destination = tittle[2].split(" ")[0];
            List<Convertion> convertions = new ArrayList<>();
            for(int j=0;j<values.length;j++){ // Por cada linea de la seccion
                String[] valuesPerLine = values[j].split(" ");
                Long sourceValue = Long.valueOf(valuesPerLine[1]);
                Long destinationValue = Long.valueOf(valuesPerLine[0]);
                Long rangeValue = Long.valueOf(valuesPerLine[2].trim());
                convertions.add(new Convertion(destinationValue,sourceValue,rangeValue));
            }
            categories.add(new CategoryMapping(source,destination,convertions));
        }
    }
}
