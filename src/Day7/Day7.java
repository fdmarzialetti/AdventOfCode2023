package Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7 {
    private List<Hand> hands = new ArrayList<>();
    public static final Map<String,Integer> gameConfig= new HashMap<>(){{
        put("A", 13);
        put("K", 12);
        put("Q", 11);
        put("T", 10);
        put("9", 9);
        put("8", 8);
        put("7", 7);
        put("6", 6);
        put("5", 5);
        put("4", 4);
        put("3", 3);
        put("2", 2);
        put("J", 1);
    }};
    private static final LinkedHashMap<String, List<Hand>> type_hands = new LinkedHashMap<>(){{
        put("High card", new ArrayList<>());
        put("One pair", new ArrayList<>());
        put("Two pair", new ArrayList<>());
        put("Three of kind", new ArrayList<>());
        put("Full house", new ArrayList<>());
        put("Four of a kind", new ArrayList<>());
        put("Five of a kind", new ArrayList<>());
    }};

    public void readAndProccessInput() throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/inputs/inputDay7"));
        String cards;
        String bid;
        for(String line:input){
            cards=line.split(" ")[0].trim();
            bid=line.split(" ")[1].trim();
            Hand newHand = new Hand(cards,Integer.valueOf(bid));
            hands.add(newHand);
            type_hands.get(newHand.determine_type_hand()).add(newHand);
        }
    }

    public Long calculateWinnings(){
        Long winnings=0L;
        int count=1;
        for(Map.Entry<String,List<Hand>> m: type_hands.entrySet()){
            List<Hand> sortedHand;
            sortedHand = m.getValue().stream().sorted((h1, h2) -> h1.handStr(gameConfig).compareTo(h2.handStr(gameConfig))).collect(Collectors.toList());
            for(Hand h:sortedHand) {
                winnings+=h.getBid()*count;
                count++;
            }
        }
        return winnings;
    }

    public static Boolean compareTypes(String c1, String c2){ // si c1>c2 = true;
     return gameConfig.get(c1)>gameConfig.get(c2);
    }

}
