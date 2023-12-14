package Day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day8 {
    private static Map<String,Node> navigateMap = new HashMap<>();
    private static List<String> instructions;

    private List<String> startingValues;
    public void readAndProcessInput() throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/inputs/inputDay8"));
        instructions = List.of(input.get(0).split(""));
        input.remove(0);
        String newInput = input.stream().collect(Collectors.joining(" "));
        Pattern pattern = Pattern.compile("(\\w{3}) = [(](\\w{3}), (\\w{3})[)]");
        Matcher matcher = pattern.matcher(newInput);
        matcher.results().forEach(x -> navigateMap.put(x.group(1),new Node(x.group(3),x.group(2))));
    }

    public void determineStartingValues(){
        startingValues = navigateMap.entrySet().stream().map(n->n.getKey()).filter(v->v.endsWith("A")).collect(Collectors.toList());
    }

    public static Integer count_steps(String start){
        Boolean find = false;
        Integer steps = 0;
        String position = start;
        while(!find){
            for(String instruction:instructions){
                if(instruction.equals("R")){
                    position = navigateMap.get(position).getRight();
                }else position = navigateMap.get(position).getLeft();
                steps++;
                if(position.endsWith("Z")) find=true;
                if(find)break;
            }
        }
        return steps;
    }

    public static long count_simultaneous_steps_B() {
        List<Map.Entry<String, Node>> nodes_A = navigateMap.entrySet().stream().filter(kv -> kv.getKey().endsWith("A")).collect(Collectors.toList());
        List<Integer> counts = nodes_A.stream().map(n -> count_steps(n.getKey())).sorted().collect(Collectors.toList());
        System.out.println("");
        return counts.stream().mapToLong(Long::valueOf).reduce(Day8::mcm).getAsLong();
    }

    public static long mcd(long a, long b) {
        long temp;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static long mcm(long a, long b) {
        return (a * b) / mcd(a, b);
    }
}
