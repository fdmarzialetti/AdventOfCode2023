package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Day4 {

    private List<Scratchcard> cards = new ArrayList<>();

    public void readFile() throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("src/inputs/inputDay4"));
        Integer i = 0;
        for(String line:allLines){

            List<Integer> cardNumbers = List.of(line.split(":")[1].split("\\|")[1].split(" "))
                    .stream()
                    .filter(s->!s.isEmpty())
                    .map(s->Integer.valueOf(s.trim()))
                    .collect(Collectors.toList());
            List<Integer> winningNumbers = List.of(line.split(":")[1].split("\\|")[0].split(" "))
                    .stream()
                    .filter(s->!s.isEmpty())
                    .map(s->Integer.valueOf(s.trim()))
                    .collect(Collectors.toList());;
            Scratchcard newCard=new Scratchcard(winningNumbers,cardNumbers,i);
            cards.add(newCard);
            i++;
        }
    }

    public Integer partOne(){
        return cards.stream().mapToInt(c->c.calculatePoints()).sum();
    }

    public void printCard(Integer i){
        System.out.println("WinningNumbers");
        for(Integer n:cards.get(i).getWinningNumbers()){
            System.out.println(n);
        }
        System.out.println("CardNumbers");
        for(Integer n:cards.get(i).getCardNumbers()){
            System.out.println(n);
        }
    }

    public void calculateCopies(){
        cards.forEach(c->c.setCopies(1));
        List<Integer> matches;
        for(Scratchcard card: cards){
            matches = card.getMatches();
            for(int i=card.getCardID();i<matches.size()+card.getCardID();i++){
                if(i<cards.size()){
                    cards.get(i+1).accumCopies(card.getCopies());
                }
            }
        }
    }
    public Integer totalCards(){
        return cards.stream().mapToInt(c->c.getCopies()).sum();
    }
}
