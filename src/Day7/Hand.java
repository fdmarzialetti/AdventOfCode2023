package Day7;

import java.util.*;
import java.util.stream.Collectors;

import static Day7.Day7.compareTypes;
import static Day7.Day7.gameConfig;

public class Hand implements Comparable<Hand> {
    private String cards;
    private Integer bid;

    public Hand(String cards, Integer bid) {
        this.cards = cards;
        this.bid = bid;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }


    public String determine_type_hand() {
        String type;
        Map<String, Integer> card_count = new HashMap<>();
        for(String card: cards.split("")) {
            if(card_count.containsKey(card)) card_count.put(card, card_count.get(card) + 1);
            else card_count.put(card, 1);
        }

        List<Map.Entry<String, Integer>> sets = card_count.entrySet().stream().sorted(Map.Entry.comparingByValue((t1, t2) -> Integer.compare(t2, t1))).collect(Collectors.toList());



        if(card_count.containsKey("J")){ // si un set tiene J
            if(card_count.get("J")!=5){ // si no son todas J
                Integer cantJ = card_count.entrySet().stream().filter(e->e.getKey().equals("J")).mapToInt(e->e.getValue()).sum();
                sets=sets.stream().filter(s->!s.getKey().equals("J")).collect(Collectors.toList());
                //ordenar por cantidad y valor
              sets = sets.stream().sorted((s1,s2)-> s2.getValue().compareTo(s1.getValue())).collect(Collectors.toList());
//             sets = sets.stream().sorted(Map.Entry.comparingByValue(Integer::compareTo)).collect(Collectors.toList());
                if(sets.size()!=1 && sets.get(0).getValue()==sets.get(1).getValue()){
                    if(compareTypes(sets.get(1).getKey(),sets.get(0).getKey())){
                        Map.Entry<String,Integer> auxSet = sets.get(0);
                        sets.remove(auxSet);
                        sets.add(auxSet);
                    }
                }
                // sumar canJ
                sets.get(0).setValue(sets.get(0).getValue()+cantJ);
            }
        }

        switch (sets.size()) {
            case 5:
                type = "High card";
                break;
            case 4:
                type = "One pair";
                break;
            case 3: //Conflicto, agarrar segundo elemento, si es 1 es un three of kind, si es un 2 es un two pair
                if(sets.get(1).getValue() == 2)
                    type = "Two pair";
                else type = "Three of kind";
                break;
            case 2: //Conflicto, agarrar ultimo elemento, si es 1 es un Four of a kind, si es un 2 es un Full house
                if(sets.get(sets.size()-1).getValue() == 2)
                    type = "Full house";
                else type = "Four of a kind";
                break;
            default:
                type = "Five of a kind";
                break;
        }
        return type;
    }

    public Integer card_str(Integer pos, Map<String,Integer> gameConfig){
        return gameConfig.get(cards.split("")[pos]);
    };

    public Integer handStr(Map<String,Integer> gameConfig){
        double total=0L;
        String[] arrCards = cards.split("");
        int exp = arrCards.length-1;
        for(int i =0;i<arrCards.length;i++){
            total+=gameConfig.get(arrCards[i])*Math.pow(13,exp);
            exp--;
        }
        return (int) total;
    }

    @Override
    public int compareTo(Hand o) {
        int value = 0;
        for(int i = 0; i < cards.split("").length; i++){
            String c1 = cards.split("")[i];
            String c2 = o.getCards().split("")[i];
            Integer n1 = gameConfig.get(c1);
            Integer n2 = gameConfig.get(c2);
            Integer comp = n1.compareTo(n2);
            if(comp != 0) {
                value = comp;
                break;
            }
        }
        return value;
    }

}
